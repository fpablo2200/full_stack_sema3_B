package mascotas.demo.controller;
import java.util.List;

import mascotas.demo.model.Entrenador;
import mascotas.demo.service.EntrenadorService;
import mascotas.demo.model.ResponseWrapper;
import mascotas.demo.hateoas.EntrenadorModelAssembler;

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
@RequestMapping("/entrenador")
public class EntrenadorController {

    private final EntrenadorService entrenadorService;
    private final EntrenadorModelAssembler assembler;

    public EntrenadorController(EntrenadorService entrenadorService, EntrenadorModelAssembler assembler){
        this.entrenadorService = entrenadorService;
        this.assembler = assembler;
    }

    // obtener todos los registros
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Entrenador>>> obtEntrenador() {

        log.info("GET /envio - Obteniendo todos los registros");
        List<Entrenador> entrenador = entrenadorService.obtenerEnentrenadores();

        if (entrenador.isEmpty()) {
            log.warn("No hay registros actualmente");
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(CollectionModel.empty());
        }

        List<EntityModel<Entrenador>> modelos = entrenador.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(modelos,
            linkTo(methodOn(EntrenadorController.class).obtEntrenador()).withSelfRel()));
    }

    //  obtener registro por id
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Entrenador>> obtEntrenadorId(@PathVariable Long id){

        log.info("GET /envio/{} - Obtener registro por id");
        Entrenador entrenador = entrenadorService.obtenerEntrenadorId(id);
        return ResponseEntity.ok(assembler.toModel(entrenador));
    }

    // eliminar registro
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarEntrenador(@PathVariable Long id) {

        log.warn("DELETE /envio/{} - Eliminando registro", id);
        entrenadorService.eliminarEnentrenador(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Entrenador eliminado exitosamente",
                        0,
                        null));
    }

    // modificar un registro por id
    @PostMapping
    public ResponseEntity<EntityModel<Entrenador>> crearEntrenador(@Valid @RequestBody Entrenador entrenador) {
        
        log.info("POST /envio - Creando registro: {}", entrenador.getId());
        Entrenador registro = entrenadorService.guardarEntrenador(entrenador);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assembler.toModel(registro));
    }

    // crear registro nuevo
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Entrenador>> actualizarEntrenador(@PathVariable Long id,
            @Valid @RequestBody Entrenador entrenadorAct) {

        log.info("PUT /envio/{} - Actualizando registro", id);
        Entrenador registro = entrenadorService.actualizarEntrenador(id, entrenadorAct);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assembler.toModel(registro));
    }
    
}
