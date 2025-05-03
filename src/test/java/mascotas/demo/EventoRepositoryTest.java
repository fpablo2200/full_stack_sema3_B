package mascotas.demo;

import mascotas.demo.model.Evento;
import mascotas.demo.repository.EventoRepository;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@DataJpaTest
public class EventoRepositoryTest {
        
    @Autowired
    private EventoRepository eventoRepository;

    @Test
    public void testSaveFind() {
        Evento evento = new Evento(2L, "Feria de Mascotas", "Evento para adopciones y stands veterinarios", "2025-08-15", "Plaza Norte", "Feria");

        eventoRepository.save(evento);

        Optional<Evento> resultado = eventoRepository.findById(2L);

        assertTrue(resultado.isPresent());
        assertEquals("Feria de Mascotas", resultado.get().getNombreEvento());
        assertEquals("Evento para adopciones y stands veterinarios", resultado.get().getDescripcion());
        assertEquals("2025-08-15", resultado.get().getFecha());
        assertEquals("Plaza Norte", resultado.get().getLugar());
        assertEquals("Feria", resultado.get().getTipoevento());
    }

    @Test
    public void testUpdateEnvio() {
        Evento evento = new Evento(1L, "Concurso Canino", "Evento para adopciones y stands veterinarios", "2025-07-10", "Parque Central", "Competencia");
        eventoRepository.save(evento);

        evento.setNombreEvento("Expo Animalia");
        evento.setLugar("Centro de Eventos Sur");
        eventoRepository.save(evento);

        Optional<Evento> resultado = eventoRepository.findById(1L);
        assertTrue(resultado.isPresent());
        assertEquals("Expo Animalia", resultado.get().getNombreEvento());
        assertEquals("Centro de Eventos Sur", resultado.get().getLugar());
    }

    @Test
    public void testDeleteEnvio() {
        Evento evento = new Evento(1L, "Concurso Canino", "Evento para adopciones y stands veterinarios", "2025-07-10", "Parque Central", "Competencia");
        eventoRepository.save(evento);

        eventoRepository.deleteById(1L);

        Optional<Evento> resultado = eventoRepository.findById(1L);
        assertFalse(resultado.isPresent());
    }

    @Test
    public void testFindAllEnvios() {
        eventoRepository.save(new Evento(1L, "Concurso Canino", "Evento para adopciones y stands veterinarios", "2025-07-10", "Parque Central", "Competencia"));
        eventoRepository.save(new Evento(2L, "Feria de Mascotas", "Evento para adopciones y stands veterinarios", "2025-08-15", "Plaza Norte", "Feria"));
    
        List<Evento> evento = eventoRepository.findAll();
        assertEquals(2, evento.size());
    }
    
}
