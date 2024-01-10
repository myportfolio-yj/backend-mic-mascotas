package org.veterinaria.dominio.servicio.Vacuna;

import org.veterinaria.dominio.modelo.Vacuna.VacunaEntrada;
import org.veterinaria.dominio.modelo.Vacuna.VacunaSalida;

public interface IActualizarVacunaServicio {
  VacunaSalida actualizarVacuna(String idVacuna, VacunaEntrada vacuna);
}
