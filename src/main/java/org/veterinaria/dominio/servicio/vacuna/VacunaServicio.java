package org.veterinaria.dominio.servicio.vacuna;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.veterinaria.aplicacion.puertos.salida.sexo.ISexoRepositorio;
import org.veterinaria.aplicacion.puertos.salida.vacuna.IVacunaRepositorio;
import org.veterinaria.dominio.modelo.vacuna.VacunaEntidad;
import org.veterinaria.dominio.modelo.vacuna.VacunaEntrada;
import org.veterinaria.dominio.modelo.vacuna.VacunaSalida;

import java.util.List;

@ApplicationScoped
public class VacunaServicio implements IVacunaServicio {
  private final IVacunaRepositorio repositorio;
  @Inject
  public VacunaServicio(IVacunaRepositorio repositorio) {
    this.repositorio = repositorio;
  }
  @Override
  public VacunaSalida actualizarVacuna(String idVacuna, VacunaEntrada vacuna) {
    VacunaEntidad vacunaEntidad = new VacunaEntidad();
    vacunaEntidad.setVacuna(vacuna.getVacuna());
    vacunaEntidad.setDuracionISO(vacuna.convertirDuracionISO());
    repositorio.actualizarVacuna(idVacuna, vacunaEntidad);
    return this.obtenerVacunaPorId(idVacuna);
  }

  @Override
  public VacunaSalida crearVacuna(VacunaEntrada vacuna) {
    VacunaEntidad vacunaEntidad = new VacunaEntidad();
    vacunaEntidad.setVacuna(vacuna.getVacuna());
    vacunaEntidad.setDuracionISO(vacuna.convertirDuracionISO());
    vacunaEntidad = repositorio.crearVacuna(vacunaEntidad);
    return this.obtenerVacunaPorId(vacunaEntidad.id.toString());
  }

  @Override
  public VacunaSalida eliminarVacuna(String idVacuna) {
    VacunaEntidad vacunaEntidad = repositorio.eliminarVacuna(idVacuna);
    return VacunaSalida.builder()
          .id(vacunaEntidad.id.toString())
          .vacuna(vacunaEntidad.getVacuna())
          .duracion(vacunaEntidad.convertirDuracionDesdeISO())
          .build();
  }

  @Override
  public VacunaSalida obtenerVacunaPorId(String idVacuna) {
    VacunaEntidad vacunaEntidad = repositorio.obtenerVacunaPorId(idVacuna);
    return VacunaSalida.builder()
          .id(vacunaEntidad.id.toString())
          .vacuna(vacunaEntidad.getVacuna())
          .duracion(vacunaEntidad.convertirDuracionDesdeISO())
          .build();
  }

  @Override
  public List<VacunaSalida> obtenerVacuna() {
    List<VacunaEntidad> vacunas = repositorio.obtenerTodosVacuna();
    return vacunas.parallelStream().map(vacunaEntidad -> VacunaSalida.builder()
          .id(vacunaEntidad.id.toString())
          .vacuna(vacunaEntidad.getVacuna())
          .duracion(vacunaEntidad.convertirDuracionDesdeISO())
          .build()
    ).toList();
  }
}