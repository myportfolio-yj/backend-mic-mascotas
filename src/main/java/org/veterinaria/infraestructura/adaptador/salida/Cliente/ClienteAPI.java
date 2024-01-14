package org.veterinaria.infraestructura.adaptador.salida.Cliente;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.veterinaria.dominio.modelo.Cliente.Cliente;

import java.util.List;

@Path("/")
@RegisterRestClient
public interface ClienteAPI {
  @GET
  @Path("/cliente/{idCliente}")
  @Produces(MediaType.APPLICATION_JSON)
  Cliente getClientePorId(@PathParam("idCliente") String idCliente);

  @POST
  @Path("/cliente-mascota/{idCliente}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response postMascotaCliente(@PathParam("idCliente") String idCliente, List<String> idMascota);
}
