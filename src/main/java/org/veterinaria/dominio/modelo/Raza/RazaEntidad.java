package org.veterinaria.dominio.modelo.Raza;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@MongoEntity(collection = "collect-Raza")
public class RazaEntidad extends PanacheMongoEntity {
  public ObjectId id;
  public Boolean borrado;
  private String idEspecie;
  private String raza;

  public Raza getRazaClass() {
    return Raza.builder()
          .id(this.id.toString())
          .raza(this.raza)
          .build();
  }
}
