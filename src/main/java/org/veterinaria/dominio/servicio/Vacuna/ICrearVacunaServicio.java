package org.veterinaria.dominio.servicio.Vacuna;

import org.veterinaria.dominio.modelo.Vacuna.VacunaEntrada;
import org.veterinaria.dominio.modelo.Vacuna.VacunaSalida;

public interface ICrearVacunaServicio {
  VacunaSalida crearVacuna(VacunaEntrada vacuna);
}
