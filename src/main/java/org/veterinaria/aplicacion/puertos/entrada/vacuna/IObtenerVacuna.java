package org.veterinaria.aplicacion.puertos.entrada.vacuna;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/vacuna")
public interface IObtenerVacuna {
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  Response getVacuna();
}
