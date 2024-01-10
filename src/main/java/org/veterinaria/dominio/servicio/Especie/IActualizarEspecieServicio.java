package org.veterinaria.dominio.servicio.Especie;

import org.veterinaria.dominio.modelo.Especie.EspecieEntrada;
import org.veterinaria.dominio.modelo.Especie.EspecieSalida;

public interface IActualizarEspecieServicio {
  EspecieSalida actualizarEspecie(String idEspecie, EspecieEntrada especie);
}
