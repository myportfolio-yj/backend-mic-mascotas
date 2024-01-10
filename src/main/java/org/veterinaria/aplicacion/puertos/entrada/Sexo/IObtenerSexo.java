package org.veterinaria.aplicacion.puertos.entrada.Sexo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/sexo")
public interface IObtenerSexo {
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  Response getSexo();
}
