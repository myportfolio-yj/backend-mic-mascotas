package org.veterinaria.dominio.servicio.Mascota;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.veterinaria.aplicacion.puertos.salida.Especie.IEspecieRepositorio;
import org.veterinaria.aplicacion.puertos.salida.Mascota.IMascotaRepositorio;
import org.veterinaria.aplicacion.puertos.salida.Raza.IRazaRepositorio;
import org.veterinaria.dominio.modelo.Mascota.MascotaActualizar;
import org.veterinaria.dominio.modelo.Mascota.MascotaCrear;
import org.veterinaria.dominio.modelo.Mascota.MascotaEntidad;
import org.veterinaria.dominio.modelo.Mascota.MascotaSalida;
import org.veterinaria.dominio.servicio.Alergia.IAlergiaServicio;
import org.veterinaria.dominio.servicio.Especie.IEspecieServicio;
import org.veterinaria.dominio.servicio.Sexo.ISexoServicio;
import org.veterinaria.dominio.servicio.Vacuna.IVacunaServicio;

import java.util.List;

@ApplicationScoped
public class MascotaServicio implements IMascotaServicio {
  @Inject
  IMascotaRepositorio repositorio;
  @Inject
  ISexoServicio servicioSexo;
  @Inject
  IEspecieServicio servicioEspecie;
  @Inject
  IEspecieRepositorio repositorioEspecie;
  @Inject
  IRazaRepositorio repositorioRaza;
  @Inject
  IAlergiaServicio servicioAlergias;
  @Inject
  IVacunaServicio servicioVacunas;

  @Override
  public MascotaSalida actualizarMascota(String idMascota, MascotaActualizar mascota) {
    MascotaEntidad mascotaEntidad = new MascotaEntidad();
    mascotaEntidad.setNombre(mascota.getNombre());
    mascotaEntidad.setApellido(mascota.getApellido());
    mascotaEntidad.setFechaNacimiento(mascota.getFechaNacimiento());
    mascotaEntidad.setIdSexo(mascota.getIdSexo());
    mascotaEntidad.setIdEspecie(mascota.getIdEspecie());
    mascotaEntidad.setIdRaza(mascota.getIdRaza());
    mascotaEntidad.setEsterilizado(mascota.getEsterilizado());
    mascotaEntidad.setAlergias(mascota.getAlergias());
    mascotaEntidad.setVacunas(mascota.getVacunas());
    mascotaEntidad.setFoto(mascota.getNombre());
    repositorio.actualizarMascota(idMascota, mascotaEntidad);
    return this.obtenerMascotaPorId(idMascota);
  }

  @Override
  public MascotaSalida crearMascota(MascotaCrear mascota) {
    MascotaEntidad mascotaEntidad = new MascotaEntidad();
    mascotaEntidad.setNombre(mascota.getNombre());
    mascotaEntidad.setApellido(mascota.getApellido());
    mascotaEntidad.setFechaNacimiento(mascota.getFechaNacimiento());
    mascotaEntidad.setIdSexo(mascota.getIdSexo());
    mascotaEntidad.setIdEspecie(mascota.getIdEspecie());
    mascotaEntidad.setIdRaza(mascota.getIdRaza());
    mascotaEntidad.setEsterilizado(mascota.getEsterilizado());
    mascotaEntidad.setAlergias(mascota.getAlergias());
    mascotaEntidad.setVacunas(mascota.getVacunas());
    mascotaEntidad.setFoto(mascota.getNombre());
    mascotaEntidad = repositorio.crearMascota(mascotaEntidad);
    return this.obtenerMascotaPorId(mascotaEntidad.getId().toString());
  }

