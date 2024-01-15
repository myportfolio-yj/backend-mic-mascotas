package org.veterinaria.aplicacion.puertos.entrada.especie;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.veterinaria.dominio.modelo.especie.EspecieEntrada;

@Path("/especie")
public interface IActualizarEspecie {
  @PUT
  @Path("/{idEspecie}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response putEspecie(@PathParam("idEspecie") String idEspecie, EspecieEntrada especie);
}
