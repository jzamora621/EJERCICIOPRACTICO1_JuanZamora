package practica2.dao; // Declara el paquete al que pertenece esta interfaz.

import practica2.domain.Arbol; // Importa la clase Arbol del paquete domain.
import org.springframework.data.jpa.repository.JpaRepository; // Importa la interfaz JpaRepository de Spring Data JPA.
import java.util.List; // Importa la interfaz List de Java para manejar listas.

public interface ArbolDao extends JpaRepository<Arbol, Long> { // Declara la interfaz ArbolDao que extiende JpaRepository.
    // JpaRepository<Arbol, Long> significa que esta interfaz maneja entidades Arbol y usa Long como tipo de ID.
    List<Arbol> findByActivoTrue(); // Declara un método que busca y retorna una lista de Arboles donde el atributo 'activo' es verdadero.
}