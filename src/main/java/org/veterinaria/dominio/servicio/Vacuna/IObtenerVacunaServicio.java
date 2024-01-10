package org.veterinaria.dominio.servicio.Vacuna;

import org.veterinaria.dominio.modelo.Vacuna.VacunaSalida;

import java.util.List;

public interface IObtenerVacunaServicio {
  List<VacunaSalida> obtenerVacuna();
}
