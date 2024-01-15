package org.veterinaria.dominio.servicio.especie;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.veterinaria.aplicacion.puertos.salida.especie.IEspecieRepositorio;
import org.veterinaria.aplicacion.puertos.salida.raza.IRazaRepositorio;
import org.veterinaria.dominio.modelo.especie.EspecieEntidad;
import org.veterinaria.dominio.modelo.especie.EspecieEntrada;
import org.veterinaria.dominio.modelo.especie.EspecieSalida;
import org.veterinaria.dominio.modelo.raza.RazaEntidad;

import java.util.List;

@ApplicationScoped
public class EspecieServicio implements IEspecieServicio {
  @Inject
  IEspecieRepositorio repositorio;
  @Inject
  IRazaRepositorio repositorioRaza;

  @Override
  public EspecieSalida actualizarEspecie(String idEspecie, EspecieEntrada especie) {
    EspecieEntidad especieEntidad = new EspecieEntidad();
    especieEntidad.setEspecie(especie.getEspecie());
    repositorio.actualizarEspecie(idEspecie, especieEntidad);
    return this.obtenerEspeciePorId(idEspecie);
  }

  @Override
  public EspecieSalida crearEspecie(EspecieEntrada especie) {
    EspecieEntidad especieEntidad = new EspecieEntidad();
    especieEntidad.setEspecie(especie.getEspecie());
    especieEntidad = repositorio.crearEspecie(especieEntidad);
    return this.obtenerEspeciePorId(especieEntidad.id.toString());
  }

  @Override
  public EspecieSalida eliminarEspecie(String idEspecie) {
    EspecieEntidad especieEntidad = repositorio.eliminarEspecie(idEspecie);
    return EspecieSalida.builder()
          .id(especieEntidad.id.toString())
          .especie(especieEntidad.getEspecie())
          .razas(
                repositorioRaza.findByEspecie(especieEntidad.id.toString())
                      .parallelStream()
                      .map(RazaEntidad::getRazaClass)
                      .toList())
          .build();
  }

  @Override
  public EspecieSalida obtenerEspeciePorId(String idEspecie) {
    EspecieEntidad especieEntidad = repositorio.obtenerEspeciePorId(idEspecie);
    return EspecieSalida.builder()
          .id(especieEntidad.id.toString())
          .especie(especieEntidad.getEspecie())
          .razas(
                repositorioRaza.findByEspecie(especieEntidad.id.toString())
                      .parallelStream()
                      .map(RazaEntidad::getRazaClass)
                      .toList())
          .build();
  }

  @Override
  public List<EspecieSalida> obtenerEspecie() {
    List<EspecieEntidad> especies = repositorio.obtenerTodosEspecie();
    return especies.parallelStream().map(especieEntidad -> EspecieSalida.builder()
          .id(especieEntidad.id.toString())
          .especie(especieEntidad.getEspecie())
          .razas(
                repositorioRaza.findByEspecie(especieEntidad.id.toString())
                      .parallelStream()
                      .map(RazaEntidad::getRazaClass)
                      .toList())
          .build()).toList();
  }
}