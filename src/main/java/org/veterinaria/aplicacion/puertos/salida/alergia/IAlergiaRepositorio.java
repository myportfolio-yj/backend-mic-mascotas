package org.veterinaria.aplicacion.puertos.salida.alergia;

import org.veterinaria.dominio.modelo.alergia.AlergiaEntidad;

import java.util.List;

public interface IAlergiaRepositorio {
  List<AlergiaEntidad> obtenerTodosAlergia();

  AlergiaEntidad obtenerAlergiaPorId(String idAlergia);

  AlergiaEntidad crearAlergia(AlergiaEntidad alergia);

  AlergiaEntidad actualizarAlergia(String idAlergia, AlergiaEntidad alergia);

  AlergiaEntidad eliminarAlergia(String idAlergia);
}
