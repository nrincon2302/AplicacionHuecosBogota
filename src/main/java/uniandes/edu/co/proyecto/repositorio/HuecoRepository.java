package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Hueco;

public interface HuecoRepository extends JpaRepository<Hueco, Long> {

    @Query(value = "SELECT * FROM huecos", nativeQuery = true)
    Collection<Hueco> darHuecos();

    @Query(value = "SELECT * FROM huecos WHERE id = :id", nativeQuery = true)
    Hueco darHuecoPorId(@Param("id") Long id);

    @Query(value = "SELECT * FROM huecos WHERE ubicacion = :ubicacion", nativeQuery = true)
    Collection<Hueco> darHuecosPorUbicacion(@Param("ubicacion") Long ubicacion);

    @Query(value = "SELECT * FROM huecos WHERE estado = :estado", nativeQuery = true)
    Collection<Hueco> darHuecosPorEstado(@Param("estado") String estado);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO huecos (id, volumen, estado, ubicacion) VALUES (hueco_sequence.nextval, :volumen, :estado, :ubicacion)", nativeQuery = true)
    void agregarHueco(@Param("volumen") Float volumen, @Param("estado") String estado, @Param("ubicacion") Long ubicacion);

    @Modifying
    @Transactional
    @Query(value = "UPDATE huecos SET volumen = :volumen, estado = :estado, ubicacion = :ubicacion WHERE id = :id", nativeQuery = true)
    void actualizarHueco(@Param("id") Long id, @Param("volumen") Float volumen, @Param("estado") String estado, @Param("ubicacion") Long ubicacion);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM huecos WHERE id = :id", nativeQuery = true)
    void eliminarHueco(@Param("id") Long id);
    
}
