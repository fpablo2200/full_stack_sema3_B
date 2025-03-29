package mascotas.demo.controller;
import mascotas.demo.service.ParticipantesService;
import mascotas.demo.model.Participantes;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/participante")
public class ParticipantesController {
    private final ParticipantesService participantesService;

    public ParticipantesController(ParticipantesService participantesService){
        this.participantesService = participantesService;
    }

    @GetMapping
    public List<Participantes> obtParticipantes(){
        return participantesService.obtenerParticipantes();
    }

    @GetMapping("/{id}")
    public Optional<Participantes> obtParticipanteId(@PathVariable Long id){
        return participantesService.obtenerParticipanteId(id);
    }
    
}
