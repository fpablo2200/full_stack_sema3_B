package mascotas.demo.controller;

import mascotas.demo.service.EventoService;
import mascotas.demo.model.Evento;
import mascotas.demo.model.ResponseWrapper;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    private final EventoService eventoService;

    public EventoController(EventoService eventoService){
        this.eventoService = eventoService;
    }

    @GetMapping
    public ResponseEntity<?> obtEventos() {
        List<Evento> evento = eventoService.obtenerEvents();

        if (evento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento no encontrado");
        }

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "OK",
                        evento.size(),
                        evento));
    }



    @GetMapping("/{id}")
    public Evento obtEventoId(@PathVariable Long id){
        return eventoService.obtenerEventoId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarEvento(@PathVariable Long id) {
        eventoService.eliminarEvento(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Evento eliminado exitosamente",
                        0,
                        null));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<Evento>> crearEvento(@Valid @RequestBody Evento evento) {
        Evento registro = eventoService.guardarEvento(evento);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(
                        "Evento creado exitosamente",
                        1,
                        List.of(registro)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Evento>> actualizarEvento(@PathVariable Long id,
            @Valid @RequestBody Evento eventoAct) {

        Evento registro = eventoService.actualizarEvento(id, eventoAct);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Evento actualizada",
                        1,
                        List.of(registro)));
    }

}
