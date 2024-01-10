package org.veterinaria.aplicacion.puertos.entrada.Mascota;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/mascota")
public interface IEliminarMascota {
  @DELETE
  @Path("/{idMascota}")
  @Produces(MediaType.APPLICATION_JSON)
  Response deleteMascota(@PathParam("idMascota") String idMascota);
}
