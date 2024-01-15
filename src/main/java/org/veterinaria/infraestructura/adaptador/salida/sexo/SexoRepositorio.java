package org.veterinaria.infraestructura.adaptador.salida.sexo;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;
import org.veterinaria.aplicacion.puertos.salida.sexo.ISexoRepositorio;
import org.veterinaria.dominio.modelo.sexo.SexoEntidad;

import java.util.List;

@ApplicationScoped
public class SexoRepositorio implements PanacheMongoRepository<SexoEntidad>, ISexoRepositorio {
  @Override
  public List<SexoEntidad> obtenerTodosSexo() {
    return listAll().parallelStream()
          .filter(p -> p.getBorrado() == null || !p.getBorrado())
          .toList();
  }

  @Override
  public SexoEntidad obtenerSexoPorId(String idSexo) {
    return findByIdOptional(new ObjectId(idSexo))
          .filter(p -> p.getBorrado() == null || !p.getBorrado())
          .orElseGet(SexoEntidad::new);
  }

  @Override
  public SexoEntidad crearSexo(SexoEntidad sexo) {
    sexo.setBorrado(false);
    sexo.persist();
    return sexo;
  }

  @Override
  public SexoEntidad actualizarSexo(String idSexo, SexoEntidad sexo) {
    return findByIdOptional(new ObjectId(idSexo))
          .map(p -> {
            p.setSexo(sexo.getSexo());
            p.setBorrado(sexo.getBorrado());
            update(p);
            return p;
          })
          .orElseThrow(() -> new RuntimeException("Sexo no encontrado"));
  }

  @Override
  public SexoEntidad eliminarSexo(String idSexo) {
    SexoEntidad sexo = this.obtenerSexoPorId(idSexo);
    sexo.setBorrado(true);
    return this.actualizarSexo(idSexo, sexo);
  }
}
