package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Ubicacion;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {

    @Query(value = "SELECT * FROM ubicaciones", nativeQuery = true)
    Collection<Ubicacion> darUbicaciones();

    @Query(value = "SELECT * FROM ubicaciones WHERE id = :id", nativeQuery = true)
    Ubicacion darUbicacionPorId(@Param("id") Long id);

    @Query(value = "SELECT * FROM ubicaciones WHERE direccion = :direccion", nativeQuery = true)
    Collection<Ubicacion> darUbicacionesPorDireccion(@Param("direccion") String direccion);

    @Query(value = "SELECT * FROM ubicaciones WHERE ciudad = :ciudad", nativeQuery = true)
    Collection<Ubicacion> darUbicacionesPorCiudad(@Param("ciudad") String ciudad);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ubicaciones (direccion, ciudad) VALUES (:direccion, :ciudad)", nativeQuery = true)
    void agregarUbicacion(@Param("direccion") String direccion, @Param("ciudad") String ciudad);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ubicaciones SET direccion = :direccion, ciudad = :ciudad WHERE id = :id", nativeQuery = true)
    void actualizarUbicacion(@Param("id") Long id, @Param("direccion") String direccion, @Param("ciudad") String ciudad);

    @Modifying
    @Transactional

    @Query(value = "DELETE FROM ubicaciones WHERE id = :id", nativeQuery = true)
    void eliminarUbicacion(@Param("id") Long id);
    
}
