package org.veterinaria.dominio.servicio.Sexo;

import org.veterinaria.dominio.modelo.Sexo.SexoEntrada;
import org.veterinaria.dominio.modelo.Sexo.SexoSalida;

public interface ICrearSexoServicio {
  SexoSalida crearSexo(SexoEntrada sexo);
}
