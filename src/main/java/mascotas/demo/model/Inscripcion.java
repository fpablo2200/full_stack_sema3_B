package mascotas.demo.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class Inscripcion {
    private long id;
    private String evento;
    private Participantes participantes;
}
