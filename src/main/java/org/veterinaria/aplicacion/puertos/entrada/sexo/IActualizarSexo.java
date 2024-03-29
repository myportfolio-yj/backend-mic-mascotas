package org.veterinaria.aplicacion.puertos.entrada.sexo;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.veterinaria.dominio.modelo.sexo.SexoEntrada;

@Path("/sexo")
public interface IActualizarSexo {
  @PUT
  @Path("/{idSexo}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response putSexo(@PathParam("idSexo") String idSexo, SexoEntrada sexo);
}
