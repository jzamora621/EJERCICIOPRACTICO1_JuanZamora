package practica2.controller; // Declara el paquete al que pertenece esta clase.

import practica2.domain.Arbol; // Importa la clase Arbol del paquete domain.
import practica2.service.ArbolService; // Importa la interfaz ArbolService del paquete service.
import practica2.service.Impl.FirebaseStorageServiceImpl; // Importa la implementación FirebaseStorageServiceImpl del paquete service.Impl.
import lombok.extern.slf4j.Slf4j; // Importa la anotación Slf4j de Lombok para logging.
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación Autowired de Spring para inyección de dependencias.
import org.springframework.stereotype.Controller; // Importa la anotación Controller de Spring para marcar esta clase como un controlador.
import org.springframework.ui.Model; // Importa la interfaz Model de Spring para pasar datos a la vista.
import org.springframework.web.bind.annotation.GetMapping; // Importa la anotación GetMapping de Spring para mapear solicitudes GET.
import org.springframework.web.bind.annotation.PostMapping; // Importa la anotación PostMapping de Spring para mapear solicitudes POST.
import org.springframework.web.bind.annotation.RequestMapping; // Importa la anotación RequestMapping de Spring para mapear solicitudes a un path base.
import org.springframework.web.bind.annotation.RequestParam; // Importa la anotación RequestParam de Spring para obtener parámetros de la solicitud.
import org.springframework.web.bind.annotation.PathVariable; // Importa la anotación PathVariable de Spring para obtener variables de la URL.
import org.springframework.web.multipart.MultipartFile; // Importa la interfaz MultipartFile de Spring para manejar archivos subidos.

@Controller // Marca esta clase como un controlador de Spring.
@Slf4j // Habilita el logging con Slf4j.
@RequestMapping("/arbol") // Mapea todas las solicitudes a esta clase con el path base "/arbol".
public class ArbolController { // Declara la clase ArbolController.

    @Autowired // Inyecta una instancia de ArbolService.
    private ArbolService arbolService; // Declara una variable de instancia para ArbolService.

    @GetMapping("/listado") // Mapea las solicitudes GET a "/arbol/listado".
    public String inicio(Model model) { // Define el método inicio que recibe un Model.
        var arbolNuevo = arbolService.getArboles(false); // Obtiene una lista de árboles del servicio.
        log.info("Cantidad de árboles: " + arbolNuevo.size()); // Registra la cantidad de árboles obtenidos.
        model.addAttribute("arboles", arbolNuevo); // Agrega la lista de árboles al modelo.
        model.addAttribute("totalArboles", arbolNuevo.size()); // Agrega la cantidad total de árboles al modelo.
        return "arbol/listado"; // Retorna el nombre de la vista "arbol/listado".
    }

    @GetMapping("/eliminar/{id_arbol}") // Mapea las solicitudes GET a "/arbol/eliminar/{id_arbol}".
    public String eliminar(Arbol arbol) { // Define el método eliminar que recibe un objeto Arbol.
        arbolService.delete(arbol); // Llama al servicio para eliminar el árbol.
        return "redirect:/arbol/listado"; // Redirige a "/arbol/listado".
    }

    @GetMapping("/modificar/{id_arbol}") // Mapea las solicitudes GET a "/arbol/modificar/{id_arbol}".
    public String modificar(Arbol arbol, Model model) { // Define el método modificar que recibe un objeto Arbol y un Model.
        arbol = arbolService.getArbol(arbol); // Obtiene el árbol del servicio.
        model.addAttribute("arbol", arbol); // Agrega el árbol al modelo.
        return "arbol/modificar"; // Retorna el nombre de la vista "arbol/modificar".
    }

    @PostMapping("/modificar") // Mapea las solicitudes POST a "/arbol/modificar".
    public String modificarGuardar(Arbol arbol, // Define el método modificarGuardar que recibe un objeto Arbol.
        @RequestParam(name = "imagenFile", required = false) MultipartFile imagenFile) { // Recibe un archivo MultipartFile opcional.
        if (imagenFile != null && !imagenFile.isEmpty()) { // Verifica si se subió un archivo.
            // Solo procesa la imagen si se ha subido una nueva
            arbol.setRuta_imagen( // Establece la ruta de la imagen del árbol.
                firebaseStorageServiceImpl.cargaImagen(imagenFile, "arbol", arbol.getId_arbol())); // Llama al servicio para cargar la imagen.
        } else { // Si no se subió un archivo.
            // Mantener la imagen anterior si no se sube una nueva
            Arbol arbolExistente = arbolService.getArbol(arbol); // Obtiene el árbol existente del servicio.
            if (arbolExistente != null && arbolExistente.getRuta_imagen() != null) { // Verifica si el árbol existente tiene una imagen.
                arbol.setRuta_imagen(arbolExistente.getRuta_imagen()); // Mantiene la ruta de la imagen existente.
            }
        }
        arbolService.save(arbol); // Guarda el árbol actualizado.
        return "redirect:/arbol/listado"; // Redirige a "/arbol/listado".
    }

    @Autowired // Inyecta una instancia de FirebaseStorageServiceImpl.
    private FirebaseStorageServiceImpl firebaseStorageServiceImpl; // Declara una variable de instancia para FirebaseStorageServiceImpl.

    @PostMapping("/guardar") // Mapea las solicitudes POST a "/arbol/guardar".
    public String arbolGuardar(Arbol arbol, // Define el método arbolGuardar que recibe un objeto Arbol.
        @RequestParam("imagenFile") MultipartFile imagenFile) { // Recibe un archivo MultipartFile.
        if (!imagenFile.isEmpty()) { // Verifica si se subió un archivo.
            arbolService.save(arbol); // Guarda el árbol.
            arbol.setRuta_imagen( // Establece la ruta de la imagen del árbol.
                firebaseStorageServiceImpl.cargaImagen(imagenFile, "arbol", arbol.getId_arbol())); // Llama al servicio para cargar la imagen.
        }
        arbolService.save(arbol); // Guarda el árbol.
        return "redirect:/arbol/listado"; // Redirige a "/arbol/listado".
    }
}