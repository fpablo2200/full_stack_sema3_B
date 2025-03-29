package mascotas.demo.service;
import mascotas.demo.model.Inscripcion;
import mascotas.demo.model.Participantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class InscripcionService {
    private final List<Inscripcion> inscripcions = new ArrayList<>();
    
    public InscripcionService(){
        inscripcions.add(new Inscripcion(1L,"Carrera de obstaculos",
            new Participantes(1L,"Duque", "Perro" , 3, "Oscar Antonio")));
        inscripcions.add(new Inscripcion(2L,"Carrera de obstaculos febrero",
            new Participantes(3L,"Henrique", "Perro" , 4, "Hector Olguin")));
        inscripcions.add(new Inscripcion(3L,"Carrera de obstaculos Marzo",
            new Participantes(2L,"MaoMao", "Gato" , 4, "Miranda Barros")));
        inscripcions.add(new Inscripcion(4L,"Carrera de obstaculos abril",
            new Participantes(4L,"Miguel", "Perro" , 8, "Felipa Acosta")));

    }

    public List<Inscripcion> obtenerInscripciones(){
        return inscripcions;
    }

    public Optional<Inscripcion> obtenerInscripcionId(Long id){
        return inscripcions.stream().filter(p -> p.getId() == id).findFirst();
    }
}
    

