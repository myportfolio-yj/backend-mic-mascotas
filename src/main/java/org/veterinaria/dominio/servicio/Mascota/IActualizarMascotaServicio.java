package org.veterinaria.dominio.servicio.Mascota;

import org.veterinaria.dominio.modelo.Mascota.MascotaActualizar;
import org.veterinaria.dominio.modelo.Mascota.MascotaSalida;

public interface IActualizarMascotaServicio {
  MascotaSalida actualizarMascota(String idMascota, MascotaActualizar mascota);
}
