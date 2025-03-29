package mascotas.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mascotas.demo.service.InscripcionService;
import mascotas.demo.model.Inscripcion;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/inscripcion")
public class InscripcionController {
    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService){
        this.inscripcionService = inscripcionService;
    }

    @GetMapping
    public List<Inscripcion> obtInscripcions(){
        return inscripcionService.obtenerInscripciones();
    }

    @GetMapping("/{id}")
    public Optional<Inscripcion> obtInscripcionId(@PathVariable Long id){
        return inscripcionService.obtenerInscripcionId(id);
    }
    
}
