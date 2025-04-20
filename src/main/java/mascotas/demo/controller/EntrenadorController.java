package mascotas.demo.controller;
import java.util.List;

import mascotas.demo.model.Entrenador;
import mascotas.demo.service.EntrenadorService;
import mascotas.demo.model.ResponseWrapper;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entrenador")
public class EntrenadorController {
    private final EntrenadorService entrenadorService;

    public EntrenadorController(EntrenadorService entrenadorService){
        this.entrenadorService = entrenadorService;
    }

    @GetMapping
    public ResponseEntity<?> obtEntrenador() {
        List<Entrenador> entrenador = entrenadorService.obtenerEnentrenadores();

        if (entrenador.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entrenador no encontrado");
        }

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "OK",
                        entrenador.size(),
                        entrenador));
    }



    @GetMapping("/{id}")
    public Entrenador obtEntrenadorId(@PathVariable Long id){
        return entrenadorService.obtenerEntrenadorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarEntrenador(@PathVariable Long id) {
        entrenadorService.eliminarEnentrenador(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Entrenador eliminado exitosamente",
                        0,
                        null));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<Entrenador>> crearEntrenador(@Valid @RequestBody Entrenador entrenador) {
        Entrenador registro = entrenadorService.guardarEntrenador(entrenador);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(
                        "Entrenador creado exitosamente",
                        1,
                        List.of(registro)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Entrenador>> actualizarEntrenador(@PathVariable Long id,
            @Valid @RequestBody Entrenador entrenadorAct) {

        Entrenador registro = entrenadorService.actualizarEntrenador(id, entrenadorAct);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Entrenador actualizado",
                        1,
                        List.of(registro)));
    }
    
}
