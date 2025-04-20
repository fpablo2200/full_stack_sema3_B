package mascotas.demo.service;

import mascotas.demo.model.Entrenador;
import mascotas.demo.repository.EntrenadorRepository;
import mascotas.demo.exception.EntrenadorNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenadorService {

    @Autowired
    private EntrenadorRepository repositorio;

    // listar todo
    public List<Entrenador> obtenerEnentrenadores() {
        return repositorio.findAll(Sort.by("id"));
    }

    // buscar por id
    public Entrenador obtenerEntrenadorId(Long id) {
        return repositorio.findById(id).orElseThrow(() -> new EntrenadorNotFoundException(id));
    }

    // guardar
    public Entrenador guardarEntrenador(Entrenador entrenador) {
        if (repositorio.existsById(entrenador.getId())) {
                throw new IllegalArgumentException("Ya existe un registro con el mismo ID" + entrenador.getId());
        }
        return repositorio.save(entrenador);
    }

    // actualizar por id
    public Entrenador actualizarEntrenador(Long id, Entrenador entrenadorAct) {
        Entrenador entrenadorEncontrado = repositorio.findById(id).orElseThrow(() -> new EntrenadorNotFoundException(id));

        entrenadorEncontrado.setNombre(entrenadorAct.getNombre());
        entrenadorEncontrado.setDni(entrenadorAct.getDni());
        entrenadorEncontrado.setCorreo(entrenadorAct.getCorreo());

        return repositorio.save(entrenadorEncontrado);
    }

    // eliminar 
    public void eliminarEnentrenador(Long id) {
        Entrenador entrenadorEncontrado = repositorio.findById(id).orElseThrow(() -> new EntrenadorNotFoundException(id));
        repositorio.delete(entrenadorEncontrado);
    }  
    
}
