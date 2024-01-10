package org.veterinaria.aplicacion.puertos.entrada.Formulario;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/mascota/formulario")
public interface IFormularioMascota {
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  Response getFormulario();
}
