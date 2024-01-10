package org.veterinaria.aplicacion.puertos.entrada.Raza;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/raza")
public interface IObtenerRaza {
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  Response getRaza();
}
