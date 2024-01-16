package org.veterinaria.dominio.modelo.alergia;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@MongoEntity(collection = "collect-Alergia")
public class AlergiaEntidad extends PanacheMongoEntity {
  private String alergia;
  private Boolean delete;
}
