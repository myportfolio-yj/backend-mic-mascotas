package org.veterinaria.dominio.servicio.sexo;

import org.veterinaria.dominio.modelo.sexo.SexoEntrada;
import org.veterinaria.dominio.modelo.sexo.SexoSalida;

public interface ICrearSexoServicio {
  SexoSalida crearSexo(SexoEntrada sexo);
}
