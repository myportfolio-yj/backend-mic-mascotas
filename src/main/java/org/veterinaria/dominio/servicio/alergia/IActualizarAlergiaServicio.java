package org.veterinaria.dominio.servicio.alergia;

import org.veterinaria.dominio.modelo.alergia.AlergiaEntrada;
import org.veterinaria.dominio.modelo.alergia.AlergiaSalida;

public interface IActualizarAlergiaServicio {
  AlergiaSalida actualizarAlergia(String idAlergia, AlergiaEntrada alergia);
}
