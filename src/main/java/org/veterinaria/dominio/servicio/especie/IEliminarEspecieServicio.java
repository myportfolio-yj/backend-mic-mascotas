package org.veterinaria.dominio.servicio.especie;

import org.veterinaria.dominio.modelo.especie.EspecieSalida;

public interface IEliminarEspecieServicio {
  EspecieSalida eliminarEspecie(String idEspecie);
}
