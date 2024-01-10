package org.veterinaria.dominio.servicio.Sexo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.veterinaria.aplicacion.puertos.salida.Sexo.ISexoRepositorio;
import org.veterinaria.dominio.modelo.Sexo.SexoEntidad;
import org.veterinaria.dominio.modelo.Sexo.SexoEntrada;
import org.veterinaria.dominio.modelo.Sexo.SexoSalida;

import java.util.List;

@ApplicationScoped
public class SexoServicio implements ISexoServicio {
  @Inject
  ISexoRepositorio repositorio;

  @Override
  public SexoSalida actualizarSexo(String idSexo, SexoEntrada sexo) {
    SexoEntidad sexoEntidad = new SexoEntidad();
    sexoEntidad.setSexo(sexo.getSexo());
    repositorio.actualizarSexo(idSexo, sexoEntidad);
    return this.obtenerSexoPorId(idSexo);
  }

  @Override
  public SexoSalida crearSexo(SexoEntrada sexo) {
    SexoEntidad sexoEntidad = new SexoEntidad();
    sexoEntidad.setSexo(sexo.getSexo());
    sexoEntidad = repositorio.crearSexo(sexoEntidad);
    return this.obtenerSexoPorId(sexoEntidad.getId().toString());
  }

  @Override
  public SexoSalida eliminarSexo(String idSexo) {
    SexoEntidad sexoEntidad = repositorio.eliminarSexo(idSexo);
    return SexoSalida.builder()
          .id(sexoEntidad.getId().toString())
          .sexo(sexoEntidad.getSexo())
          .build();
  }

  @Override
  public SexoSalida obtenerSexoPorId(String idSexo) {
    SexoEntidad sexoEntidad = repositorio.obtenerSexoPorId(idSexo);
    return SexoSalida.builder()
          .id(sexoEntidad.getId().toString())
          .sexo(sexoEntidad.getSexo())
          .build();
  }

  @Override
  public List<SexoSalida> obtenerSexo() {
    List<SexoEntidad> sexos = repositorio.obtenerTodosSexo();
    return sexos.parallelStream().map(sexoEntidad -> SexoSalida.builder()
          .id(sexoEntidad.getId().toString())
          .sexo(sexoEntidad.getSexo())
          .build()
    ).toList();
  }
}