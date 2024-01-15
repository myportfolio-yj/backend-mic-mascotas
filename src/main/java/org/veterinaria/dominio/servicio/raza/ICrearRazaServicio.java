package org.veterinaria.dominio.servicio.raza;

import org.veterinaria.dominio.modelo.raza.RazaEntrada;
import org.veterinaria.dominio.modelo.raza.RazaSalida;

public interface ICrearRazaServicio {
  RazaSalida crearRaza(RazaEntrada raza);
}
