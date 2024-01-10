package org.veterinaria.infraestructura.adaptador.salida.Raza;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;
import org.veterinaria.aplicacion.puertos.salida.Raza.IRazaRepositorio;
import org.veterinaria.dominio.modelo.Raza.RazaEntidad;

import java.util.List;

@ApplicationScoped
public class RazaRepositorio implements PanacheMongoRepository<RazaEntidad>, IRazaRepositorio {
  @Override
  public List<RazaEntidad> obtenerTodosRaza() {
    return listAll().parallelStream()
          .filter(p -> p.getBorrado() == null || !p.getBorrado())
          .toList();
  }

  @Override
  public RazaEntidad obtenerRazaPorId(String idRaza) {
    return findByIdOptional(new ObjectId(idRaza))
          .filter(p -> p.getBorrado() == null || !p.getBorrado())
          .orElseGet(RazaEntidad::new);
  }

  @Override
  public RazaEntidad crearRaza(RazaEntidad raza) {
    raza.setBorrado(false);
    raza.persist();
    return raza;
  }

  @Override
  public RazaEntidad actualizarRaza(String idRaza, RazaEntidad raza) {
    return findByIdOptional(new ObjectId(idRaza))
          .map(p -> {
            p.setIdEspecie(raza.getIdEspecie());
            p.setBorrado(raza.getBorrado());
            update(p);
            return p;
          })
          .orElseThrow(() -> new RuntimeException("Raza no encontrado"));
  }

  @Override
  public RazaEntidad eliminarRaza(String idRaza) {
    RazaEntidad raza = this.obtenerRazaPorId(idRaza);
    raza.setBorrado(true);
    return this.actualizarRaza(idRaza, raza);
  }

  @Override
  public List<RazaEntidad> findByEspecie(String idEspecie) {
    return find("idEspecie", idEspecie)
          .stream()
          .toList();
  }
}