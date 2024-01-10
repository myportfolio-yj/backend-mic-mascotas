package org.veterinaria.infraestructura.adaptador.salida.Vacuna;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;
import org.veterinaria.aplicacion.puertos.salida.Vacuna.IVacunaRepositorio;
import org.veterinaria.dominio.modelo.Vacuna.VacunaEntidad;

import java.util.List;

@ApplicationScoped
public class VacunaRepositorio implements PanacheMongoRepository<VacunaEntidad>, IVacunaRepositorio {

  @Override
  public List<VacunaEntidad> obtenerTodosVacuna() {
    return listAll().parallelStream()
          .filter(p -> p.getBorrado() == null || !p.getBorrado())
          .toList();
  }

  @Override
  public VacunaEntidad obtenerVacunaPorId(String idVacuna) {
    return findByIdOptional(new ObjectId(idVacuna))
          .filter(p -> p.getBorrado() == null || !p.getBorrado())
          .orElseGet(VacunaEntidad::new);
  }

  @Override
  public VacunaEntidad crearVacuna(VacunaEntidad vacuna) {
    vacuna.setBorrado(false);
    vacuna.persist();
    return vacuna;
  }

  @Override
  public VacunaEntidad actualizarVacuna(String idVacuna, VacunaEntidad vacuna) {
    return findByIdOptional(new ObjectId(idVacuna))
          .map(p -> {
            p.setVacuna(vacuna.getVacuna());
            p.setDuracionISO(vacuna.getDuracionISO());
            p.setBorrado(vacuna.getBorrado());
            update(p);
            return p;
          })
          .orElseThrow(() -> new RuntimeException("Vacuna no encontrado"));
  }

  @Override
  public VacunaEntidad eliminarVacuna(String idVacuna) {
    VacunaEntidad vacuna = this.obtenerVacunaPorId(idVacuna);
    vacuna.setBorrado(true);
    return this.actualizarVacuna(idVacuna, vacuna);
  }
}
