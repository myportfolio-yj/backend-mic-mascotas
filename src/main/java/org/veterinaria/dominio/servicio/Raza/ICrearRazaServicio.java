package org.veterinaria.dominio.servicio.Raza;

import org.veterinaria.dominio.modelo.Raza.RazaEntrada;
import org.veterinaria.dominio.modelo.Raza.RazaSalida;

public interface ICrearRazaServicio {
  RazaSalida crearRaza(RazaEntrada raza);
}
