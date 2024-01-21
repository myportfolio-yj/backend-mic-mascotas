package org.veterinaria.dominio.modelo.sexo;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MongoEntity(collection = "collect-Sexo")
public class SexoEntidad extends PanacheMongoEntity {
  private String sexo;
  private Boolean borrado;
}
