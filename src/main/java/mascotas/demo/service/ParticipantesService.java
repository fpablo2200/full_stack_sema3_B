package mascotas.demo.service;

import mascotas.demo.model.Participantes;
import mascotas.demo.repository.ParticipantesRepository;
import mascotas.demo.exception.ParticipanteNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantesService {

    @Autowired
    private ParticipantesRepository repositorio;

    // listar todo
    public List<Participantes> obtenerParticipantes() {
        return repositorio.findAll(Sort.by("id"));
    }

    // buscar por id
    public Participantes obtenerParticipanteId(Long id) {
        return repositorio.findById(id).orElseThrow(() -> new ParticipanteNotFoundException(id));
    }

    // guardar 
    public Participantes guardarParticipante(Participantes participante) {
        if (repositorio.existsById(participante.getId())) {
                throw new IllegalArgumentException("Ya existe un registro con el mismo ID" + participante.getId());
        }
        return repositorio.save(participante);
    }

    // actualizar por id
    public Participantes actualizarParticipante(Long id, Participantes participanteAct) {
        Participantes participanteEncontrado = repositorio.findById(id).orElseThrow(() -> new ParticipanteNotFoundException(id));

        participanteEncontrado.setNombreMascota(participanteAct.getNombreMascota());
        participanteEncontrado.setTipoMascota(participanteAct.getTipoMascota());
        participanteEncontrado.setEdad(participanteAct.getEdad());

        return repositorio.save(participanteEncontrado);
    }

    // eliminar
    public void eliminarParticipante(Long id) {
        Participantes participanteEncontrado = repositorio.findById(id).orElseThrow(() -> new ParticipanteNotFoundException(id));
        repositorio.delete(participanteEncontrado);
    }     
    
}
