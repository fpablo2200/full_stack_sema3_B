package mascotas.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor 
@Entity
@Table(name = "inscripcion")
public class Inscripcion {

    @Id
    @NotNull(message = "El ID no puede ser nulo")
    @Positive(message = "El ID debe ser positivo")
    private long id;

    @NotNull(message = "El ID  no puede ser nulo")
    @Positive(message = "El ID  debe ser positivo")
    private int idEvento;

    @NotNull(message = "El ID no puede ser nulo")
    @Positive(message = "El ID debe ser positivo")
    private int idEntrenador;

    @NotNull(message = "El ID no puede ser nulo")
    @Positive(message = "El ID debe ser positivo")
    private int idParticipante;

    @NotBlank(message = "La fecha no puede estar vacía")
    @Size(min = 1, max = 20, message = "rango de caracteres entre 1 y 20 caracteres")
    private String fechaInscripcion;


}
