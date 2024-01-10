package org.veterinaria.dominio.modelo.Vacuna;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.Duration;

@Getter
@Setter
@MongoEntity(collection = "collect-Vacuna")
public class VacunaEntidad extends PanacheMongoEntity {
  public ObjectId id;
  public String vacuna;
  public Boolean borrado;
  private String duracionISO;

  public long convertirDuracionDesdeISO() {
    return Duration.parse(this.duracionISO).toDays();
  }
}
