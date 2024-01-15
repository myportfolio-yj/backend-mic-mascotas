package org.veterinaria.aplicacion.puertos.entrada.raza;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/raza")
public interface IEliminarRaza {
  @DELETE
  @Path("/{idRaza}")
  @Produces(MediaType.APPLICATION_JSON)
  Response deleteRaza(@PathParam("idRaza") String idRaza);
}
