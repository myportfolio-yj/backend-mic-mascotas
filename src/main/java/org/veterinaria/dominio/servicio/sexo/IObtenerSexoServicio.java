package org.veterinaria.dominio.servicio.sexo;

import org.veterinaria.dominio.modelo.sexo.SexoSalida;

import java.util.List;

public interface IObtenerSexoServicio {
  List<SexoSalida> obtenerSexo();
}
