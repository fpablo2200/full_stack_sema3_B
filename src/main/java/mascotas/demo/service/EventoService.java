package mascotas.demo.service;
import java.util.List;

import mascotas.demo.model.Evento;
import mascotas.demo.repository.EventoRepository;
import mascotas.demo.exception.EventoNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventoService {

    @Autowired
    private EventoRepository repositorio;

    public EventoService(EventoRepository eventoRepository) {
        this.repositorio = eventoRepository;
    }

    // listar todo
    public List<Evento> obtenerEvents() {
        log.debug("Servicio: obtenerEvents()");
        return repositorio.findAll(Sort.by("id"));
    }

    // buscar por id
    public Evento obtenerEventoId(Long id) {
        log.debug("Servicio: obtenerEventoId({})", id);
        return repositorio.findById(id).orElseThrow(() -> new EventoNotFoundException(id));
    }

    // guardar envio
    public Evento guardarEvento(Evento evento) {
        log.debug("Servicio: guardarEvento({})", evento.getId());
        if (repositorio.existsById(evento.getId())) {
            log.error("Ya existe una registro con ID {}", evento.getId());
                throw new IllegalArgumentException("Ya existe un registro con el mismo ID" + evento.getId());
        }
        return repositorio.save(evento);
    }

    // actualizar por id
    public Evento actualizarEvento(Long id, Evento eventoAct) {
        log.debug("Servicio: actualizarEvento({}, {})", id, eventoAct.getId());
        Evento eventoEncontrado = repositorio.findById(id).orElseThrow(() -> new EventoNotFoundException(id));

        eventoEncontrado.setNombreEvento(eventoAct.getNombreEvento());
        eventoEncontrado.setDescripcion(eventoAct.getDescripcion());
        eventoEncontrado.setFecha(eventoAct.getFecha());
        eventoEncontrado.setLugar(eventoAct.getLugar());
        eventoEncontrado.setTipoevento(eventoAct.getTipoevento());

        return repositorio.save(eventoEncontrado);
    }

    // eliminar 
    public void eliminarEvento(Long id) {
        log.debug("Servicio: eliminarEvento({})", id);
        Evento eventoEncontrado = repositorio.findById(id).orElseThrow(() -> new EventoNotFoundException(id));
        repositorio.delete(eventoEncontrado);
    }      
}
