package mascotas.demo.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Participantes {
    private Long id;
    private String nombreMascota;
    private String tipoMascota;
    private int edad;
    private String nombreDueno;
    
}
