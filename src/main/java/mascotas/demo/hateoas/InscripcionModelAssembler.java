package mascotas.demo.hateoas;

import mascotas.demo.controller.InscripcionController;
import mascotas.demo.model.Inscripcion;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*; 
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class InscripcionModelAssembler implements RepresentationModelAssembler<Inscripcion, EntityModel<Inscripcion>> {
            
    @Override
    public EntityModel<Inscripcion> toModel(Inscripcion inscripcion) {
        return EntityModel.of(
            inscripcion, 
            linkTo(methodOn(InscripcionController.class)
                .obtInscripcion())
                .withRel("all"),

            linkTo(methodOn(InscripcionController.class)
                .obtInscripcionId(inscripcion.getId()))
                .withSelfRel(),

            linkTo(methodOn(InscripcionController.class)
                .eliminarInscripcion(inscripcion.getId()))
                .withRel("delete"),

            linkTo(methodOn(InscripcionController.class)
                .actualizarInscripcion(inscripcion.getId(), null))
                .withRel("update")
        );
    }
}
