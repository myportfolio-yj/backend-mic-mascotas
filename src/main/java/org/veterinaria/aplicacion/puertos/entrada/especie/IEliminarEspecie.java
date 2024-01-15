package org.veterinaria.aplicacion.puertos.entrada.especie;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/especie")
public interface IEliminarEspecie {
  @DELETE
  @Path("/{idEspecie}")
  @Produces(MediaType.APPLICATION_JSON)
  Response deleteEspecie(@PathParam("idEspecie") String idEspecie);
}
