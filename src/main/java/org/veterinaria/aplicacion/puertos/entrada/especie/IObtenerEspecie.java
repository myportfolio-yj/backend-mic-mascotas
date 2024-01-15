package org.veterinaria.aplicacion.puertos.entrada.especie;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/especie")
public interface IObtenerEspecie {
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  Response getEspecie();
}
