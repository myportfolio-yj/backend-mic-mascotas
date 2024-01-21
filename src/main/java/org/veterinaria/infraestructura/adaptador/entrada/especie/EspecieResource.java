package org.veterinaria.infraestructura.adaptador.entrada.especie;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.veterinaria.aplicacion.puertos.entrada.especie.IEspecieResource;
import org.veterinaria.dominio.modelo.especie.EspecieEntrada;
import org.veterinaria.dominio.modelo.especie.EspecieSalida;
import org.veterinaria.dominio.servicio.alergia.IAlergiaServicio;
import org.veterinaria.dominio.servicio.especie.IEspecieServicio;

public class EspecieResource implements IEspecieResource {
  private final IEspecieServicio servicio;
  @Inject
  public EspecieResource(IEspecieServicio servicio) {
    this.servicio = servicio;
  }
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
