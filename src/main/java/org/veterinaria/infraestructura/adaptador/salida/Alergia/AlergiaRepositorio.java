package org.veterinaria.infraestructura.adaptador.salida.Alergia;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;
import org.veterinaria.aplicacion.puertos.salida.Alergia.IAlergiaRepositorio;
import org.veterinaria.dominio.modelo.Alergia.AlergiaEntidad;

import java.util.List;

@ApplicationScoped
public class AlergiaRepositorio implements PanacheMongoRepository<AlergiaEntidad>, IAlergiaRepositorio {
  @Override
  public List<AlergiaEntidad> obtenerTodosAlergia() {
    return listAll().parallelStream()
          .filter(p -> p.getDelete() == null || !p.getDelete())
          .toList();
  }

  @Override
  public AlergiaEntidad obtenerAlergiaPorId(String idAlergia) {
    return findByIdOptional(new ObjectId(idAlergia))
          .filter(p -> p.getDelete() == null || !p.getDelete())
          .orElseGet(AlergiaEntidad::new);
  }

  @Override
  public AlergiaEntidad crearAlergia(AlergiaEntidad alergia) {
    alergia.setDelete(false);
    alergia.persist();
    return alergia;
  }

  @Override
  public AlergiaEntidad actualizarAlergia(String idAlergia, AlergiaEntidad alergia) {
    return findByIdOptional(new ObjectId(idAlergia))
          .map(p -> {
            p.setAlergia(alergia.getAlergia());
            p.setDelete(alergia.getDelete());
            update(p);
            return p;
          })
          .orElseThrow(() -> new RuntimeException("Alergia no encontrado"));
  }

  @Override
  public AlergiaEntidad eliminarAlergia(String idAlergia) {
    AlergiaEntidad alergia = this.obtenerAlergiaPorId(idAlergia);
    alergia.setDelete(true);
    return this.actualizarAlergia(idAlergia, alergia);
  }
}
