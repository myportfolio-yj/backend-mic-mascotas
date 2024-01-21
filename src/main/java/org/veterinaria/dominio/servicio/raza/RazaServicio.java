package org.veterinaria.dominio.servicio.raza;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.veterinaria.aplicacion.puertos.salida.alergia.IAlergiaRepositorio;
import org.veterinaria.aplicacion.puertos.salida.especie.IEspecieRepositorio;
import org.veterinaria.aplicacion.puertos.salida.raza.IRazaRepositorio;
import org.veterinaria.dominio.modelo.raza.RazaEntidad;
import org.veterinaria.dominio.modelo.raza.RazaEntrada;
import org.veterinaria.dominio.modelo.raza.RazaSalida;

import java.util.List;

@ApplicationScoped
public class RazaServicio implements IRazaServicio {
  private final IRazaRepositorio repositorio;
  private final IEspecieRepositorio repositorioEspecie;
  @Inject
  public RazaServicio(IRazaRepositorio repositorio, IEspecieRepositorio repositorioEspecie) {
    this.repositorio = repositorio;
    this.repositorioEspecie = repositorioEspecie;
  }
  @Override
  public RazaSalida actualizarRaza(String idRaza, RazaEntrada raza) {
    RazaEntidad razaEntidad = new RazaEntidad();
    razaEntidad.setRaza(raza.getRaza());
    razaEntidad.setIdEspecie(raza.getIdEspecie());
    repositorio.actualizarRaza(idRaza, razaEntidad);
    return this.obtenerRazaPorId(idRaza);
  }

  @Override
  public RazaSalida crearRaza(RazaEntrada raza) {
    RazaEntidad razaEntidad = new RazaEntidad();
    razaEntidad.setRaza(raza.getRaza());
    razaEntidad.setIdEspecie(raza.getIdEspecie());
    razaEntidad = repositorio.crearRaza(razaEntidad);
    return this.obtenerRazaPorId(razaEntidad.id.toString());
  }

  @Override
  public RazaSalida eliminarRaza(String idRaza) {
    RazaEntidad razaEntidad = repositorio.eliminarRaza(idRaza);
    return RazaSalida.builder()
          .id(razaEntidad.id.toString())
          .raza(razaEntidad.getRaza())
          .especie(repositorioEspecie.obtenerEspeciePorId(razaEntidad.getIdEspecie()).getEspecieClass())
          .build();
  }

  @Override
  public RazaSalida obtenerRazaPorId(String idRaza) {
    RazaEntidad razaEntidad = repositorio.obtenerRazaPorId(idRaza);
    return RazaSalida.builder()
          .id(razaEntidad.id.toString())
          .raza(razaEntidad.getRaza())
          .especie(
                repositorioEspecie.obtenerEspeciePorId(razaEntidad.getIdEspecie())
                      .getEspecieClass()
          )
          .build();
  }

  @Override
  public List<RazaSalida> obtenerRaza() {
    List<RazaEntidad> razas = repositorio.obtenerTodosRaza();
    return razas.parallelStream().map(razaEntidad -> RazaSalida.builder()
                .id(razaEntidad.id.toString())
                .raza(razaEntidad.getRaza())
                .especie(repositorioEspecie.obtenerEspeciePorId(razaEntidad.getIdEspecie()).getEspecieClass())
                .build())
          .toList();
  }
}