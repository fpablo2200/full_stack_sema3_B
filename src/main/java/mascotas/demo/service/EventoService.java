package mascotas.demo.service;
import java.util.List;

import mascotas.demo.model.Evento;
import mascotas.demo.repository.EventoRepository;
import mascotas.demo.exception.EventoNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repositorio;

    // listar todo
    public List<Evento> obtenerEvents() {
        return repositorio.findAll(Sort.by("id"));
    }

    // buscar por id
    public Evento obtenerEventoId(Long id) {
        return repositorio.findById(id).orElseThrow(() -> new EventoNotFoundException(id));
    }

    // guardar envio
    public Evento guardarEvento(Evento evento) {
        if (repositorio.existsById(evento.getId())) {
                throw new IllegalArgumentException("Ya existe un registro con el mismo ID" + evento.getId());
        }
        return repositorio.save(evento);
    }

    // actualizar por id
    public Evento actualizarEvento(Long id, Evento eventoAct) {
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
        Evento eventoEncontrado = repositorio.findById(id).orElseThrow(() -> new EventoNotFoundException(id));
        repositorio.delete(eventoEncontrado);
    }      
}
