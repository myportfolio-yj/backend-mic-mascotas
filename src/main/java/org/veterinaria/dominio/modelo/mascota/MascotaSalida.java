package org.veterinaria.dominio.modelo.mascota;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.veterinaria.dominio.modelo.alergia.AlergiaSalida;
import org.veterinaria.dominio.modelo.especie.Especie;
import org.veterinaria.dominio.modelo.raza.Raza;
import org.veterinaria.dominio.modelo.recordatorio.Recordatorio;
import org.veterinaria.dominio.modelo.sexo.SexoSalida;

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