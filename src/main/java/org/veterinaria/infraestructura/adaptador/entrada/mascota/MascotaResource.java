package org.veterinaria.infraestructura.adaptador.entrada.mascota;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.Response;
import org.veterinaria.aplicacion.puertos.entrada.mascota.IMascotaResource;
import org.veterinaria.dominio.modelo.mascota.MascotaActualizar;
import org.veterinaria.dominio.modelo.mascota.MascotaCrear;
import org.veterinaria.dominio.modelo.mascota.MascotaSalida;
import org.veterinaria.dominio.servicio.formulario.IFormularioServicio;
import org.veterinaria.dominio.servicio.mascota.IMascotaServicio;

public class MascotaResource implements IMascotaResource {
  private final IMascotaServicio servicio;
  @Inject
  public MascotaResource(IMascotaServicio servicio) {
    this.servicio = servicio;
  }
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
