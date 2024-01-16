package org.veterinaria.dominio.modelo.especie;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MongoEntity(collection = "collect-Especie")
public class EspecieEntidad extends PanacheMongoEntity {
  private String especie;
  private Boolean borrado;

  public EspecieMinSalida getEspecieClass() {
    return EspecieMinSalida.builder()
          .id(this.id.toString())
          .especie(this.especie)
          .build();
  }
}
