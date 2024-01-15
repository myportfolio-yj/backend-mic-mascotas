package org.veterinaria.aplicacion.puertos.entrada.mascota;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.veterinaria.dominio.modelo.mascota.MascotaActualizar;

@Path("/mascota")
public interface IActualizarMascota {
  @PUT
  @Path("/{idMascota}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response putMascota(@PathParam("idMascota") String idMascota, MascotaActualizar mascota);
}
