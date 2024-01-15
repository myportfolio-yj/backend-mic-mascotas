package org.veterinaria.dominio.servicio.mascota;

import org.veterinaria.dominio.modelo.mascota.MascotaSalida;

import java.util.List;

public interface IObtenerMascotaServicio {
  List<MascotaSalida> obtenerMascota();
}
