package org.veterinaria.dominio.servicio.Raza;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.veterinaria.aplicacion.puertos.salida.Especie.IEspecieRepositorio;
import org.veterinaria.aplicacion.puertos.salida.Raza.IRazaRepositorio;
import org.veterinaria.dominio.modelo.Raza.RazaEntidad;
import org.veterinaria.dominio.modelo.Raza.RazaEntrada;
import org.veterinaria.dominio.modelo.Raza.RazaSalida;

import java.util.List;

@ApplicationScoped
public class RazaServicio implements IRazaServicio {
  @Inject
  IRazaRepositorio repositorio;
  @Inject
  IEspecieRepositorio repositorioEspecie;

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
    return this.obtenerRazaPorId(razaEntidad.getId().toString());
  }

  @Override
  public RazaSalida eliminarRaza(String idRaza) {
    RazaEntidad razaEntidad = repositorio.eliminarRaza(idRaza);
    return RazaSalida.builder()
          .id(razaEntidad.getId().toString())
          .raza(razaEntidad.getRaza())
          .especie(repositorioEspecie.obtenerEspeciePorId(razaEntidad.getIdEspecie()).getEspecieClass())
          .build();
  }

  @Override
  public RazaSalida obtenerRazaPorId(String idRaza) {
    RazaEntidad razaEntidad = repositorio.obtenerRazaPorId(idRaza);
    return RazaSalida.builder()
          .id(razaEntidad.getId().toString())
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
                .id(razaEntidad.getId().toString())
                .raza(razaEntidad.getRaza())
                .especie(repositorioEspecie.obtenerEspeciePorId(razaEntidad.getIdEspecie()).getEspecieClass())
                .build())
          .toList();
  }
}