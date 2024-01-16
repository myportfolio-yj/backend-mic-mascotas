package org.veterinaria.dominio.modelo.raza;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MongoEntity(collection = "collect-Raza")
public class RazaEntidad extends PanacheMongoEntity {
  public Boolean borrado;
  private String idEspecie;
  private String raza;

  public RazaMinsalida getRazaClass() {
    return RazaMinsalida.builder()
          .id(this.id.toString())
          .raza(this.raza)
          .build();
  }
}
