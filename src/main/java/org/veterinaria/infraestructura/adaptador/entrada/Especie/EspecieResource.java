package org.veterinaria.infraestructura.adaptador.entrada.Especie;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.veterinaria.aplicacion.puertos.entrada.Especie.IEspecieResource;
import org.veterinaria.dominio.modelo.Especie.EspecieEntrada;
import org.veterinaria.dominio.modelo.Especie.EspecieSalida;
import org.veterinaria.dominio.servicio.Especie.IEspecieServicio;

public class EspecieResource implements IEspecieResource {
  @Inject
  IEspecieServicio servicio;

  @Override
  public Response putEspecie(String idEspecie, EspecieEntrada especie) {
    EspecieSalida especieActualizado = servicio.actualizarEspecie(idEspecie, especie);
    return Response.ok(especieActualizado).build();
  }

  @Override
  public Response postEspecie(EspecieEntrada especie) {
    EspecieSalida especieCreado = servicio.crearEspecie(especie);
    return Response.status(Response.Status.CREATED).entity(especieCreado).build();
  }

  @Override
  public Response deleteEspecie(String idEspecie) {
    EspecieSalida especieEliminado = servicio.eliminarEspecie(idEspecie);
    return Response.status(Response.Status.CREATED).entity(especieEliminado).build();
  }

  @Override
  public Response getEspecie() {
    return Response.ok(servicio.obtenerEspecie()).build();
  }

  @Override
  public Response getEspeciePorId(String idEspecie) {
    return Response.ok(servicio.obtenerEspeciePorId(idEspecie)).build();
  }
}
