package mascotas.demo.hateoas;

import mascotas.demo.controller.EventoController;
import mascotas.demo.model.Evento;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*; 
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class EventoModelAssembler implements RepresentationModelAssembler<Evento, EntityModel<Evento>> {
            
    @Override
    public EntityModel<Evento> toModel(Evento evento) {
        return EntityModel.of(
            evento, 
            linkTo(methodOn(EventoController.class)
                .obtEventos())
                .withRel("all"),

            linkTo(methodOn(EventoController.class)
                .obtEventoId(evento.getId()))
                .withSelfRel(),

            linkTo(methodOn(EventoController.class)
                .eliminarEvento(evento.getId()))
                .withRel("delete"),

            linkTo(methodOn(EventoController.class)
                .actualizarEvento(evento.getId(), null))
                .withRel("update")
        );
    }
}
