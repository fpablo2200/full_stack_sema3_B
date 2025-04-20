package mascotas.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntrenadorNotFoundException extends RuntimeException {
    
    public EntrenadorNotFoundException(Long id){
        super("No existe el envio con id: "+ id);
    }
    
}
