package org.veterinaria.aplicacion.puertos.entrada.Vacuna;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.veterinaria.dominio.modelo.Vacuna.VacunaEntrada;

@Path("/vacuna")
public interface IActualizarVacuna {
  @PUT
  @Path("/{idVacuna}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response putVacuna(@PathParam("idVacuna") String idVacuna, VacunaEntrada vacuna);
}
