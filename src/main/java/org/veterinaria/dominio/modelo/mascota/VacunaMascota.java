package org.veterinaria.dominio.modelo.mascota;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VacunaMascota {
  public static final String FORMATO_FECHA = "dd/MM/yyyy";

  private String idVacuna;
  private String fecha;

  public Date convertirFecha() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);
    LocalDate fechaLocal = LocalDate.parse(this.fecha, formatter);
    return Date.from(fechaLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }

  public boolean fechaInvalida() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);
    try {
      LocalDate fechaLocal = LocalDate.parse(this.fecha, formatter);
      return fechaLocal.isAfter(LocalDate.now());
    } catch (DateTimeParseException e) {
      return true;
    }
  }
}
