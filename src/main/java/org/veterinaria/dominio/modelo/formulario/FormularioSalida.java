package org.veterinaria.dominio.modelo.formulario;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.veterinaria.dominio.modelo.alergia.AlergiaSalida;
import org.veterinaria.dominio.modelo.especie.EspecieSalida;
import org.veterinaria.dominio.modelo.sexo.SexoSalida;
import org.veterinaria.dominio.modelo.vacuna.VacunaSalida;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@RegisterForReflection
public class FormularioSalida {
  private List<SexoSalida> sexos;
  private List<EspecieSalida> especies;
  private List<AlergiaSalida> alergias;
  private List<VacunaSalida> vacunas;
}
