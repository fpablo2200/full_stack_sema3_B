package mascotas.demo.service;

import mascotas.demo.model.Inscripcion;
import mascotas.demo.repository.InscripcionRepository;
import mascotas.demo.exception.InscripcionNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository repositorio;

    // listar todo
    public List<Inscripcion> obtenerInscripciones() {
        return repositorio.findAll(Sort.by("id"));
    }

    // buscar por id
    public Inscripcion obtenerInscripcionId(Long id) {
        return repositorio.findById(id).orElseThrow(() -> new InscripcionNotFoundException(id));
    }

    // guardar
    public Inscripcion guardarInscripcion(Inscripcion inscripcion) {
        if (repositorio.existsById(inscripcion.getId())) {
                throw new IllegalArgumentException("Ya existe un registro con el mismo ID" + inscripcion.getId());
        }
        return repositorio.save(inscripcion);
    }

    // actualizar por id
    public Inscripcion actualizarInscripcion(Long id, Inscripcion inscripcionAct) {
        Inscripcion inscripcionEncontrado = repositorio.findById(id).orElseThrow(() -> new InscripcionNotFoundException(id));

        inscripcionEncontrado.setIdEvento(inscripcionAct.getIdEvento());
        inscripcionEncontrado.setIdEntrenador(inscripcionAct.getIdEntrenador());
        inscripcionEncontrado.setIdParticipante(inscripcionAct.getIdParticipante());
        inscripcionEncontrado.setFechaInscripcion(inscripcionAct.getFechaInscripcion());

        return repositorio.save(inscripcionEncontrado);
    }

    // eliminar 
    public void eliminarInscripcion(Long id) {
        Inscripcion inscripcionEncontrado = repositorio.findById(id).orElseThrow(() -> new InscripcionNotFoundException(id));
        repositorio.delete(inscripcionEncontrado);
    }     
}
    

