package org.veterinaria.dominio.modelo.vacuna;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@MongoEntity(collection = "collect-Vacuna")
public class VacunaEntidad extends PanacheMongoEntity {
  private String vacuna;
  private Boolean borrado;
  private String duracionISO;

  public long convertirDuracionDesdeISO() {
    return Duration.parse(this.duracionISO).toDays();
  }
}
