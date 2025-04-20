package mascotas.demo.controller;

import mascotas.demo.service.ParticipantesService;
import mascotas.demo.model.Participantes;
import mascotas.demo.model.ResponseWrapper;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/participante")
public class ParticipantesController {
    private final ParticipantesService participantesService;

    public ParticipantesController(ParticipantesService participantesService){
        this.participantesService = participantesService;
    }

    @GetMapping
    public ResponseEntity<?> obtParticipantes() {
        List<Participantes> participante = participantesService.obtenerParticipantes();

        if (participante.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participantes no encontrada");
        }

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "OK",
                        participante.size(),
                        participante));
    }



    @GetMapping("/{id}")
    public Participantes obtParticipantesId(@PathVariable Long id){
        return participantesService.obtenerParticipanteId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarParticipantes(@PathVariable Long id) {
        participantesService.eliminarParticipante(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Participantes eliminado exitosamente",
                        0,
                        null));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<Participantes>> crearParticipantes(@Valid @RequestBody Participantes participante) {
        Participantes registro = participantesService.guardarParticipante(participante);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(
                        "Participantes creado exitosamente",
                        1,
                        List.of(registro)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Participantes>> actualizarParticipantes(@PathVariable Long id,
            @Valid @RequestBody Participantes participanteAct) {

        Participantes registro = participantesService.actualizarParticipante(id, participanteAct);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Participantes actualizada",
                        1,
                        List.of(registro)));
    }
    
}
