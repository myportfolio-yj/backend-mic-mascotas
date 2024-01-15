package org.veterinaria.dominio.servicio.mascota;

import org.veterinaria.dominio.modelo.mascota.MascotaActualizar;
import org.veterinaria.dominio.modelo.mascota.MascotaSalida;

public interface IActualizarMascotaServicio {
  MascotaSalida actualizarMascota(String idMascota, MascotaActualizar mascota);
}
