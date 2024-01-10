package org.veterinaria.dominio.modelo.Sexo;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@MongoEntity(collection = "collect-Sexo")
public class SexoEntidad extends PanacheMongoEntity {
  public ObjectId id;
  private String sexo;
  private Boolean borrado;
}
