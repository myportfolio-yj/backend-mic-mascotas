package org.veterinaria.aplicacion.puertos.salida.mascota;

import org.veterinaria.dominio.modelo.mascota.MascotaEntidad;

import java.util.List;

public interface IMascotaRepositorio {
  List<MascotaEntidad> obtenerTodosMascota();

  MascotaEntidad obtenerMascotaPorId(String idMascota);

  MascotaEntidad crearMascota(MascotaEntidad mascota);

  MascotaEntidad actualizarMascota(String idMascota, MascotaEntidad mascota);

  MascotaEntidad eliminarMascota(String idMascota);
}
