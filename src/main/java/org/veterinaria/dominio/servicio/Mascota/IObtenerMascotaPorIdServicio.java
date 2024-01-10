package org.veterinaria.dominio.servicio.Mascota;

import org.veterinaria.dominio.modelo.Mascota.MascotaSalida;

public interface IObtenerMascotaPorIdServicio {
  MascotaSalida obtenerMascotaPorId(String idMascota);
}
