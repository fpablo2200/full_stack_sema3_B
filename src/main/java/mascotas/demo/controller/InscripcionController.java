package mascotas.demo.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import mascotas.demo.service.InscripcionService;
import mascotas.demo.model.Inscripcion;
import mascotas.demo.model.ResponseWrapper;

import java.util.List;

@RestController
@RequestMapping("/inscripcion")
public class InscripcionController {
    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService){
        this.inscripcionService = inscripcionService;
    }

    @GetMapping
    public ResponseEntity<?> obtInscripcion() {
        List<Inscripcion> inscripcion = inscripcionService.obtenerInscripciones();

        if (inscripcion.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inscripcion no encontrada");
        }

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "OK",
                        inscripcion.size(),
                        inscripcion));
    }



    @GetMapping("/{id}")
    public Inscripcion obtInscripcionId(@PathVariable Long id){
        return inscripcionService.obtenerInscripcionId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarInscripcion(@PathVariable Long id) {
        inscripcionService.eliminarInscripcion(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Inscripcion eliminado exitosamente",
                        0,
                        null));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<Inscripcion>> crearInscripcion(@Valid @RequestBody Inscripcion inscripcion) {
        Inscripcion registro = inscripcionService.guardarInscripcion(inscripcion);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(
                        "Inscripcion creado exitosamente",
                        1,
                        List.of(registro)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Inscripcion>> actualizarInscripcion(@PathVariable Long id,
            @Valid @RequestBody Inscripcion inscripcionAct) {

        Inscripcion registro = inscripcionService.actualizarInscripcion(id, inscripcionAct);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Inscripcion actualizada",
                        1,
                        List.of(registro)));
    }
}
