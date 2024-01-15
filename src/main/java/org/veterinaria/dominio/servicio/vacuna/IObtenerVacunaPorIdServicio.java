package org.veterinaria.dominio.servicio.vacuna;

import org.veterinaria.dominio.modelo.vacuna.VacunaSalida;

public interface IObtenerVacunaPorIdServicio {
  VacunaSalida obtenerVacunaPorId(String idVacuna);
}
