package org.veterinaria.dominio.servicio.Formulario;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.veterinaria.dominio.modelo.Formulario.FormularioSalida;
import org.veterinaria.dominio.servicio.Alergia.IAlergiaServicio;
import org.veterinaria.dominio.servicio.Especie.IEspecieServicio;
import org.veterinaria.dominio.servicio.Sexo.ISexoServicio;
import org.veterinaria.dominio.servicio.Vacuna.IVacunaServicio;

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