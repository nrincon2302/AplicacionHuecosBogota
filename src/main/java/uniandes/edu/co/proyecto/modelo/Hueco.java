package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="huecos")
public class Hueco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float volumen;
    private String estado;
    @OneToOne
    @JoinColumn(name = "ubicacion", referencedColumnName = "id")
    private Ubicacion ubicacion;

    public Hueco() {}

    public Hueco(Float volumen, String estado, Ubicacion ubicacion) {
        this.volumen = volumen;
        this.estado = estado;
        this.ubicacion = ubicacion;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getVolumen() {
        return volumen;
    }

    public void setVolumen(Float volumen) {
        this.volumen = volumen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
    
}
