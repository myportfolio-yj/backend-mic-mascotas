package org.veterinaria.dominio.servicio.mascota;

import org.veterinaria.dominio.modelo.mascota.MascotaSalida;

public interface IObtenerMascotaPorIdServicio {
  MascotaSalida obtenerMascotaPorId(String idMascota);
}
