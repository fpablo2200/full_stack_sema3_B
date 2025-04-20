package mascotas.demo.repository;
import mascotas.demo.model.Participantes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantesRepository extends JpaRepository<Participantes, Long>{
    
}
