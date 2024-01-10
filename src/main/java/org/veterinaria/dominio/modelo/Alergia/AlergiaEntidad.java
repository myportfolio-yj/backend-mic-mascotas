package org.veterinaria.dominio.modelo.Alergia;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@MongoEntity(collection = "collect-Alergia")
public class AlergiaEntidad extends PanacheMongoEntity {
  public ObjectId id;
  public String alergia;
  public Boolean delete;
}
