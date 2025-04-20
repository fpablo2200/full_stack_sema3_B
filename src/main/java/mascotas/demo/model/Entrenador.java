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
@Table(name = "entrenador")
public class Entrenador {
    
    @Id
    @NotNull(message = "El ID no puede ser nulo")
    @Positive(message = "El ID debe ser positivo")
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El dni no puede estar vacío")
    @Size(min = 1, max = 100, message = "El dni debe tener entre 1 y 100 caracteres")
    private String dni;

    @NotBlank(message = "El correo no puede estar vacío")
    @Size(min = 1, max = 100, message = "El correo debe tener entre 1 y 100 caracteres")
    private String correo;

}
