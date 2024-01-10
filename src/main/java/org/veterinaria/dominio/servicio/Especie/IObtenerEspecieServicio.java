package org.veterinaria.dominio.servicio.Especie;

import org.veterinaria.dominio.modelo.Especie.EspecieSalida;

import java.util.List;

public interface IObtenerEspecieServicio {
  List<EspecieSalida> obtenerEspecie();
}
