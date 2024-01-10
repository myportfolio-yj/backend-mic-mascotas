package org.veterinaria.dominio.servicio.Alergia;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.veterinaria.aplicacion.puertos.salida.Alergia.IAlergiaRepositorio;
import org.veterinaria.dominio.modelo.Alergia.AlergiaEntidad;
import org.veterinaria.dominio.modelo.Alergia.AlergiaEntrada;
import org.veterinaria.dominio.modelo.Alergia.AlergiaSalida;

import java.util.List;

@ApplicationScoped
public class AlergiaServicio implements IAlergiaServicio {
  @Inject
  IAlergiaRepositorio repositorio;

  @Override
  public AlergiaSalida actualizarAlergia(String idAlergia, AlergiaEntrada alergia) {
    AlergiaEntidad alergiaEntidad = new AlergiaEntidad();
    alergiaEntidad.setAlergia(alergia.getAlergia());
    repositorio.actualizarAlergia(idAlergia, alergiaEntidad);
    return this.obtenerAlergiaPorId(idAlergia);
  }

  @Override
  public AlergiaSalida crearAlergia(AlergiaEntrada alergia) {
    AlergiaEntidad alergiaEntidad = new AlergiaEntidad();
    alergiaEntidad.setAlergia(alergia.getAlergia());
    alergiaEntidad = repositorio.crearAlergia(alergiaEntidad);
    return this.obtenerAlergiaPorId(alergiaEntidad.getId().toString());
  }

  @Override
  public AlergiaSalida eliminarAlergia(String idAlergia) {
    AlergiaEntidad alergiaEntidad = repositorio.eliminarAlergia(idAlergia);
    return AlergiaSalida.builder()
          .id(alergiaEntidad.getId().toString())
          .alergia(alergiaEntidad.getAlergia())
          .build();
  }

  @Override
  public AlergiaSalida obtenerAlergiaPorId(String idAlergia) {
    AlergiaEntidad alergiaEntidad = repositorio.obtenerAlergiaPorId(idAlergia);
    return AlergiaSalida.builder()
          .id(alergiaEntidad.getId().toString())
          .alergia(alergiaEntidad.getAlergia())
          .build();
  }

  @Override
  public List<AlergiaSalida> obtenerAlergia() {
    List<AlergiaEntidad> alergias = repositorio.obtenerTodosAlergia();
    return alergias.parallelStream().map(alergiaEntidad -> AlergiaSalida.builder()
          .id(alergiaEntidad.getId().toString())
          .alergia(alergiaEntidad.getAlergia())
          .build()
    ).toList();
  }
}