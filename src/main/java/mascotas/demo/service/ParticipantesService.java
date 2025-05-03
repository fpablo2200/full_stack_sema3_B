package mascotas.demo.service;

import mascotas.demo.model.Participantes;
import mascotas.demo.repository.ParticipantesRepository;
import mascotas.demo.exception.ParticipanteNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ParticipantesService {

    @Autowired
    private ParticipantesRepository repositorio;

    public ParticipantesService(ParticipantesRepository participanteRepository) {
        this.repositorio = participanteRepository;
    }

    // listar todo
    public List<Participantes> obtenerParticipantes() {
        log.debug("Servicio: obtenerParticipantes()");
        return repositorio.findAll(Sort.by("id"));
    }

    // buscar por id
    public Participantes obtenerParticipanteId(Long id) {
        log.debug("Servicio: obtenerParticipanteId({})", id);
        return repositorio.findById(id).orElseThrow(() -> new ParticipanteNotFoundException(id));
    }

    // guardar 
    public Participantes guardarParticipante(Participantes participante) {
        log.debug("Servicio: guardarParticipante({})", participante.getId());
        if (repositorio.existsById(participante.getId())) {
            log.error("Ya existe una registro con ID {}", participante.getId());
                throw new IllegalArgumentException("Ya existe un registro con el mismo ID" + participante.getId());
        }
        return repositorio.save(participante);
    }

    // actualizar por id
    public Participantes actualizarParticipante(Long id, Participantes participanteAct) {
        log.debug("Servicio: actualizarParticipante({}, {})", id, participanteAct.getId());
        Participantes participanteEncontrado = repositorio.findById(id).orElseThrow(() -> new ParticipanteNotFoundException(id));

        participanteEncontrado.setNombreMascota(participanteAct.getNombreMascota());
        participanteEncontrado.setTipoMascota(participanteAct.getTipoMascota());
        participanteEncontrado.setEdad(participanteAct.getEdad());

        return repositorio.save(participanteEncontrado);
    }

    // eliminar
    public void eliminarParticipante(Long id) {
        log.debug("Servicio: eliminarParticipante({})", id);
        Participantes participanteEncontrado = repositorio.findById(id).orElseThrow(() -> new ParticipanteNotFoundException(id));
        repositorio.delete(participanteEncontrado);
    }     
    
}
