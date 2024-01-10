package org.veterinaria.dominio.servicio.Mascota;

import org.veterinaria.dominio.modelo.Mascota.MascotaCrear;
import org.veterinaria.dominio.modelo.Mascota.MascotaSalida;

public interface ICrearMascotaServicio {
  MascotaSalida crearMascota(MascotaCrear mascota);
}
