package org.veterinaria.dominio.servicio.Vacuna;

import org.veterinaria.dominio.modelo.Vacuna.VacunaSalida;

public interface IObtenerVacunaPorIdServicio {
  VacunaSalida obtenerVacunaPorId(String idVacuna);
}
