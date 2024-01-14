package org.veterinaria.dominio.servicio.Mascota;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.veterinaria.aplicacion.puertos.salida.Especie.IEspecieRepositorio;
import org.veterinaria.aplicacion.puertos.salida.Mascota.IMascotaRepositorio;
import org.veterinaria.aplicacion.puertos.salida.Raza.IRazaRepositorio;
import org.veterinaria.aplicacion.puertos.salida.Vacuna.IVacunaRepositorio;
import org.veterinaria.dominio.modelo.Cliente.Cliente;
import org.veterinaria.dominio.modelo.Mascota.*;
import org.veterinaria.dominio.modelo.Recordatorio.Recordatorio;
import org.veterinaria.dominio.modelo.Vacuna.VacunaEntidad;
import org.veterinaria.dominio.modelo.Vacuna.VacunaSalida;
import org.veterinaria.dominio.servicio.Alergia.IAlergiaServicio;
import org.veterinaria.dominio.servicio.Sexo.ISexoServicio;
import org.veterinaria.dominio.servicio.Vacuna.IVacunaServicio;
import org.veterinaria.infraestructura.adaptador.salida.Cliente.ClienteAPI;
import org.veterinaria.infraestructura.adaptador.salida.Excepciones.ClienteNotFoundException;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class MascotaServicio implements IMascotaServicio {
  public static final String FORMATO_FECHA = "dd/MM/yyyy";
  @Inject
  IMascotaRepositorio repositorio;
  @Inject
  ISexoServicio servicioSexo;
  @Inject
  IEspecieRepositorio repositorioEspecie;
  @Inject
  IRazaRepositorio repositorioRaza;
  @Inject
  IAlergiaServicio servicioAlergias;
  @Inject
  IVacunaServicio servicioVacunas;
  @Inject
  IVacunaRepositorio vacunaRepositorio;
  @Inject
  @RestClient
  ClienteAPI clienteService;

  @Override
  public List<MascotaSalida> obtenerMascota() {
    List<MascotaEntidad> mascotas = repositorio.obtenerTodosMascota();
    return mascotas.parallelStream().map(p ->
          getMascotaSalida(p,null)).toList();
  }

  @Override
  public MascotaSalida obtenerMascotaPorId(String idMascota) {
    MascotaEntidad mascotaEntidad = repositorio.obtenerMascotaPorId(idMascota);
    List<Recordatorio> recordatorios = null;
    if(mascotaEntidad.getVacunas() != null && !mascotaEntidad.getVacunas().isEmpty()){
      recordatorios = obtenerRecordatorios(mascotaEntidad.getVacunas());
    }
    return getMascotaSalida(mascotaEntidad, recordatorios);
  }

  public List<Recordatorio> obtenerRecordatorios(List<VacunaMascotaEntidad> vacunas) {
    return vacunas.parallelStream()
          .map(p -> {
            VacunaEntidad vs = vacunaRepositorio.obtenerVacunaPorId(p.getIdVacuna());
            // Convertir la fecha a ZonedDateTime
            ZonedDateTime fechaZDT = p.getFecha().toInstant().atZone(ZoneId.systemDefault());
            // Parsear la duración
            Duration duracionISO = Duration.parse(vs.getDuracionISO());
            // Sumar la duración a la fecha
            ZonedDateTime fechaFinal = fechaZDT.plus(duracionISO);
            // Convertir la fecha final a Date si es necesario
            Date fechaFinalDate = Date.from(fechaFinal.toInstant());
            return Recordatorio.builder()
                  .fecha(convertirFechaAString(fechaFinalDate))
                  .tipo("Vacuna")
                  .detalle(vs.getVacuna())
                  .build();
          })
          .toList();
  }

  public String convertirFechaAString(Date fecha) {
    SimpleDateFormat formatter = new SimpleDateFormat(FORMATO_FECHA);
    return formatter.format(fecha);
  }

  @Override
  public MascotaSalida crearMascota(MascotaCrear mascota) {
    List<String> clientesValidos;
    clientesValidos = validarClientesIds(mascota.getClientes());
    if (!mascota.getVacunas().isEmpty()) {
      mascota.getVacunas().forEach(p -> {
        if (p.fechaInvalida()) throw new RuntimeException("Fecha invalida");
      });
    }
    MascotaEntidad mascotaEntidad = crearMascotaEntidad(mascota.getNombre(), mascota.getApellido(),
          mascota.getFechaNacimiento(), mascota.getIdSexo(), mascota.getIdEspecie(), mascota.getIdRaza(),
          mascota.getEsterilizado(), mascota.getAlergias(), mascota.getVacunas(), clientesValidos);
    mascotaEntidad = repositorio.crearMascota(mascotaEntidad);
    agregarMascotaAlUsuario(mascota.getClientes(), mascotaEntidad.getId().toString());

    return this.obtenerMascotaPorId(mascotaEntidad.getId().toString());
  }

  @Override
  public MascotaSalida actualizarMascota(String idMascota, MascotaActualizar mascota) {
    List<String> clientesValidos;
    clientesValidos = validarClientesIds(mascota.getClientes());
    if (!mascota.getVacunas().isEmpty()) {
      mascota.getVacunas().forEach(p -> {
        if (p.fechaInvalida()) throw new RuntimeException("Fecha invalida");
      });
    }
    MascotaEntidad mascotaEntidad = crearMascotaEntidad(mascota.getNombre(), mascota.getApellido(),
          mascota.getFechaNacimiento(), mascota.getIdSexo(), mascota.getIdEspecie(), mascota.getIdRaza(),
          mascota.getEsterilizado(), mascota.getAlergias(), mascota.getVacunas(), clientesValidos);
    repositorio.actualizarMascota(idMascota, mascotaEntidad);
    agregarMascotaAlUsuario(mascota.getClientes(), idMascota);
    return this.obtenerMascotaPorId(idMascota);
  }

  @Override
  public MascotaSalida eliminarMascota(String idMascota) {
    MascotaEntidad mascotaEntidad = repositorio.eliminarMascota(idMascota);
    return getMascotaSalida(mascotaEntidad, null);
  }

  private MascotaEntidad crearMascotaEntidad(String nombre, String apellido, String fechaNacimiento, String idSexo, String idEspecie, String idRaza, Boolean esterilizado, List<String> alergias, List<VacunaMascota> vacunas, List<String> clientes) {
    MascotaEntidad mascotaEntidad = new MascotaEntidad();
    mascotaEntidad.setNombre(nombre);
    mascotaEntidad.setApellido(apellido);
    mascotaEntidad.setFechaNacimiento(fechaNacimiento);
    mascotaEntidad.setIdSexo(idSexo);
    mascotaEntidad.setIdEspecie(idEspecie);
    mascotaEntidad.setIdRaza(idRaza);
    mascotaEntidad.setEsterilizado(esterilizado);
    mascotaEntidad.setAlergias(alergias);
    List<VacunaMascotaEntidad> vacunaMascotaEntidad = vacunas.parallelStream().map(p -> VacunaMascotaEntidad.builder()
          .idVacuna(p.getIdVacuna())
          .fecha(p.convertirFecha())
          .build()).toList();
    mascotaEntidad.setVacunas(vacunaMascotaEntidad);
    mascotaEntidad.setClientes(clientes);
    mascotaEntidad.setFoto(nombre);
    return mascotaEntidad;
  }

  private MascotaSalida getMascotaSalida(MascotaEntidad mascotaEntidad, List<Recordatorio> recordatorios) {
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
                (mascotaEntidad.getAlergias() == null || mascotaEntidad.getAlergias().isEmpty()) ? null :
                      mascotaEntidad.getAlergias().parallelStream()
                            .map(p -> servicioAlergias.obtenerAlergiaPorId(p))
                            .toList()
          )
          .vacunas(
                (mascotaEntidad.getVacunas() == null || mascotaEntidad.getVacunas().isEmpty()) ? null :
                      mascotaEntidad.getVacunas().parallelStream()
                            .map(p -> VacunaMascotaSalida.builder()
                                  .idVacuna(p.getIdVacuna())
                                  .vacuna(servicioVacunas.obtenerVacunaPorId(p.getIdVacuna()).getVacuna())
                                  .fecha(p.convertirFechaAString())
                                  .build())
                            .toList()
          )
          .recordatorios(recordatorios)
          .clientes(mascotaEntidad.getClientes())
          .build();
  }

  private List<String> validarClientesIds(List<String> clientes) {
    List<String> clientesValidos = new ArrayList<String>();
    if (!clientes.isEmpty()) {
      clientes.forEach(p -> {
        try {
          Cliente cliente = clienteService.getClientePorId(p);
          clientesValidos.add(p);
        } catch (WebApplicationException e) {
          if (e.getResponse().getStatus() == 404) {
            throw new ClienteNotFoundException(p);
          } else {
            throw e;
          }
        }
      });
    }
    return clientesValidos;
  }

  private void agregarMascotaAlUsuario(List<String> clientes, String idMascota) {
    if (!clientes.isEmpty()) {
      clientes.forEach(p -> {
        try {
          clienteService.postMascotaCliente(p, Arrays.asList(idMascota));
        } catch (WebApplicationException e) {
          if (e.getResponse().getStatus() == 404) {
            throw new ClienteNotFoundException(p);
          } else {
            throw e;
          }
        }
      });
    }
  }
}