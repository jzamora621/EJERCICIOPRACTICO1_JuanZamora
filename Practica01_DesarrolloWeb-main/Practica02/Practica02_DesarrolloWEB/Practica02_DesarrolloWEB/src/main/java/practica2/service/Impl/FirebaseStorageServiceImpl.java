/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package practica2.service.Impl; // Declara el paquete al que pertenece esta clase.

import com.google.auth.Credentials; // Importa la clase Credentials de Google Auth.
import com.google.auth.ServiceAccountSigner; // Importa la interfaz ServiceAccountSigner de Google Auth.
import com.google.auth.oauth2.GoogleCredentials; // Importa la clase GoogleCredentials de Google Auth.
import com.google.cloud.storage.*; // Importa las clases de Google Cloud Storage.
import com.google.cloud.storage.BlobInfo; // Importa la clase BlobInfo de Google Cloud Storage.
import com.google.cloud.storage.Storage; // Importa la clase Storage de Google Cloud Storage.
import com.google.cloud.storage.Storage.SignUrlOption; // Importa la enumeración SignUrlOption de Google Cloud Storage.
import com.google.cloud.storage.StorageOptions; // Importa la clase StorageOptions de Google Cloud Storage.
import practica2.service.FirebaseStorageService; // Importa la interfaz FirebaseStorageService del paquete service.
import java.io.File; // Importa la clase File de Java IO.
import java.io.FileOutputStream; // Importa la clase FileOutputStream de Java IO.
import java.io.IOException; // Importa la clase IOException de Java IO.
import java.nio.file.Files; // Importa la clase Files de Java NIO.
import java.util.concurrent.TimeUnit; // Importa la clase TimeUnit de Java concurrency.
import org.springframework.core.io.ClassPathResource; // Importa la clase ClassPathResource de Spring Core.
import org.springframework.stereotype.Service; // Importa la anotación Service de Spring.
import org.springframework.web.multipart.MultipartFile; // Importa la interfaz MultipartFile de Spring.

@Service // Marca esta clase como un servicio de Spring.
public class FirebaseStorageServiceImpl implements FirebaseStorageService { // Declara la clase FirebaseStorageServiceImpl que implementa FirebaseStorageService.

    @Override // Indica que este método sobreescribe un método de la interfaz FirebaseStorageService.
    public String cargaImagen(MultipartFile archivoLocalCliente, String carpeta, Long id) { // Define el método cargaImagen que recibe un MultipartFile, String y Long.
        try { // Inicia un bloque try-catch para manejar excepciones.
            // El nombre original del archivo local del cliene
            String extension = archivoLocalCliente.getOriginalFilename(); // Obtiene la extensión del archivo original.

            // Se genera el nombre según el código del articulo.
            String fileName = "img" + sacaNumero(id) + extension; // Genera el nombre del archivo usando el ID y la extensión.

            // Se convierte/sube el archivo a un archivo temporal
            File file = this.convertToFile(archivoLocalCliente); // Convierte el MultipartFile a un File temporal.

            // se copia a Firestore y se obtiene el url válido de la imagen (por 10 años)
            String URL = this.uploadFile(file, carpeta, fileName); // Sube el archivo a Firebase Storage y obtiene la URL.

            // Se elimina el archivo temporal cargado desde el cliente
            file.delete(); // Elimina el archivo temporal.

            return URL; // Retorna la URL de la imagen.
        } catch (IOException e) { // Captura excepciones de tipo IOException.
            e.printStackTrace(); // Imprime la traza de la excepción.
            return null; // Retorna null en caso de error.
        }
    }

    private String uploadFile(File file, String carpeta, String fileName) throws IOException { // Define el método uploadFile que recibe un File, String y String.

        ClassPathResource json = new ClassPathResource(rutaJsonFile + File.separator + archivoJsonFile); // Crea un ClassPathResource para el archivo JSON de configuración.
        BlobId blobId = BlobId.of(BucketName, rutaSuperiorStorage + "/" + carpeta + "/" + fileName); // Crea un BlobId para el archivo en Firebase Storage.
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build(); // Crea un BlobInfo para el archivo.

        Credentials credentials = GoogleCredentials.fromStream(json.getInputStream()); // Obtiene las credenciales de Google desde el archivo JSON.
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService(); // Crea un objeto Storage usando las credenciales.
        storage.create(blobInfo, Files.readAllBytes(file.toPath())); // Sube el archivo a Firebase Storage.
        String url = storage.signUrl(blobInfo, 3650, TimeUnit.DAYS, SignUrlOption.signWith((ServiceAccountSigner) credentials)).toString(); // Genera una URL firmada para el archivo.
        return url; // Retorna la URL firmada.
    }

    //Método utilitario que convierte el archivo desde el equipo local del usuario a un archivo temporal en el servidor
    private File convertToFile(MultipartFile archivoLocalCliente) throws IOException { // Define el método convertToFile que recibe un MultipartFile.
        File tempFile = File.createTempFile("img", null); // Crea un archivo temporal.
        try ( // Inicia un bloque try-with-resources para manejar el FileOutputStream.
            FileOutputStream fos = new FileOutputStream(tempFile)) { // Crea un FileOutputStream para el archivo temporal.
            fos.write(archivoLocalCliente.getBytes()); // Escribe los bytes del MultipartFile en el archivo temporal.
            fos.close(); // Cierra el FileOutputStream.
        }
        return tempFile; // Retorna el archivo temporal.
    }

    //Método utilitario para obtener un string con ceros....
    private String sacaNumero(long id) { // Define el método sacaNumero que recibe un long.
        return String.format("%019d", id); // Formatea el ID como una cadena con ceros a la izquierda.
    }
}