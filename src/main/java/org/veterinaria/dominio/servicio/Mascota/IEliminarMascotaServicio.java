package org.veterinaria.dominio.servicio.Mascota;

import org.veterinaria.dominio.modelo.Mascota.MascotaSalida;

public interface IEliminarMascotaServicio {
  MascotaSalida eliminarMascota(String idMascota);
}
