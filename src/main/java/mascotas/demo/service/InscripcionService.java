package mascotas.demo.service;

import mascotas.demo.model.Inscripcion;
import mascotas.demo.repository.InscripcionRepository;
import mascotas.demo.exception.InscripcionNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository repositorio;

    public InscripcionService(InscripcionRepository inscripcionRepository) {
        this.repositorio = inscripcionRepository;
    }

    // listar todo
    public List<Inscripcion> obtenerInscripciones() {
        log.debug("Servicio: obtenerInscripciones()");
        return repositorio.findAll(Sort.by("id"));
    }

    // buscar por id
    public Inscripcion obtenerInscripcionId(Long id) {
        log.debug("Servicio: obtenerInscripcionId({})", id);
        return repositorio.findById(id).orElseThrow(() -> new InscripcionNotFoundException(id));
    }

    // guardar
    public Inscripcion guardarInscripcion(Inscripcion inscripcion) {
        log.debug("Servicio: guardarInscripcion({})", inscripcion.getId());
        if (repositorio.existsById(inscripcion.getId())) {
            log.error("Ya existe una registro con ID {}", inscripcion.getId());
                throw new IllegalArgumentException("Ya existe un registro con el mismo ID" + inscripcion.getId());
        }
        return repositorio.save(inscripcion);
    }

    // actualizar por id
    public Inscripcion actualizarInscripcion(Long id, Inscripcion inscripcionAct) {
        log.debug("Servicio: actualizarInscripcion({}, {})", id, inscripcionAct.getId());
        Inscripcion inscripcionEncontrado = repositorio.findById(id).orElseThrow(() -> new InscripcionNotFoundException(id));

        inscripcionEncontrado.setIdEvento(inscripcionAct.getIdEvento());
        inscripcionEncontrado.setIdEntrenador(inscripcionAct.getIdEntrenador());
        inscripcionEncontrado.setIdParticipante(inscripcionAct.getIdParticipante());
        inscripcionEncontrado.setFechaInscripcion(inscripcionAct.getFechaInscripcion());

        return repositorio.save(inscripcionEncontrado);
    }

    // eliminar 
    public void eliminarInscripcion(Long id) {
        log.debug("Servicio: eliminarInscripcion({})", id);
        Inscripcion inscripcionEncontrado = repositorio.findById(id).orElseThrow(() -> new InscripcionNotFoundException(id));
        repositorio.delete(inscripcionEncontrado);
    }     
}
    

