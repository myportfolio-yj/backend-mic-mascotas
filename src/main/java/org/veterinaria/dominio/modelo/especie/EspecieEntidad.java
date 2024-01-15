package org.veterinaria.dominio.modelo.especie;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@MongoEntity(collection = "collect-Especie")
public class EspecieEntidad extends PanacheMongoEntity {
  public String especie;
  public Boolean borrado;

  public Especie getEspecieClass() {
    return Especie.builder()
          .id(this.id.toString())
          .especie(this.especie)
          .build();
  }
}
