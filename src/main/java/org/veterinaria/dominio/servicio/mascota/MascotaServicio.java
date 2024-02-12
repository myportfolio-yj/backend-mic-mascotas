package org.veterinaria.dominio.servicio.mascota;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.veterinaria.aplicacion.puertos.salida.especie.IEspecieRepositorio;
import org.veterinaria.aplicacion.puertos.salida.mascota.IMascotaRepositorio;
import org.veterinaria.aplicacion.puertos.salida.raza.IRazaRepositorio;
import org.veterinaria.aplicacion.puertos.salida.vacuna.IVacunaRepositorio;
import org.veterinaria.dominio.modelo.cliente.ClienteSalida;
import org.veterinaria.dominio.modelo.mascota.*;
import org.veterinaria.dominio.modelo.recordatorio.RecordatorioSalida;
import org.veterinaria.dominio.modelo.vacuna.VacunaEntidad;
import org.veterinaria.dominio.modelo.vacuna.VacunaSalida;
import org.veterinaria.dominio.servicio.alergia.IAlergiaServicio;
import org.veterinaria.dominio.servicio.sexo.ISexoServicio;
import org.veterinaria.dominio.servicio.vacuna.IVacunaServicio;
import org.veterinaria.infraestructura.adaptador.salida.cliente.ClienteAPI;
import org.veterinaria.infraestructura.adaptador.salida.excepciones.ClienteNotFoundException;

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
  public static final String FECHA_INVALDIA = "Fecha invalida";
  public static final String VACUNA = "Vacuna";
  private final IMascotaRepositorio repositorio;
  private final ISexoServicio servicioSexo;
  private final IEspecieRepositorio repositorioEspecie;
  private final IRazaRepositorio repositorioRaza;
  private final IAlergiaServicio servicioAlergias;
  private final IVacunaServicio servicioVacunas;
  private final IVacunaRepositorio vacunaRepositorio;
  private final ClienteAPI clienteService;

  @Inject
  public MascotaServicio(IMascotaRepositorio repositorio,
                         ISexoServicio servicioSexo,
                         IEspecieRepositorio repositorioEspecie,
                         IRazaRepositorio repositorioRaza,
                         IAlergiaServicio servicioAlergias,
                         IVacunaServicio servicioVacunas,
                         IVacunaRepositorio vacunaRepositorio, @RestClient ClienteAPI clienteService) {
    this.repositorio = repositorio;
    this.servicioSexo = servicioSexo;
    this.repositorioEspecie = repositorioEspecie;
    this.repositorioRaza = repositorioRaza;
    this.servicioAlergias = servicioAlergias;
    this.servicioVacunas = servicioVacunas;
    this.vacunaRepositorio = vacunaRepositorio;
    this.clienteService = clienteService;
  }

  @Override
  public List<MascotaSalida> obtenerMascota() {
    List<MascotaEntidad> mascotas = repositorio.obtenerTodosMascota();
    return mascotas.parallelStream().map(p ->
          getMascotaSalida(p, null)).toList();
  }

  @Override
  public MascotaSalida obtenerMascotaPorId(String idMascota) {
    MascotaEntidad mascotaEntidad = repositorio.obtenerMascotaPorId(idMascota);
    List<RecordatorioSalida> recordatorios = null;
    if (mascotaEntidad.getVacunas() != null && !mascotaEntidad.getVacunas().isEmpty()) {
      recordatorios = obtenerRecordatorios(mascotaEntidad.getVacunas());
    }
    return getMascotaSalida(mascotaEntidad, recordatorios);
  }

  public List<RecordatorioSalida> obtenerRecordatorios(List<VacunaMascotaEntidad> vacunas) {
    return vacunas.parallelStream()
          .map(p -> {
            VacunaEntidad vs = vacunaRepositorio.obtenerVacunaPorId(p.getIdVacuna());
            ZonedDateTime fechaZDT = p.getFecha().toInstant().atZone(ZoneId.systemDefault());
            Duration duracionISO = Duration.parse(vs.getDuracionISO());
            ZonedDateTime fechaFinal = fechaZDT.plus(duracionISO);
            Date fechaFinalDate = Date.from(fechaFinal.toInstant());
            return RecordatorioSalida.builder()
                  .fecha(convertirFechaAString(fechaFinalDate))
                  .tipo(VACUNA)
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
    MascotaEntidad mascotaEntidad = validaGeneraMascotaEntidad(mascota.getClientes(), mascota.getVacunas(), mascota.getNombre(),
          mascota.getApellido(), mascota.getFechaNacimiento(), mascota.getIdSexo(), mascota.getIdEspecie(),
          mascota.getIdRaza(), mascota.getEsterilizado(), mascota.getAlergias());
    mascotaEntidad = repositorio.crearMascota(mascotaEntidad);
    agregarMascotaAlUsuario(mascota.getClientes(), mascotaEntidad.id.toString());

    return this.obtenerMascotaPorId(mascotaEntidad.id.toString());
  }

  private MascotaEntidad validaGeneraMascotaEntidad(List<String> clientes, List<VacunaMascota> vacunas, String nombre,
                                                    String apellido, String fechaNacimiento, String idSexo, String idEspecie,
                                                    String idRaza, Boolean esterilizado, List<String> alergias) {
    List<String> clientesValidos = validarClientesIds(clientes);
    if (!vacunas.isEmpty()) {
      vacunas.forEach(p -> {
        if (p.fechaInvalida()) throw new RuntimeException(FECHA_INVALDIA);
      });
    }
    return crearMascotaEntidad(nombre, apellido,
          fechaNacimiento, idSexo, idEspecie, idRaza,
          esterilizado, alergias, vacunas, clientesValidos);
  }

  @Override
  public MascotaSalida actualizarMascota(String idMascota, MascotaActualizar mascota) {
    MascotaEntidad mascotaEntidad = validaGeneraMascotaEntidad(mascota.getClientes(), mascota.getVacunas(), mascota.getNombre(),
          mascota.getApellido(), mascota.getFechaNacimiento(), mascota.getIdSexo(), mascota.getIdEspecie(),
          mascota.getIdRaza(), mascota.getEsterilizado(), mascota.getAlergias());
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

  private MascotaSalida getMascotaSalida(MascotaEntidad mascotaEntidad, List<RecordatorioSalida> recordatorios) {
    return MascotaSalida.builder()
          .id(mascotaEntidad.id.toString())
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
                      getVacuna(mascotaEntidad.getVacunas())
          )
          .recordatorios(recordatorios)
          .clientes(mascotaEntidad.getClientes().parallelStream().map(
                clienteService::getClienteMinPorId
          ).toList())
          .build();
  }

  private List<VacunaMascotaSalida> getVacuna(List<VacunaMascotaEntidad> vacuna) {
    return vacuna.parallelStream()
          .map(p -> {
            VacunaSalida vacunaSalida = servicioVacunas.obtenerVacunaPorId(p.getIdVacuna());
            return VacunaMascotaSalida.builder()
                  .idVacuna(p.getIdVacuna())
                  .vacuna(vacunaSalida.getVacuna())
                  .fecha(p.convertirFechaAString())
                  .duracion(vacunaSalida.getDuracion())
                  .build();
          })
          .toList();
  }

  private List<String> validarClientesIds(List<String> clientes) {
    List<String> clientesValidos = new ArrayList<String>();
    if (!clientes.isEmpty()) {
      clientes.forEach(p -> {
        try {
          ClienteSalida cliente = clienteService.getClientePorId(p);
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