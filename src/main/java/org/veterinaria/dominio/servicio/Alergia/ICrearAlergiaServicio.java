package org.veterinaria.dominio.servicio.Alergia;

import org.veterinaria.dominio.modelo.Alergia.AlergiaEntrada;
import org.veterinaria.dominio.modelo.Alergia.AlergiaSalida;

public interface ICrearAlergiaServicio {
  AlergiaSalida crearAlergia(AlergiaEntrada alergia);
}
