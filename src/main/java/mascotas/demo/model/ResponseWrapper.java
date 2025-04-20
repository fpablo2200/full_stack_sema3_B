package mascotas.demo.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDateTime;

@JsonPropertyOrder({ "status", "registros", "timestamp", "data" })
public class ResponseWrapper<T> {
    
    private String status;
    private int registros;
    private LocalDateTime timestamp;
    private List<T> data;

    public ResponseWrapper(String status, int registros, List<T> data) {
        this.status = status;
        this.registros = registros;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRegistros() {
        return registros;
    }

    public void setRegistros(int registros) {
        this.registros = registros;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
    
}
