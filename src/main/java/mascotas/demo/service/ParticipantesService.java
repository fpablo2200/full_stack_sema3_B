package mascotas.demo.service;
import mascotas.demo.model.Participantes;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ParticipantesService {
    private final List<Participantes> participantes = new ArrayList<>();
    
    public ParticipantesService(){
        participantes.add(new Participantes(1L,"Duque", "Perro" , 3, "Oscar Antonio"));
        participantes.add(new Participantes(2L,"MaoMao", "Gato" , 4, "Miranda Barros"));
        participantes.add(new Participantes(3L,"Henrique", "Perro" , 4, "Hector Olguin"));
        participantes.add(new Participantes(4L,"Miguel", "Perro" , 8, "Felipa Acosta"));
        participantes.add(new Participantes(5L,"Negro", "Gato" , 10, "Julieta Herrera"));
    
    }

    public List<Participantes> obtenerParticipantes(){
        return participantes;
    }

    public Optional<Participantes> obtenerParticipanteId(Long id){
        return participantes.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
    
}
