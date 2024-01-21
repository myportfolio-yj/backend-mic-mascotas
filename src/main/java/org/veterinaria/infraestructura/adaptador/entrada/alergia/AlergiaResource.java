package org.veterinaria.infraestructura.adaptador.entrada.alergia;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.Response;
import org.veterinaria.aplicacion.puertos.entrada.alergia.IAlergiaResource;
import org.veterinaria.aplicacion.puertos.salida.alergia.IAlergiaRepositorio;
import org.veterinaria.dominio.modelo.alergia.AlergiaEntrada;
import org.veterinaria.dominio.modelo.alergia.AlergiaSalida;
import org.veterinaria.dominio.servicio.alergia.IAlergiaServicio;

public class AlergiaResource implements IAlergiaResource {
  private final IAlergiaServicio servicio;
  @Inject
  public AlergiaResource(IAlergiaServicio servicio) {
    this.servicio = servicio;
  }

  @Override
  public Response putAlergia(@NotNull String idAlergia, @Valid AlergiaEntrada alergia) {
    AlergiaSalida alergiaActualizado = servicio.actualizarAlergia(idAlergia, alergia);
    return Response.ok(alergiaActualizado).build();
  }

  @Override
  public Response postAlergia(@Valid AlergiaEntrada alergia) {
    AlergiaSalida alergiaCreado = servicio.crearAlergia(alergia);
    return Response.status(Response.Status.CREATED).entity(alergiaCreado).build();
  }

  @Override
  public Response deleteAlergia(@NotNull String idAlergia) {
    AlergiaSalida alergiaEliminado = servicio.eliminarAlergia(idAlergia);
    return Response.status(Response.Status.CREATED).entity(alergiaEliminado).build();
  }

  @Override
  public Response getAlergia() {
    return Response.ok(servicio.obtenerAlergia()).build();
  }

  @Override
  public Response getAlergiaPorId(@NotNull String idAlergia) {
    return Response.ok(servicio.obtenerAlergiaPorId(idAlergia)).build();
  }
}
