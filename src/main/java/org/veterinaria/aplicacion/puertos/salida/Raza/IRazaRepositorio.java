package org.veterinaria.aplicacion.puertos.salida.Raza;

import org.veterinaria.dominio.modelo.Raza.RazaEntidad;

import java.util.List;

public interface IRazaRepositorio {
  List<RazaEntidad> obtenerTodosRaza();

  RazaEntidad obtenerRazaPorId(String idRaza);

  RazaEntidad crearRaza(RazaEntidad raza);

  RazaEntidad actualizarRaza(String idRaza, RazaEntidad raza);

  RazaEntidad eliminarRaza(String idRaza);

  List<RazaEntidad> findByEspecie(String idEspecie);
}
