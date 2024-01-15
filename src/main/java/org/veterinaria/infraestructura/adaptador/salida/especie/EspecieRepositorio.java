package org.veterinaria.infraestructura.adaptador.salida.especie;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;
import org.veterinaria.aplicacion.puertos.salida.especie.IEspecieRepositorio;
import org.veterinaria.dominio.modelo.especie.EspecieEntidad;

import java.util.List;

@ApplicationScoped
public class EspecieRepositorio implements PanacheMongoRepository<EspecieEntidad>, IEspecieRepositorio {
  @Override
  public List<EspecieEntidad> obtenerTodosEspecie() {
    return listAll().parallelStream()
          .filter(p -> p.getBorrado() == null || !p.getBorrado())
          .toList();
  }

  @Override
  public EspecieEntidad obtenerEspeciePorId(String idEspecie) {
    return findByIdOptional(new ObjectId(idEspecie))
          .filter(p -> p.getBorrado() == null || !p.getBorrado())
          .orElseGet(EspecieEntidad::new);
  }

  @Override
  public EspecieEntidad crearEspecie(EspecieEntidad especie) {
    especie.setBorrado(false);
    especie.persist();
    return especie;
  }

  @Override
  public EspecieEntidad actualizarEspecie(String idEspecie, EspecieEntidad especie) {
    return findByIdOptional(new ObjectId(idEspecie))
          .map(p -> {
            p.setEspecie(especie.getEspecie());
            p.setBorrado(especie.getBorrado());
            update(p);
            return p;
          })
          .orElseThrow(() -> new RuntimeException("Especie no encontrado"));
  }

  @Override
  public EspecieEntidad eliminarEspecie(String idEspecie) {
    EspecieEntidad especie = this.obtenerEspeciePorId(idEspecie);
    especie.setBorrado(true);
    return this.actualizarEspecie(idEspecie, especie);
  }
}