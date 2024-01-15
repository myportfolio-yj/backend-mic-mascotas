package org.veterinaria.dominio.servicio.mascota;

import org.veterinaria.dominio.modelo.mascota.MascotaCrear;
import org.veterinaria.dominio.modelo.mascota.MascotaSalida;

public interface ICrearMascotaServicio {
  MascotaSalida crearMascota(MascotaCrear mascota);
}
