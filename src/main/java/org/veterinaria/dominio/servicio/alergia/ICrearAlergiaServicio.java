package org.veterinaria.dominio.servicio.alergia;

import org.veterinaria.dominio.modelo.alergia.AlergiaEntrada;
import org.veterinaria.dominio.modelo.alergia.AlergiaSalida;

public interface ICrearAlergiaServicio {
  AlergiaSalida crearAlergia(AlergiaEntrada alergia);
}
