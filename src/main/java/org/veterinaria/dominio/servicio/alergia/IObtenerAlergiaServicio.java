package org.veterinaria.dominio.servicio.alergia;

import org.veterinaria.dominio.modelo.alergia.AlergiaSalida;

import java.util.List;

public interface IObtenerAlergiaServicio {
  List<AlergiaSalida> obtenerAlergia();
}
