package org.veterinaria.aplicacion.puertos.entrada.Raza;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/raza")
public interface IObtenerRazaPorId {
  @GET
  @Path("/{idRaza}")
  @Produces(MediaType.APPLICATION_JSON)
  Response getRazaPorId(@PathParam("idRaza") String idRaza);
}
