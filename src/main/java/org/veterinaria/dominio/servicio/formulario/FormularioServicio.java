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
  private final ISexoServicio servicioSexo;
  private final IEspecieServicio servicioEspecie;
  private final IAlergiaServicio servicioAlergias;
  private final IVacunaServicio servicioVacunas;

  @Inject
  public FormularioServicio(ISexoServicio servicioSexo, IEspecieServicio servicioEspecie, IAlergiaServicio servicioAlergias, IVacunaServicio servicioVacunas) {
    this.servicioSexo = servicioSexo;
    this.servicioEspecie = servicioEspecie;
    this.servicioAlergias = servicioAlergias;
    this.servicioVacunas = servicioVacunas;
  }

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