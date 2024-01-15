package org.veterinaria.aplicacion.puertos.entrada.raza;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.veterinaria.dominio.modelo.raza.RazaEntrada;

@Path("/raza")
public interface IActualizarRaza {
  @PUT
  @Path("/{idRaza}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response putRaza(@PathParam("idRaza") String idRaza, RazaEntrada raza);
}
