package mascotas.demo.controller;
import java.util.List;

import mascotas.demo.service.EventoService;
import mascotas.demo.model.Evento;
import mascotas.demo.model.ResponseWrapper;
import mascotas.demo.hateoas.EventoModelAssembler;

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
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;
    private final EventoModelAssembler assembler;

    public EventoController(EventoService eventoService, EventoModelAssembler assembler){
        this.eventoService = eventoService;
        this.assembler = assembler;
    }

    // obtener todos los registros
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Evento>>> obtEventos() {
        
        log.info("GET /envio - Obteniendo todos los registros");
        List<Evento> evento = eventoService.obtenerEvents();

        if (evento.isEmpty()) {
            log.warn("No hay registros actualmente");
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(CollectionModel.empty());
        }

        List<EntityModel<Evento>> modelos = evento.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(modelos,
            linkTo(methodOn(EventoController.class).obtEventos()).withSelfRel()));
    }

    //  obtener registro por id
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Evento>> obtEventoId(@PathVariable Long id){
        
        log.info("GET /envio/{} - Obtener registro por id");
        Evento evento = eventoService.obtenerEventoId(id);
        return ResponseEntity.ok(assembler.toModel(evento));
    }

    // eliminar registro
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarEvento(@PathVariable Long id) {
        
        log.warn("DELETE /envio/{} - Eliminando registro", id);
        eventoService.eliminarEvento(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Evento eliminado exitosamente",
                        0,
                        null));
    }

    // modificar un registro por id
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Evento>> actualizarEvento(@PathVariable Long id,
            @Valid @RequestBody Evento eventoAct) {

        log.info("PUT /envio/{} - Actualizando registro", id);
        Evento registro = eventoService.actualizarEvento(id, eventoAct);

        return ResponseEntity.ok(assembler.toModel(registro));
    }

    // crear registro nuevo
    @PostMapping
    public ResponseEntity<EntityModel<Evento>> crearEvento(@Valid @RequestBody Evento evento) {
        
        log.info("POST /envio - Creando registro: {}", evento.getId());
        Evento registro = eventoService.guardarEvento(evento);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assembler.toModel(registro));
    }

}
