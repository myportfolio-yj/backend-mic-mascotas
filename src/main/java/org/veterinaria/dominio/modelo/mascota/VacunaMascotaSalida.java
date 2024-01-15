package org.veterinaria.dominio.modelo.mascota;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VacunaMascotaSalida {
  public static final String FORMATO_FECHA = "dd/MM/yyyy";

  private String idVacuna;
  private String fecha;
  private String vacuna;
}
