package org.veterinaria.infraestructura.adaptador.entrada.raza;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.veterinaria.aplicacion.puertos.entrada.raza.IRazaResource;
import org.veterinaria.dominio.modelo.raza.RazaEntrada;
import org.veterinaria.dominio.modelo.raza.RazaSalida;
import org.veterinaria.dominio.servicio.mascota.IMascotaServicio;
import org.veterinaria.dominio.servicio.raza.IRazaServicio;

public class RazaResource implements IRazaResource {
  private final IRazaServicio servicio;
  @Inject
  public RazaResource(IRazaServicio servicio) {
    this.servicio = servicio;
  }
  @Override
  public Response putRaza(String idRaza, RazaEntrada raza) {
    RazaSalida razaActualizado = servicio.actualizarRaza(idRaza, raza);
    return Response.ok(razaActualizado).build();
  }

  @Override
  public Response postRaza(RazaEntrada raza) {
    RazaSalida razaCreado = servicio.crearRaza(raza);
    return Response.status(Response.Status.CREATED).entity(razaCreado).build();
  }

  @Override
  public Response deleteRaza(String idRaza) {
    RazaSalida razaEliminado = servicio.eliminarRaza(idRaza);
    return Response.status(Response.Status.CREATED).entity(razaEliminado).build();
  }

  @Override
  public Response getRaza() {
    return Response.ok(servicio.obtenerRaza()).build();
  }

  @Override
  public Response getRazaPorId(String idRaza) {
    return Response.ok(servicio.obtenerRazaPorId(idRaza)).build();
  }
}
