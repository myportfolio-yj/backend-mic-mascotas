package org.veterinaria.dominio.servicio.sexo;

import org.veterinaria.dominio.modelo.sexo.SexoSalida;

public interface IObtenerSexoPorIdServicio {
  SexoSalida obtenerSexoPorId(String idSexo);
}
