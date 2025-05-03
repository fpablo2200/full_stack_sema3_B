package mascotas.demo.service;

import mascotas.demo.model.Entrenador;
import mascotas.demo.repository.EntrenadorRepository;
import mascotas.demo.exception.EntrenadorNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EntrenadorService {

    @Autowired
    private EntrenadorRepository repositorio;

    public EntrenadorService(EntrenadorRepository entrenadorRepository) {
        this.repositorio = entrenadorRepository;
    }

    // listar todo
    public List<Entrenador> obtenerEnentrenadores() {
        log.debug("Servicio: obtenerEnentrenadores()");
        return repositorio.findAll(Sort.by("id"));
    }

    // buscar por id
    public Entrenador obtenerEntrenadorId(Long id) {
        log.debug("Servicio: obtenerEntrenadorId({})", id);
        return repositorio.findById(id).orElseThrow(() -> new EntrenadorNotFoundException(id));
    }

    // guardar
    public Entrenador guardarEntrenador(Entrenador entrenador) {
        log.debug("Servicio: guardarEntrenador({})", entrenador.getId());
        if (repositorio.existsById(entrenador.getId())) {
            log.error("Ya existe una registro con ID {}", entrenador.getId());
                throw new IllegalArgumentException("Ya existe un registro con el mismo ID" + entrenador.getId());
        }
        return repositorio.save(entrenador);
    }

    // actualizar por id
    public Entrenador actualizarEntrenador(Long id, Entrenador entrenadorAct) {
        log.debug("Servicio: actualizarEntrenador({}, {})", id, entrenadorAct.getId());
        Entrenador entrenadorEncontrado = repositorio.findById(id).orElseThrow(() -> new EntrenadorNotFoundException(id));

        entrenadorEncontrado.setNombre(entrenadorAct.getNombre());
        entrenadorEncontrado.setDni(entrenadorAct.getDni());
        entrenadorEncontrado.setCorreo(entrenadorAct.getCorreo());

        return repositorio.save(entrenadorEncontrado);
    }

    // eliminar 
    public void eliminarEnentrenador(Long id) {
        log.debug("Servicio: eliminarEnentrenador({})", id);
        Entrenador entrenadorEncontrado = repositorio.findById(id).orElseThrow(() -> new EntrenadorNotFoundException(id));
        repositorio.delete(entrenadorEncontrado);
    }  
    
}
