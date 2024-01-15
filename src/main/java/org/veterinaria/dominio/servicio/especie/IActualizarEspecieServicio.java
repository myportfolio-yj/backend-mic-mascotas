package org.veterinaria.dominio.servicio.especie;

import org.veterinaria.dominio.modelo.especie.EspecieEntrada;
import org.veterinaria.dominio.modelo.especie.EspecieSalida;

public interface IActualizarEspecieServicio {
  EspecieSalida actualizarEspecie(String idEspecie, EspecieEntrada especie);
}
