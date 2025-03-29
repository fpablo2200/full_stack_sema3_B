package mascotas.demo.model;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento {

    private Long id;
    private String nombreEvento;
    private String descripcion;
    private String fecha;
    private String lugar;
    private String tipoevento;
}
