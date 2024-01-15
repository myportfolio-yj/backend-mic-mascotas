package org.veterinaria.dominio.servicio.raza;

import org.veterinaria.dominio.modelo.raza.RazaSalida;

public interface IObtenerRazaPorIdServicio {
  RazaSalida obtenerRazaPorId(String idRaza);
}
