package org.veterinaria.dominio.servicio.vacuna;

import org.veterinaria.dominio.modelo.vacuna.VacunaEntrada;
import org.veterinaria.dominio.modelo.vacuna.VacunaSalida;

public interface IActualizarVacunaServicio {
  VacunaSalida actualizarVacuna(String idVacuna, VacunaEntrada vacuna);
}
