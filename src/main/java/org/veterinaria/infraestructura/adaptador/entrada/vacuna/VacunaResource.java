package org.veterinaria.infraestructura.adaptador.entrada.vacuna;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.Response;
import org.veterinaria.aplicacion.puertos.entrada.vacuna.IVacunaResource;
import org.veterinaria.dominio.modelo.vacuna.VacunaEntrada;
import org.veterinaria.dominio.modelo.vacuna.VacunaSalida;
import org.veterinaria.dominio.servicio.vacuna.IVacunaServicio;

public class VacunaResource implements IVacunaResource {
  @Inject
  IVacunaServicio servicio;

  @Override
  public Response putVacuna(@NotNull String idVacuna, @Valid VacunaEntrada vacuna) {
    VacunaSalida vacunaActualizado = servicio.actualizarVacuna(idVacuna, vacuna);
    return Response.ok(vacunaActualizado).build();
  }

  @Override
  public Response postVacuna(@Valid VacunaEntrada vacuna) {
    VacunaSalida vacunaCreado = servicio.crearVacuna(vacuna);
    return Response.status(Response.Status.CREATED).entity(vacunaCreado).build();
  }

  @Override
  public Response deleteVacuna(@NotNull String idVacuna) {
    VacunaSalida vacunaEliminado = servicio.eliminarVacuna(idVacuna);
    return Response.status(Response.Status.CREATED).entity(vacunaEliminado).build();
  }

  @Override
  public Response getVacuna() {
    return Response.ok(servicio.obtenerVacuna()).build();
  }

  @Override
  public Response getVacunaPorId(@NotNull String idVacuna) {
    return Response.ok(servicio.obtenerVacunaPorId(idVacuna)).build();
  }
}
