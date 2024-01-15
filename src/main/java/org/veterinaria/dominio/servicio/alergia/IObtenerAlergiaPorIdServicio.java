package org.veterinaria.dominio.servicio.alergia;

import org.veterinaria.dominio.modelo.alergia.AlergiaSalida;

public interface IObtenerAlergiaPorIdServicio {
  AlergiaSalida obtenerAlergiaPorId(String idAlergia);
}
