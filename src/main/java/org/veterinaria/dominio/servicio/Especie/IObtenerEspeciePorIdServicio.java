package org.veterinaria.dominio.servicio.Especie;

import org.veterinaria.dominio.modelo.Especie.EspecieSalida;

public interface IObtenerEspeciePorIdServicio {
  EspecieSalida obtenerEspeciePorId(String idEspecie);
}
