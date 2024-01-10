package org.veterinaria.dominio.servicio.Alergia;

import org.veterinaria.dominio.modelo.Alergia.AlergiaSalida;

import java.util.List;

public interface IObtenerAlergiaServicio {
  List<AlergiaSalida> obtenerAlergia();
}
