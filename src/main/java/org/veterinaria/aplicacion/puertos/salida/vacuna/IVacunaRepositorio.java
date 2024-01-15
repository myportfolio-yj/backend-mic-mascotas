package org.veterinaria.aplicacion.puertos.salida.vacuna;

import org.veterinaria.dominio.modelo.vacuna.VacunaEntidad;

import java.util.List;

public interface IVacunaRepositorio {
  List<VacunaEntidad> obtenerTodosVacuna();

  VacunaEntidad obtenerVacunaPorId(String idVacuna);

  VacunaEntidad crearVacuna(VacunaEntidad vacuna);

  VacunaEntidad actualizarVacuna(String idVacuna, VacunaEntidad vacuna);

  VacunaEntidad eliminarVacuna(String idVacuna);
}
