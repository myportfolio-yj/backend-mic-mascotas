package org.veterinaria.dominio.servicio.Alergia;

import org.veterinaria.dominio.modelo.Alergia.AlergiaEntrada;
import org.veterinaria.dominio.modelo.Alergia.AlergiaSalida;

public interface IActualizarAlergiaServicio {
  AlergiaSalida actualizarAlergia(String idAlergia, AlergiaEntrada alergia);
}
