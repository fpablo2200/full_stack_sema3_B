package mascotas.demo.controller;
import java.util.List;

import mascotas.demo.service.ParticipantesService;
import mascotas.demo.model.Participantes;
import mascotas.demo.model.ResponseWrapper;
import mascotas.demo.hateoas.ParticipantesModelAssembler;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Slf4j
@RestController
@RequestMapping("/participante")
public class ParticipantesController {

    private final ParticipantesService participantesService;
    private final ParticipantesModelAssembler assembler;

    public ParticipantesController(ParticipantesService participantesService, ParticipantesModelAssembler assembler){
        this.participantesService = participantesService;
        this.assembler = assembler;
    }

    // obtener todos los registros
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Participantes>>> obtParticipantes() {
        
        log.info("GET /envio - Obteniendo todos los registros");
        List<Participantes> participante = participantesService.obtenerParticipantes();

        if (participante.isEmpty()) {
            log.warn("No hay registros actualmente");
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(CollectionModel.empty());
        }

        List<EntityModel<Participantes>> modelos = participante.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(modelos,
            linkTo(methodOn(ParticipantesController.class).obtParticipantes()).withSelfRel()));
    }

    //  obtener registro por id
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Participantes>> obtParticipantesId(@PathVariable Long id){

        log.info("GET /envio/{} - Obtener registro por id");
        Participantes participantes = participantesService.obtenerParticipanteId(id);
        return ResponseEntity.ok(assembler.toModel(participantes));
    }

    // eliminar registro
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarParticipantes(@PathVariable Long id) {
        
        log.warn("DELETE /envio/{} - Eliminando registro", id);
        participantesService.eliminarParticipante(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Participantes eliminado exitosamente",
                        0,
                        null));
    }

    // modificar un registro por id
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Participantes>> actualizarParticipantes(@PathVariable Long id, @Valid @RequestBody Participantes participanteAct) {

        log.info("PUT /envio/{} - Actualizando registro", id);
        Participantes registro = participantesService.actualizarParticipante(id, participanteAct);

        return ResponseEntity.ok(assembler.toModel(registro));
    }

    // crear registro nuevo
    @PostMapping
    public ResponseEntity<EntityModel<Participantes>> crearParticipantes(@Valid @RequestBody Participantes participante) {
        
        log.info("POST /envio - Creando registro: {}", participante.getId());
        Participantes registro = participantesService.guardarParticipante(participante);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assembler.toModel(registro));
    }
    
}
