package org.veterinaria.infraestructura.adaptador.salida.Mascota;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;
import org.veterinaria.aplicacion.puertos.salida.Mascota.IMascotaRepositorio;
import org.veterinaria.dominio.modelo.Mascota.MascotaEntidad;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ApplicationScoped
public class MascotaRepositorio implements PanacheMongoRepository<MascotaEntidad>, IMascotaRepositorio {
  @Override
  public List<MascotaEntidad> obtenerTodosMascota() {
    return listAll().parallelStream()
          .filter(p -> p.getBorrado() == null || !p.getBorrado())
          .toList();
  }

  @Override
  public MascotaEntidad obtenerMascotaPorId(String idMascota) {
    return findByIdOptional(new ObjectId(idMascota))
          .filter(p -> p.getBorrado() == null || !p.getBorrado())
          .orElseGet(MascotaEntidad::new);
  }

  @Override
  public MascotaEntidad crearMascota(MascotaEntidad mascota) {
    // Crear un formateador de fecha y hora
    // Obtener la fecha y hora actual
    // Formatear la fecha y hora actual
    String ahora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("'C'yyyyMMddHHmmssSSS"));
    mascota.setCodIdentificacion(ahora);
    mascota.setBorrado(false);
    mascota.persist();
    return mascota;
  }

  @Override
  public MascotaEntidad actualizarMascota(String idMascota, MascotaEntidad mascota) {
    return findByIdOptional(new ObjectId(idMascota))
          .map(p -> {
            p.setNombre(mascota.getNombre());
            p.setApellido(mascota.getApellido());
            p.setFechaNacimiento(mascota.getFechaNacimiento());
            p.setIdSexo(mascota.getIdSexo());
            p.setIdEspecie(mascota.getIdEspecie());
            p.setIdRaza(mascota.getIdRaza());
            p.setEsterilizado(mascota.getEsterilizado());
            p.setAlergias(mascota.getAlergias());
            p.setVacunas(mascota.getVacunas());
            p.setFoto(mascota.getFoto());
            p.setClientes(mascota.getClientes());
            update(p);
            return p;
          })
          .orElseThrow(() -> new RuntimeException("Mascota no encontrado"));
  }

  @Override
  public MascotaEntidad eliminarMascota(String idMascota) {
    MascotaEntidad mascotaEntidad = this.obtenerMascotaPorId(idMascota);
    mascotaEntidad.setBorrado(true);
    return this.actualizarMascota(idMascota, mascotaEntidad);
  }
}
