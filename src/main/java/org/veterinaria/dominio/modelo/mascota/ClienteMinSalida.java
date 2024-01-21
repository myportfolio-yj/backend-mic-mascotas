package org.veterinaria.dominio.modelo.mascota;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.veterinaria.dominio.modelo.cliente.TipoDocumentoSalida;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@RegisterForReflection
public class ClienteMinSalida {
  private String id;
  private String nombres;
  private String apellidos;
  private String celular;
  private String fijo;
  private String email;
  private TipoDocumentoSalida tipoDocumento;
  private String documento;
}
