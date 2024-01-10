package org.veterinaria.infraestructura.adaptador.entrada.Sexo;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.Response;
import org.veterinaria.aplicacion.puertos.entrada.Sexo.ISexoResource;
import org.veterinaria.dominio.modelo.Sexo.SexoEntrada;
import org.veterinaria.dominio.modelo.Sexo.SexoSalida;
import org.veterinaria.dominio.servicio.Sexo.ISexoServicio;

public class SexoResource implements ISexoResource {
  @Inject
  ISexoServicio servicio;

  @Override
  public Response putSexo(@NotNull String idSexo, @Valid SexoEntrada sexo) {
    SexoSalida sexoActualizado = servicio.actualizarSexo(idSexo, sexo);
    return Response.ok(sexoActualizado).build();
  }

  @Override
  public Response postSexo(@Valid SexoEntrada sexo) {
    SexoSalida sexoCreado = servicio.crearSexo(sexo);
    return Response.status(Response.Status.CREATED).entity(sexoCreado).build();
  }

  @Override
  public Response deleteSexo(@NotNull String idSexo) {
    SexoSalida sexoEliminado = servicio.eliminarSexo(idSexo);
    return Response.status(Response.Status.CREATED).entity(sexoEliminado).build();
  }

  @Override
  public Response getSexo() {
    return Response.ok(servicio.obtenerSexo()).build();
  }

  @Override
  public Response getSexoPorId(@NotNull String idSexo) {
    return Response.ok(servicio.obtenerSexoPorId(idSexo)).build();
  }
}
