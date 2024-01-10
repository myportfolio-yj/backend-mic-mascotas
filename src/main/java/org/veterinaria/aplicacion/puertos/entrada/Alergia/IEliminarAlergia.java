package org.veterinaria.aplicacion.puertos.entrada.Alergia;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/alergia")
public interface IEliminarAlergia {
  @DELETE
  @Path("/{idAlergia}")
  @Produces(MediaType.APPLICATION_JSON)
  Response deleteAlergia(@PathParam("idAlergia") String idAlergia);
}
