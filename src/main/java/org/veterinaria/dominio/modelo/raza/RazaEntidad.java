package org.veterinaria.dominio.modelo.raza;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MongoEntity(collection = "collect-Raza")
public class RazaEntidad extends PanacheMongoEntity {
  private Boolean borrado;
  private String idEspecie;
  private String raza;

  public RazaMinsalida getRazaClass() {
    StringBuilder cadenaFormateada = new StringBuilder();
    return RazaMinsalida.builder()
          .id(this.id.toString())
          .raza(
                cadenaFormateada
                      .append(this.raza.substring(0, 1).toUpperCase())
                      .append(this.raza.substring(1).toLowerCase())
                      .toString()
          )
          .build();
  }
}
