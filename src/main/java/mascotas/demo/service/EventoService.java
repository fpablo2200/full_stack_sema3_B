package mascotas.demo.service;
import mascotas.demo.model.Evento;
import mascotas.demo.model.Inscripcion;
import mascotas.demo.model.Participantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EventoService {
    private final List<Evento> evento = new ArrayList<>();
    
    public EventoService(){
        evento.add(new Evento(1L,"Campeonato 2025", "carrera de obstaculos nacional para mascotas" , 
            "05/04/2025", "centro recreativo para mascotas" , "Carrera de obstaculos"));

        evento.add(new Evento(2L,"Exhibición Canina", "Exhibiciones de perros" , 
            "10/05/2025", "centro recreativo para mascotas" ,"Exhibición mascotas"));

        evento.add(new Evento(3L,"Exhibición Gatuna", "Exhibiciones de gatos" , 
            "02/06/2025", "centro recreativo para mascotas" , "Exhibición mascotas"));

        evento.add(new Evento(4L,"Finales 2025 mascotas", "carrera de obstaculos nacional para mascotas" , 
            "27/07/2025", "centro recreativo para mascotas" , "Carrera de obstaculos"));
        
    
    
    }

    public List<Evento> obtenerEventos(){
        return evento;
    }

    public Optional<Evento> obtenerEventoId(Long id){
        return evento.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}
