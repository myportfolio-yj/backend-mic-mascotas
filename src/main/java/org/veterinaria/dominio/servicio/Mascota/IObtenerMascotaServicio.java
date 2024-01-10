package org.veterinaria.dominio.servicio.Mascota;

import org.veterinaria.dominio.modelo.Mascota.MascotaSalida;

import java.util.List;

public interface IObtenerMascotaServicio {
  List<MascotaSalida> obtenerMascota();
}
