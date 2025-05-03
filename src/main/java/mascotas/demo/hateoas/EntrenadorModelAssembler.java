package mascotas.demo.hateoas;

import mascotas.demo.controller.EntrenadorController;
import mascotas.demo.model.Entrenador;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*; 
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class EntrenadorModelAssembler implements RepresentationModelAssembler<Entrenador, EntityModel<Entrenador>> {
        
    @Override
    public EntityModel<Entrenador> toModel(Entrenador entrenador) {
        return EntityModel.of(
            entrenador, 
            linkTo(methodOn(EntrenadorController.class)
                .obtEntrenador())
                .withRel("all"),

            linkTo(methodOn(EntrenadorController.class)
                .obtEntrenadorId(entrenador.getId()))
                .withSelfRel(),

            linkTo(methodOn(EntrenadorController.class)
                .eliminarEntrenador(entrenador.getId()))
                .withRel("delete"),

            linkTo(methodOn(EntrenadorController.class)
                .actualizarEntrenador(entrenador.getId(), null))
                .withRel("update")
        );
    }
}
