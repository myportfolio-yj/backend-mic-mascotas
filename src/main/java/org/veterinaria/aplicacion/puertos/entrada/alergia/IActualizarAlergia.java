package org.veterinaria.aplicacion.puertos.entrada.alergia;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.veterinaria.dominio.modelo.alergia.AlergiaEntrada;

@Path("/alergia")
public interface IActualizarAlergia {
  @PUT
  @Path("/{idAlergia}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response putAlergia(@PathParam("idAlergia") String idAlergia, AlergiaEntrada alergia);
}
