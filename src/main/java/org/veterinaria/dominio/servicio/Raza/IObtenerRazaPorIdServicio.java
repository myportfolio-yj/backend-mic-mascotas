package org.veterinaria.dominio.servicio.Raza;

import org.veterinaria.dominio.modelo.Raza.RazaSalida;

public interface IObtenerRazaPorIdServicio {
  RazaSalida obtenerRazaPorId(String idRaza);
}
