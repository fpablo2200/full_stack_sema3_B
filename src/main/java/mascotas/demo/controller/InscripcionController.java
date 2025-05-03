package mascotas.demo.controller;
import java.util.List;

import mascotas.demo.service.InscripcionService;
import mascotas.demo.model.Inscripcion;
import mascotas.demo.model.ResponseWrapper;
import mascotas.demo.hateoas.InscripcionModelAssembler;

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
@RequestMapping("/inscripcion")
public class InscripcionController {

    private final InscripcionService inscripcionService;
    private final InscripcionModelAssembler assembler;

    public InscripcionController(InscripcionService inscripcionService, InscripcionModelAssembler assembler){
        this.inscripcionService = inscripcionService;
        this.assembler = assembler;
    }

    // obtener todos los registros
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Inscripcion>>> obtInscripcion() {

        log.info("GET /envio - Obteniendo todos los registros");
        List<Inscripcion> inscripcion = inscripcionService.obtenerInscripciones();

        if (inscripcion.isEmpty()) {
            log.warn("No hay registros actualmente");
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(CollectionModel.empty());
        }

        List<EntityModel<Inscripcion>> modelos = inscripcion.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(modelos,
            linkTo(methodOn(InscripcionController.class).obtInscripcion()).withSelfRel()));
    }

    //  obtener registro por id
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Inscripcion>> obtInscripcionId(@PathVariable Long id){

        log.info("GET /envio/{} - Obtener registro por id");
        Inscripcion inscripcion = inscripcionService.obtenerInscripcionId(id);
        return ResponseEntity.ok(assembler.toModel(inscripcion));
    }

    // eliminar registro
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarInscripcion(@PathVariable Long id) {
        
        log.warn("DELETE /envio/{} - Eliminando registro", id);
        inscripcionService.eliminarInscripcion(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Inscripcion eliminado exitosamente",
                        0,
                        null));
    }

    // modificar un registro por id
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Inscripcion>> actualizarInscripcion(@PathVariable Long id, @Valid @RequestBody Inscripcion inscripcionAct) {

        log.info("PUT /envio/{} - Actualizando registro", id);
        Inscripcion registro = inscripcionService.actualizarInscripcion(id, inscripcionAct);

        return ResponseEntity.ok(assembler.toModel(registro));
    }

    // crear registro nuevo
    @PostMapping
    public ResponseEntity<EntityModel<Inscripcion>> crearInscripcion(@Valid @RequestBody Inscripcion inscripcion) {
        
        log.info("POST /envio - Creando registro: {}", inscripcion.getId());
        Inscripcion registro = inscripcionService.guardarInscripcion(inscripcion);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assembler.toModel(registro));
    }
}
