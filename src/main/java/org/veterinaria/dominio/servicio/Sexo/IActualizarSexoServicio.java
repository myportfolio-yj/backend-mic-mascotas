package org.veterinaria.dominio.servicio.Sexo;

import org.veterinaria.dominio.modelo.Sexo.SexoEntrada;
import org.veterinaria.dominio.modelo.Sexo.SexoSalida;

public interface IActualizarSexoServicio {
  SexoSalida actualizarSexo(String idSexo, SexoEntrada sexo);
}
