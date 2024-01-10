package org.veterinaria.dominio.modelo.Formulario;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.veterinaria.dominio.modelo.Alergia.AlergiaSalida;
import org.veterinaria.dominio.modelo.Especie.EspecieSalida;
import org.veterinaria.dominio.modelo.Sexo.SexoSalida;
import org.veterinaria.dominio.modelo.Vacuna.VacunaSalida;

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
