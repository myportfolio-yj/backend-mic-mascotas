package org.veterinaria.dominio.servicio.Vacuna;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.veterinaria.aplicacion.puertos.salida.Vacuna.IVacunaRepositorio;
import org.veterinaria.dominio.modelo.Vacuna.VacunaEntidad;
import org.veterinaria.dominio.modelo.Vacuna.VacunaEntrada;
import org.veterinaria.dominio.modelo.Vacuna.VacunaSalida;

import java.util.List;

@ApplicationScoped
public class VacunaServicio implements IVacunaServicio {
  @Inject
  IVacunaRepositorio repositorio;

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
    return this.obtenerVacunaPorId(vacunaEntidad.getId().toString());
  }

  @Override
  public VacunaSalida eliminarVacuna(String idVacuna) {
    VacunaEntidad vacunaEntidad = repositorio.eliminarVacuna(idVacuna);
    return VacunaSalida.builder()
          .id(vacunaEntidad.getId().toString())
          .vacuna(vacunaEntidad.getVacuna())
          .duracion(vacunaEntidad.convertirDuracionDesdeISO())
          .build();
  }

  @Override
  public VacunaSalida obtenerVacunaPorId(String idVacuna) {
    VacunaEntidad vacunaEntidad = repositorio.obtenerVacunaPorId(idVacuna);
    return VacunaSalida.builder()
          .id(vacunaEntidad.getId().toString())
          .vacuna(vacunaEntidad.getVacuna())
          .duracion(vacunaEntidad.convertirDuracionDesdeISO())
          .build();
  }

  @Override
  public List<VacunaSalida> obtenerVacuna() {
    List<VacunaEntidad> vacunas = repositorio.obtenerTodosVacuna();
    return vacunas.parallelStream().map(vacunaEntidad -> VacunaSalida.builder()
          .id(vacunaEntidad.getId().toString())
          .vacuna(vacunaEntidad.getVacuna())
          .duracion(vacunaEntidad.convertirDuracionDesdeISO())
          .build()
    ).toList();
  }
}