package org.veterinaria.dominio.servicio.alergia;

import org.veterinaria.dominio.modelo.alergia.AlergiaSalida;

public interface IEliminarAlergiaServicio {
  AlergiaSalida eliminarAlergia(String idAlergia);
}
