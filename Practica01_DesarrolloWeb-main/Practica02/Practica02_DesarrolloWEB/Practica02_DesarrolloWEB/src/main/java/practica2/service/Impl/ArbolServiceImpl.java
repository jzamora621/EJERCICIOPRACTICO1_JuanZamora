package practica2.service.Impl; // Declara el paquete al que pertenece esta clase.

import practica2.dao.ArbolDao; // Importa la interfaz ArbolDao del paquete dao.
import practica2.domain.Arbol; // Importa la clase Arbol del paquete domain.
import practica2.service.ArbolService; // Importa la interfaz ArbolService del paquete service.
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación Autowired de Spring para inyección de dependencias.
import org.springframework.stereotype.Service; // Importa la anotación Service de Spring para marcar esta clase como un servicio.
import org.springframework.transaction.annotation.Transactional; // Importa la anotación Transactional de Spring para manejo de transacciones.

import java.util.List; // Importa la interfaz List de Java para manejar listas.
import java.util.Optional; // Importa la clase Optional de Java para manejar valores opcionales.

@Service // Marca esta clase como un servicio de Spring.
public class ArbolServiceImpl implements ArbolService { // Declara la clase ArbolServiceImpl que implementa ArbolService.

    @Autowired // Inyecta una instancia de ArbolDao.
    private ArbolDao arbolDao; // Declara una variable de instancia para ArbolDao.

    @Override // Indica que este método sobreescribe un método de la interfaz ArbolService.
    @Transactional(readOnly = true) // Marca este método como transaccional y de solo lectura.
    public List<Arbol> getArboles(boolean activos) { // Define el método getArboles que recibe un boolean.
        List<Arbol> lista = arbolDao.findAll(); // Obtiene todos los árboles de la base de datos.
        if (activos) { // Verifica si se deben filtrar los árboles activos.
            lista.removeIf(e -> !e.isActivo()); // Remueve los árboles que no están activos de la lista.
        }
        return lista; // Retorna la lista de árboles.
    }

    @Override // Indica que este método sobreescribe un método de la interfaz ArbolService.
    @Transactional(readOnly = true) // Marca este método como transaccional y de solo lectura.
    public Arbol getArbol(Arbol arbol) { // Define el método getArbol que recibe un objeto Arbol.
        Optional<Arbol> optionalArbol = arbolDao.findById(arbol.getId_arbol()); // Obtiene un árbol por su ID de la base de datos.
        return optionalArbol.orElse(null); // Retorna el árbol si se encuentra, o null si no.
    }

    @Transactional // Marca este método como transaccional.
    public void delete(Arbol arbol) { // Define el método delete que recibe un objeto Arbol.
        // Use arbolDao to delete the record
        arbolDao.delete(arbol); // Elimina el árbol de la base de datos.
    }

    @Transactional // Marca este método como transaccional.
    public void save(Arbol arbol) { // Define el método save que recibe un objeto Arbol.
        // Use arbolDao to save the object
        arbolDao.save(arbol); // Guarda o actualiza el árbol en la base de datos.
    }
}