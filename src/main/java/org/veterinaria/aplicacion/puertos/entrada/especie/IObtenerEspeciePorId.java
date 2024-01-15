package org.veterinaria.aplicacion.puertos.entrada.especie;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/especie")
public interface IObtenerEspeciePorId {
  @GET
  @Path("/{idEspecie}")
  @Produces(MediaType.APPLICATION_JSON)
  Response getEspeciePorId(@PathParam("idEspecie") String idEspecie);
}
