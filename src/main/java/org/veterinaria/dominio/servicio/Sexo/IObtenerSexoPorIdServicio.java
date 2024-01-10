package org.veterinaria.dominio.servicio.Sexo;

import org.veterinaria.dominio.modelo.Sexo.SexoSalida;

public interface IObtenerSexoPorIdServicio {
  SexoSalida obtenerSexoPorId(String idSexo);
}
