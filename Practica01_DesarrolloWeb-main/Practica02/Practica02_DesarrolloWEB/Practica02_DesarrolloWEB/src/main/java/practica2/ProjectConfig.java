/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package practica2; 

/**
 *
 * @author sebastian // Indica el autor de la clase.
 */
import java.util.Locale; // Importa la clase Locale para manejar configuraciones regionales.
import org.springframework.context.MessageSource; // Importa la interfaz MessageSource para resolución de mensajes.
import org.springframework.context.annotation.Bean; // Importa la anotación Bean para definir beans de Spring.
import org.springframework.context.annotation.Configuration; // Importa la anotación Configuration para clases de configuración.
import org.springframework.context.support.ResourceBundleMessageSource; // Importa ResourceBundleMessageSource para cargar mensajes desde archivos .properties.
import org.springframework.web.servlet.LocaleResolver; // Importa LocaleResolver para determinar la configuración regional del usuario.
import org.springframework.web.servlet.config.annotation.InterceptorRegistry; // Importa InterceptorRegistry para registrar interceptores.
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; // Importa WebMvcConfigurer para configurar Spring MVC.
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor; // Importa LocaleChangeInterceptor para cambiar la configuración regional.
import org.springframework.web.servlet.i18n.SessionLocaleResolver; // Importa SessionLocaleResolver para almacenar la configuración regional en la sesión.

@Configuration // Indica que esta clase es una clase de configuración de Spring.
public class ProjectConfig implements WebMvcConfigurer { // Declara la clase ProjectConfig que implementa WebMvcConfigurer.
    /* Los siguientes métodos son para incorporar el tema de internacionalización en el proyecto */

    /* localeResolver se utiliza para crear una sesión de cambio de idioma*/
    @Bean // Indica que este método define un bean de Spring.
    public LocaleResolver localeResolver() { // Define un método que retorna un LocaleResolver.
        var slr = new SessionLocaleResolver(); // Crea una instancia de SessionLocaleResolver.
        slr.setDefaultLocale(Locale.getDefault()); // Establece la configuración regional predeterminada a la del sistema.
        slr.setLocaleAttributeName("session.current.locale"); // Establece el nombre del atributo de sesión para el locale.
        slr.setTimeZoneAttributeName("session.current.timezone"); // Establece el nombre del atributo de sesión para la zona horaria.
        return slr; // Retorna la instancia de SessionLocaleResolver.
    }

    /* localeChangeInterceptor se utiliza para crear un interceptor de cambio de idioma*/
    @Bean // Indica que este método define un bean de Spring.
    public LocaleChangeInterceptor localeChangeInterceptor() { // Define un método que retorna un LocaleChangeInterceptor.
        var lci = new LocaleChangeInterceptor(); // Crea una instancia de LocaleChangeInterceptor.
        lci.setParamName("lang"); // Establece el nombre del parámetro para cambiar el locale a "lang".
        return lci; // Retorna la instancia de LocaleChangeInterceptor.
    }

    @Override // Indica que este método sobreescribe un método de la interfaz WebMvcConfigurer.
    public void addInterceptors(InterceptorRegistry registro) { // Define el método para agregar interceptores.
        registro.addInterceptor(localeChangeInterceptor()); // Agrega el LocaleChangeInterceptor al registro.
    }

    //Bean para poder acceder a los Messages.properties en código...
    @Bean("messageSource") // Método que define un bean de Spring con el nombre "messageSource".
    public MessageSource messageSource() { // Define un método que retorna un MessageSource.
        ResourceBundleMessageSource messageSource= new ResourceBundleMessageSource(); // Crea una instancia de ResourceBundleMessageSource.
        messageSource.setBasenames("messages"); // Establece el nombre base de los archivos de mensajes a "messages".
        messageSource.setDefaultEncoding("UTF-8"); // Establece la codificación predeterminada a UTF-8.
        return messageSource; // Retorna la instancia de ResourceBundleMessageSource.
    }
}
