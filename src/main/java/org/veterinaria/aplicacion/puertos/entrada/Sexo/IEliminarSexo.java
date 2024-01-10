package org.veterinaria.aplicacion.puertos.entrada.Sexo;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/sexo")
public interface IEliminarSexo {
  @DELETE
  @Path("/{idSexo}")
  @Produces(MediaType.APPLICATION_JSON)
  Response deleteSexo(@PathParam("idSexo") String idSexo);
}
