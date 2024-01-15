package org.veterinaria.dominio.servicio.especie;

import org.veterinaria.dominio.modelo.especie.EspecieEntrada;
import org.veterinaria.dominio.modelo.especie.EspecieSalida;

public interface ICrearEspecieServicio {
  EspecieSalida crearEspecie(EspecieEntrada especie);
}
