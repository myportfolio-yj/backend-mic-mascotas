package org.veterinaria.infraestructura.adaptador.entrada.Mascota;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.Response;
import org.veterinaria.aplicacion.puertos.entrada.Mascota.IMascotaResource;
import org.veterinaria.dominio.modelo.Mascota.MascotaActualizar;
import org.veterinaria.dominio.modelo.Mascota.MascotaCrear;
import org.veterinaria.dominio.modelo.Mascota.MascotaSalida;
import org.veterinaria.dominio.servicio.Mascota.IMascotaServicio;

public class MascotaResource implements IMascotaResource {
  @Inject
  IMascotaServicio servicio;

  @Override
  public Response putMascota(@NotNull String idMascota, @Valid MascotaActualizar mascota) {
    MascotaSalida mascotaActualizado = servicio.actualizarMascota(idMascota, mascota);
    return Response.ok(mascotaActualizado).build();
  }

  @Override
  public Response postMascota(MascotaCrear mascota) {
    MascotaSalida mascotaCreado = servicio.crearMascota(mascota);
    return Response.status(Response.Status.CREATED).entity(mascotaCreado).build();
  }

  @Override
  public Response deleteMascota(@NotNull String idMascota) {
    MascotaSalida mascotaEliminado = servicio.eliminarMascota(idMascota);
    return Response.status(Response.Status.CREATED).entity(mascotaEliminado).build();
  }

  @Override
  public Response getMascota() {
    return Response.ok(servicio.obtenerMascota()).build();
  }

  @Override
  public Response getMascotaPorId(@NotNull String idMascota) {
    return Response.ok(servicio.obtenerMascotaPorId(idMascota)).build();
  }
}
