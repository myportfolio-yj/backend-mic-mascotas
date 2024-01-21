package org.veterinaria.infraestructura.adaptador.entrada.formulario;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.veterinaria.aplicacion.puertos.entrada.formulario.IFormularioResource;
import org.veterinaria.dominio.servicio.especie.IEspecieServicio;
import org.veterinaria.dominio.servicio.formulario.IFormularioServicio;

public class FormularioResource implements IFormularioResource {
  private final IFormularioServicio servicio;
  @Inject
  public FormularioResource(IFormularioServicio servicio) {
    this.servicio = servicio;
  }
  @Override
  public Response getFormulario() {
    return Response.ok(servicio.obtenerFormulario()).build();
  }
}
