package org.veterinaria.dominio.servicio.Alergia;

import org.veterinaria.dominio.modelo.Alergia.AlergiaSalida;

public interface IObtenerAlergiaPorIdServicio {
  AlergiaSalida obtenerAlergiaPorId(String idAlergia);
}
