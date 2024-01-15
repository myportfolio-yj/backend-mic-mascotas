package org.veterinaria.dominio.servicio.mascota;

import org.veterinaria.dominio.modelo.mascota.MascotaSalida;

public interface IEliminarMascotaServicio {
  MascotaSalida eliminarMascota(String idMascota);
}
