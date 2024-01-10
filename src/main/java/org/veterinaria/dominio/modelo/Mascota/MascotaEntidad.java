package org.veterinaria.dominio.modelo.Mascota;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
@MongoEntity(collection = "collect-Mascota")
public class MascotaEntidad extends PanacheMongoEntity {
  public ObjectId id;
  public String codIdentificacion;
  public String nombre;
  public String apellido;
  public String fechaNacimiento;
  public String idSexo;
  public String idEspecie;
  public String idRaza;
  public Boolean esterilizado;
  public List<String> alergias;
  public List<String> vacunas;
  public String foto;
  public String qr;
  private Boolean borrado;
}
