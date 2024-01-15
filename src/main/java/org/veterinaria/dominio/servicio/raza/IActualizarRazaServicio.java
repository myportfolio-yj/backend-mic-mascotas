package org.veterinaria.dominio.servicio.raza;

import org.veterinaria.dominio.modelo.raza.RazaEntrada;
import org.veterinaria.dominio.modelo.raza.RazaSalida;

public interface IActualizarRazaServicio {
  RazaSalida actualizarRaza(String idRaza, RazaEntrada raza);
}
