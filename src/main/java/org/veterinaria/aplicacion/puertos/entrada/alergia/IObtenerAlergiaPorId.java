package org.veterinaria.aplicacion.puertos.entrada.alergia;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/alergia")
public interface IObtenerAlergiaPorId {
  @GET
  @Path("/{idAlergia}")
  @Produces(MediaType.APPLICATION_JSON)
  Response getAlergiaPorId(@PathParam("idAlergia") String idAlergia);
}
