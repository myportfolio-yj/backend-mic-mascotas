package org.veterinaria.aplicacion.puertos.entrada.Mascota;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.veterinaria.dominio.modelo.Mascota.MascotaCrear;

@Path("/mascota")
public interface ICrearMascota {
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response postMascota(MascotaCrear mascota);
}