  @Override
  public MascotaSalida eliminarMascota(String idMascota) {
    MascotaEntidad mascotaEntidad = repositorio.eliminarMascota(idMascota);

    return MascotaSalida.builder()
          .id(mascotaEntidad.getId().toString())
          .codIdentificacion(mascotaEntidad.getCodIdentificacion())
          .nombre(mascotaEntidad.getNombre())
          .apellido(mascotaEntidad.getApellido())
          .fechaNacimiento(mascotaEntidad.getFechaNacimiento())
          .sexo(servicioSexo.obtenerSexoPorId(mascotaEntidad.getIdSexo()))
          .especie(repositorioEspecie.obtenerEspeciePorId(mascotaEntidad.getIdEspecie()).getEspecieClass())
          .raza(repositorioRaza.obtenerRazaPorId(mascotaEntidad.getIdRaza()).getRazaClass())
          .esterilizado(mascotaEntidad.getEsterilizado())
          .foto(mascotaEntidad.getFoto())
          .qr(mascotaEntidad.getQr())
          .alergias(
                mascotaEntidad.getAlergias().parallelStream()
                      .map(p -> servicioAlergias.obtenerAlergiaPorId(p))
                      .toList()
          )
          .vacunas(
                mascotaEntidad.getVacunas().parallelStream()
                      .map(p -> servicioVacunas.obtenerVacunaPorId(p))
                      .toList()
          )
          .build();
  }

  @Override
  public MascotaSalida obtenerMascotaPorId(String idMascota) {
    MascotaEntidad mascotaEntidad = repositorio.obtenerMascotaPorId(idMascota);
    return MascotaSalida.builder()
          .id(mascotaEntidad.getId().toString())
          .codIdentificacion(mascotaEntidad.getCodIdentificacion())
          .nombre(mascotaEntidad.getNombre())
          .apellido(mascotaEntidad.getApellido())
          .fechaNacimiento(mascotaEntidad.getFechaNacimiento())
          .sexo(servicioSexo.obtenerSexoPorId(mascotaEntidad.getIdSexo()))
          .especie(repositorioEspecie.obtenerEspeciePorId(mascotaEntidad.getIdEspecie()).getEspecieClass())
          .raza(repositorioRaza.obtenerRazaPorId(mascotaEntidad.getIdRaza()).getRazaClass())
          .esterilizado(mascotaEntidad.getEsterilizado())
          .foto(mascotaEntidad.getFoto())
          .qr(mascotaEntidad.getQr())
          .alergias(
                mascotaEntidad.getAlergias().parallelStream()
                      .map(p -> servicioAlergias.obtenerAlergiaPorId(p))
                      .toList()
          )
          .vacunas(
                mascotaEntidad.getVacunas().parallelStream()
                      .map(p -> servicioVacunas.obtenerVacunaPorId(p))
                      .toList()
          )
          .build();
  }

  @Override
  public List<MascotaSalida> obtenerMascota() {
    List<MascotaEntidad> mascotas = repositorio.obtenerTodosMascota();
    return mascotas.parallelStream().map(mascotaEntidad -> MascotaSalida.builder()
          .id(mascotaEntidad.getId().toString())
          .codIdentificacion(mascotaEntidad.getCodIdentificacion())
          .nombre(mascotaEntidad.getNombre())
          .apellido(mascotaEntidad.getApellido())
          .fechaNacimiento(mascotaEntidad.getFechaNacimiento())
          .sexo(servicioSexo.obtenerSexoPorId(mascotaEntidad.getIdSexo()))
          .especie(repositorioEspecie.obtenerEspeciePorId(mascotaEntidad.getIdEspecie()).getEspecieClass())
          .raza(repositorioRaza.obtenerRazaPorId(mascotaEntidad.getIdRaza()).getRazaClass())
          .esterilizado(mascotaEntidad.getEsterilizado())
          .foto(mascotaEntidad.getFoto())
          .qr(mascotaEntidad.getQr())
          .alergias(
                mascotaEntidad.getAlergias().parallelStream()
                      .map(p -> servicioAlergias.obtenerAlergiaPorId(p))
                      .toList()
          )
          .vacunas(
                mascotaEntidad.getVacunas().parallelStream()
                      .map(p -> servicioVacunas.obtenerVacunaPorId(p))
                      .toList()
          )
          .build()).toList();
  }
}