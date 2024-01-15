package org.veterinaria.aplicacion.puertos.salida.especie;

import org.veterinaria.dominio.modelo.especie.EspecieEntidad;

import java.util.List;

public interface IEspecieRepositorio {
  List<EspecieEntidad> obtenerTodosEspecie();

  EspecieEntidad obtenerEspeciePorId(String idEspecie);

  EspecieEntidad crearEspecie(EspecieEntidad especie);

  EspecieEntidad actualizarEspecie(String idEspecie, EspecieEntidad especie);

  EspecieEntidad eliminarEspecie(String idEspecie);
}
