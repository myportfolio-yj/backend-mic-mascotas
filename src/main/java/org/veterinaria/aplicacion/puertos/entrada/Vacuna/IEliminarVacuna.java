package org.veterinaria.aplicacion.puertos.entrada.Vacuna;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/vacuna")
public interface IEliminarVacuna {
  @DELETE
  @Path("/{idVacuna}")
  @Produces(MediaType.APPLICATION_JSON)
  Response deleteVacuna(@PathParam("idVacuna") String idVacuna);
}
