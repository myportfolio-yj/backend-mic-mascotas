package org.veterinaria.dominio.servicio.vacuna;

import org.veterinaria.dominio.modelo.vacuna.VacunaSalida;

import java.util.List;

public interface IObtenerVacunaServicio {
  List<VacunaSalida> obtenerVacuna();
}
