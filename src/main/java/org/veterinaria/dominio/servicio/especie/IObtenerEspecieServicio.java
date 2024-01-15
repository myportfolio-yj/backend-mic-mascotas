package org.veterinaria.dominio.servicio.especie;

import org.veterinaria.dominio.modelo.especie.EspecieSalida;

import java.util.List;

public interface IObtenerEspecieServicio {
  List<EspecieSalida> obtenerEspecie();
}
