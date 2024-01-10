package org.veterinaria.aplicacion.puertos.salida.Especie;

import org.veterinaria.dominio.modelo.Especie.EspecieEntidad;

import java.util.List;

public interface IEspecieRepositorio {
  List<EspecieEntidad> obtenerTodosEspecie();

  EspecieEntidad obtenerEspeciePorId(String idEspecie);

  EspecieEntidad crearEspecie(EspecieEntidad especie);

  EspecieEntidad actualizarEspecie(String idEspecie, EspecieEntidad especie);

  EspecieEntidad eliminarEspecie(String idEspecie);
}
