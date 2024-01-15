package org.veterinaria.dominio.servicio.especie;

import org.veterinaria.dominio.modelo.especie.EspecieSalida;

public interface IObtenerEspeciePorIdServicio {
  EspecieSalida obtenerEspeciePorId(String idEspecie);
}
