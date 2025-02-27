package practica2.service; // Declara el paquete al que pertenece esta interfaz.

import practica2.domain.Arbol; // Importa la clase Arbol del paquete domain.
import java.util.List; // Importa la interfaz List de Java para manejar listas.

public interface ArbolService { // Declara la interfaz ArbolService.

    // Se obtiene un listado de categorias en un List
    public List<Arbol> getArboles(boolean activos); // Declara un método que retorna una lista de Arboles, filtrando por estado activo.

    public Arbol getArbol(Arbol arbol); // Declara un método que retorna un Arbol específico.

    public void save(Arbol arbol); // Declara un método para guardar un Arbol.

    public void delete(Arbol arbol); // Declara un método para eliminar un Arbol.
}