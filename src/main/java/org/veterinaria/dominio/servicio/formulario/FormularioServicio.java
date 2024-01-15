package org.veterinaria.dominio.servicio.formulario;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.veterinaria.dominio.modelo.formulario.FormularioSalida;
import org.veterinaria.dominio.servicio.alergia.IAlergiaServicio;
import org.veterinaria.dominio.servicio.especie.IEspecieServicio;
import org.veterinaria.dominio.servicio.sexo.ISexoServicio;
import org.veterinaria.dominio.servicio.vacuna.IVacunaServicio;

@ApplicationScoped
public class FormularioServicio implements IFormularioServicio {
  @Inject
  ISexoServicio servicioSexo;
  @Inject
  IEspecieServicio servicioEspecie;
  @Inject
  IAlergiaServicio servicioAlergias;
  @Inject
  IVacunaServicio servicioVacunas;

  @Override
  public FormularioSalida obtenerFormulario() {
    return FormularioSalida.builder()
          .sexos(servicioSexo.obtenerSexo())
          .especies(servicioEspecie.obtenerEspecie())
          .alergias(servicioAlergias.obtenerAlergia())
          .vacunas(servicioVacunas.obtenerVacuna())
          .build();
  }
}