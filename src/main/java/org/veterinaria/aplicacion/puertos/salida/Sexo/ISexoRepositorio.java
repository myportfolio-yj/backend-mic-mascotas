package org.veterinaria.aplicacion.puertos.salida.Sexo;

import org.veterinaria.dominio.modelo.Sexo.SexoEntidad;

import java.util.List;

public interface ISexoRepositorio {
  List<SexoEntidad> obtenerTodosSexo();

  SexoEntidad obtenerSexoPorId(String idSexo);

  SexoEntidad crearSexo(SexoEntidad sexo);

  SexoEntidad actualizarSexo(String idSexo, SexoEntidad sexo);

  SexoEntidad eliminarSexo(String idSexo);
}
