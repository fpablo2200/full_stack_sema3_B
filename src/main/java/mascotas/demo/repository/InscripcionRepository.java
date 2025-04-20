package mascotas.demo.repository;
import mascotas.demo.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long>{
    
}
