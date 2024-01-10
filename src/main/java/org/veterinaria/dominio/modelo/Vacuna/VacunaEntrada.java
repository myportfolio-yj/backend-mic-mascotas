package org.veterinaria.dominio.modelo.Vacuna;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VacunaEntrada {
  private String vacuna;
  private Long duracion;

  public String convertirDuracionISO() {
    return Duration.ofDays(this.duracion).toString();
  }
}