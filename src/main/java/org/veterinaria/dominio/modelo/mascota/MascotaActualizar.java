package org.veterinaria.dominio.modelo.mascota;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MascotaActualizar {
  private List<String> clientes;
  private String nombre;
  private String apellido;
  private String fechaNacimiento;
  private String idSexo;
  private String idEspecie;
  private String idRaza;
  private Boolean esterilizado;
  private List<String> alergias;
  private List<VacunaMascota> vacunas;
  private String foto;
}
