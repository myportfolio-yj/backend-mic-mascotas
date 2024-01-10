package org.veterinaria.infraestructura.adaptador.entrada.Raza;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.veterinaria.aplicacion.puertos.entrada.Raza.IRazaResource;
import org.veterinaria.dominio.modelo.Raza.RazaEntrada;
import org.veterinaria.dominio.modelo.Raza.RazaSalida;
import org.veterinaria.dominio.servicio.Raza.IRazaServicio;

public class RazaResource implements IRazaResource {
  @Inject
  IRazaServicio servicio;

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
