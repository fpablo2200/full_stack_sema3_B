package mascotas.demo.hateoas;

import mascotas.demo.controller.ParticipantesController;
import mascotas.demo.model.Participantes;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*; 
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ParticipantesModelAssembler implements RepresentationModelAssembler<Participantes, EntityModel<Participantes>>  {
    @Override
    public EntityModel<Participantes> toModel(Participantes participante) {
        return EntityModel.of(
            participante, 
            linkTo(methodOn(ParticipantesController.class)
                .obtParticipantes())
                .withRel("all"),

            linkTo(methodOn(ParticipantesController.class)
                .obtParticipantesId(participante.getId()))
                .withSelfRel(),

            linkTo(methodOn(ParticipantesController.class)
                .eliminarParticipantes(participante.getId()))
                .withRel("delete"),

            linkTo(methodOn(ParticipantesController.class)
                .actualizarParticipantes(participante.getId(), null))
                .withRel("update")
        );
    }
}
