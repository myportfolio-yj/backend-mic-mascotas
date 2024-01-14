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
  private String fechaNacimiento;
  private String idSexo;
  private String idEspecie;
  private String idRaza;
  private Boolean esterilizado;
  private List<String> alergias;
  private List<VacunaMascotaEntidad> vacunas;
  private List<String> clientes;
  private String foto;
  private String qr;
  private Boolean borrado;
}
