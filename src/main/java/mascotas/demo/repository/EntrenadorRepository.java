package mascotas.demo.repository;
import mascotas.demo.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrenadorRepository extends JpaRepository<Entrenador, Long>{
    
}
