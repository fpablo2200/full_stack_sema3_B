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
@Table(name = "evento")
public class Evento {

    @Id
    @NotNull(message = "El ID no puede ser nulo")
    @Positive(message = "El ID debe ser positivo")
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    private String nombreEvento;

    @NotBlank(message = "El descripción no puede estar vacío")
    @Size(min = 1, max = 100, message = "El descripción debe tener entre 1 y 100 caracteres")
    private String descripcion;

    @NotBlank(message = "La fecha no puede estar vacía")
    @Size(min = 1, max = 20, message = "rango de caracteres entre 1 y 20 caracteres")
    private String fecha;

    @NotBlank(message = "El lugar no puede estar vacío")
    @Size(min = 1, max = 100, message = "El lugar debe tener entre 1 y 100 caracteres")
    private String lugar;

    @NotBlank(message = "El tipo de evento no puede estar vacío")
    @Size(min = 1, max = 100, message = "El tipo de evento debe tener entre 1 y 100 caracteres")
    private String tipoevento;
}
