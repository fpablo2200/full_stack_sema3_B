package mascotas.demo;

import mascotas.demo.controller.EventoController;
import mascotas.demo.model.Evento;
import mascotas.demo.service.EventoService;
import mascotas.demo.hateoas.EventoModelAssembler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

@WebMvcTest(EventoController.class)
public class EventoControllerTest {
        
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean

    private EventoService eventoService;
    @SuppressWarnings("removal")
    @MockBean

    private EventoModelAssembler eventoAssambler;

    @Test
    @WithMockUser(username = "test", password = "qwerasdf", roles = { "ADMIN" })
    public void testObtenerPorId() throws Exception {

        Evento evento = new Evento(1L, "Feria de Mascotas", "Evento para adopciones y stands veterinarios", "2025-08-15", "Plaza Norte", "Feria");

        EntityModel<Evento> eventoModel = EntityModel.of(evento,
                linkTo(methodOn(EventoController.class).obtEventoId(1L)).withSelfRel(),
                linkTo(methodOn(EventoController.class).eliminarEvento(1L)).withRel("delete"),
                linkTo(methodOn(EventoController.class).actualizarEvento(1L, null)).withRel("update"),
                linkTo(methodOn(EventoController.class).obtEventos()).withRel("all"));

        when(eventoService.obtenerEventoId(1L)).thenReturn(evento);
        when(eventoAssambler.toModel(evento)).thenReturn(eventoModel);

        mockMvc.perform(get("/eventos/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombreEvento").value("Feria de Mascotas"))
                .andExpect(jsonPath("$.descripcion").value("Evento para adopciones y stands veterinarios"))
                .andExpect(jsonPath("$.fecha").value("2025-08-15"))
                .andExpect(jsonPath("$.lugar").value("Plaza Norte"))
                .andExpect(jsonPath("$.tipoevento").value("Feria"))
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$._links.delete.href").exists())
                .andExpect(jsonPath("$._links.update.href").exists());
    }


    @Test
    @WithMockUser(username = "test", password = "qwerasdf", roles = { "ADMIN" })
    public void testObtenerTodosLosEnvios_conResultados() throws Exception {
        Evento evento = new Evento(1L, "Feria de Mascotas", "Evento para adopciones y stands veterinarios", "2025-08-15", "Plaza Norte", "Feria");
        List<Evento> lista = List.of(evento);
    
        EntityModel<Evento> eventoModel = EntityModel.of(evento,
                linkTo(methodOn(EventoController.class).obtEventoId(1L)).withSelfRel());
    
        when(eventoService.obtenerEvents()).thenReturn(lista);
        when(eventoAssambler.toModel(evento)).thenReturn(eventoModel);
    
        mockMvc.perform(get("/eventos")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.eventoList[0].id").value(1))
                .andExpect(jsonPath("$._links.self.href").exists());
    }
}
