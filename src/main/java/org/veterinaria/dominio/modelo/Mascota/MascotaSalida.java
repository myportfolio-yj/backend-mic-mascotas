package org.veterinaria.dominio.modelo.Mascota;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.veterinaria.dominio.modelo.Alergia.AlergiaSalida;
import org.veterinaria.dominio.modelo.Especie.Especie;
import org.veterinaria.dominio.modelo.Raza.Raza;
import org.veterinaria.dominio.modelo.Recordatorio.Recordatorio;
import org.veterinaria.dominio.modelo.Sexo.SexoSalida;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@RegisterForReflection
public class MascotaSalida {
  private String id;
  private String codIdentificacion;
  private String nombre;
  private String apellido;
  private String fechaNacimiento;
  private SexoSalida sexo;
  private Especie especie;
  private Raza raza;
  private Boolean esterilizado;
  private List<AlergiaSalida> alergias;
  private List<VacunaMascotaSalida> vacunas;
  private List<String> clientes;
  private List<Recordatorio> recordatorios;
  private String foto;
  private String qr;
}

/**
 * import java.time.LocalDate;
 * import java.time.format.DateTimeFormatter;
 * <p>
 * public class MiEntidad {
 * // ...
 * private LocalDate fechaNacimiento;
 * // ...
 * <p>
 * public void setFechaNacimiento(String fechaNacimiento) {
 * DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
 * this.fechaNacimiento = LocalDate.parse(fechaNacimiento, formatter);
 * }
 * }
 * <p>
 * Cuando guardes una instancia de MiEntidad en MongoDB, la fecha de nacimiento se almacenará como un objeto Date de
 * BSON, que es el formato de fecha nativo de MongoDB.
 * <p>
 * Por favor, ten en cuenta que si estás en una zona horaria diferente a UTC, deberías tener cuidado al manejar las
 * fechas. Podrías considerar el uso de java.time.ZonedDateTime si necesitas tener en cuenta las zonas horarias.
 */