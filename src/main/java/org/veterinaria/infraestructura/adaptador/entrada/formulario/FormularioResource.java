package org.veterinaria.infraestructura.adaptador.entrada.formulario;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.veterinaria.aplicacion.puertos.entrada.formulario.IFormularioResource;
import org.veterinaria.dominio.servicio.formulario.IFormularioServicio;

public class FormularioResource implements IFormularioResource {
  @Inject
  IFormularioServicio servicio;

  @Override
  public Response getFormulario() {
    return Response.ok(servicio.obtenerFormulario()).build();
  }
}
