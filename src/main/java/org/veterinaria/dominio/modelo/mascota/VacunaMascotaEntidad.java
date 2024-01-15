package org.veterinaria.dominio.modelo.mascota;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VacunaMascotaEntidad {
  public static final String FORMATO_FECHA = "dd/MM/yyyy";

  private String idVacuna;
  private Date fecha;

  public String convertirFechaAString() {
    SimpleDateFormat formatter = new SimpleDateFormat(FORMATO_FECHA);
    return formatter.format(this.fecha);
  }
}
