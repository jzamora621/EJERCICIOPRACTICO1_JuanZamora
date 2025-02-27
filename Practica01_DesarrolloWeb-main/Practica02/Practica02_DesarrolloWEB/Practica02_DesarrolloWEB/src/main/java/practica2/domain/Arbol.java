package practica2.domain; // Declara el paquete al que pertenece esta clase.

import jakarta.persistence.*; // Importa las anotaciones de Jakarta Persistence para mapeo ORM.
import java.io.Serializable; // Importa la interfaz Serializable para permitir la serialización de objetos.
import lombok.Data; // Importa la anotación Data de Lombok para generar automáticamente getters, setters, equals, hashCode y toString.

@Data // Genera automáticamente getters, setters, equals, hashCode y toString usando Lombok.
@Entity // Marca esta clase como una entidad JPA, mapeándola a una tabla en la base de datos.
@Table(name = "arbol") // Especifica el nombre de la tabla en la base de datos como "arbol".
public class Arbol implements Serializable { // Declara la clase Arbol que implementa Serializable.

    private static final long serialVersionUID = 1L; // Declara una constante para la serialización.

    @Id // Marca el atributo id_arbol como la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Especifica que el valor de la clave primaria se genera automáticamente por la base de datos.
    @Column(name = "id_arbol") // Especifica el nombre de la columna en la base de datos como "id_arbol".
    private Long id_arbol; // Declara el atributo id_arbol de tipo Long.
    private String nombre_comun; // Declara el atributo nombre_comun de tipo String.
    private String tipo_flor; // Declara el atributo tipo_flor de tipo String.
    private String dureza_madera; // Declara el atributo dureza_madera de tipo String.
    private double altura_promedio; // Declara el atributo altura_promedio de tipo double.
    private String ruta_imagen; // Declara el atributo ruta_imagen de tipo String.
    public boolean activo; // Declara el atributo activo de tipo boolean.
 
    public Arbol() { 
    }

    public Arbol(String nombre_comun, String tipo_flor, String dureza_madera, double altura_promedio, boolean activo) { // Declara un constructor con argumentos.
        this.nombre_comun = nombre_comun; // Asigna el valor del parámetro nombre_comun al atributo nombre_comun.
        this.tipo_flor = tipo_flor; // Asigna el valor del parámetro tipo_flor al atributo tipo_flor.
        this.dureza_madera = dureza_madera; // Asigna el valor del parámetro dureza_madera al atributo dureza_madera.
        this.altura_promedio = altura_promedio; // Asigna el valor del parámetro altura_promedio al atributo altura_promedio.
        this.activo = activo; // Asigna el valor del parámetro activo al atributo activo.
    }

    // Getters y Setters
    public Long getId_arbol() { // Declara el getter para el atributo id_arbol.
        return id_arbol; // Retorna el valor del atributo id_arbol.
    }

    public void setId_arbol(Long id_arbol) { // Declara el setter para el atributo id_arbol.
        this.id_arbol = id_arbol; // Asigna el valor del parámetro id_arbol al atributo id_arbol.
    }

    public String getNombre_comun() { // Declara el getter para el atributo nombre_comun.
        return nombre_comun; // Retorna el valor del atributo nombre_comun.
    }

    public void setNombre_comun(String nombre_comun) { // Declara el setter para el atributo nombre_comun.
        this.nombre_comun = nombre_comun; // Asigna el valor del parámetro nombre_comun al atributo nombre_comun.
    }

    public String getTipo_flor() { // Declara el getter para el atributo tipo_flor.
        return tipo_flor; // Retorna el valor del atributo tipo_flor.
    }

    public void setTipo_flor(String tipo_flor) { // Declara el setter para el atributo tipo_flor.
        this.tipo_flor = tipo_flor; // Asigna el valor del parámetro tipo_flor al atributo tipo_flor.
    }

    public String getDureza_madera() { // Declara el getter para el atributo dureza_madera.
        return dureza_madera; // Retorna el valor del atributo dureza_madera.
    }

    public void setDureza_madera(String dureza_madera) { // Declara el setter para el atributo dureza_madera.
        this.dureza_madera = dureza_madera; // Asigna el valor del parámetro dureza_madera al atributo dureza_madera.
    }

    public double getAltura_promedio() { // Declara el getter para el atributo altura_promedio.
        return altura_promedio; // Retorna el valor del atributo altura_promedio.
    }

    public void setAltura_promedio(double altura_promedio) { // Declara el setter para el atributo altura_promedio.
        this.altura_promedio = altura_promedio; // Asigna el valor del parámetro altura_promedio al atributo altura_promedio.
    }

    public String getRuta_imagen() { // Declara el getter para el atributo ruta_imagen.
        return ruta_imagen; // Retorna el valor del atributo ruta_imagen.
    }

    public void setRuta_imagen(String ruta_imagen) { // Declara el setter para el atributo ruta_imagen.
        this.ruta_imagen = ruta_imagen; // Asigna el valor del parámetro ruta_imagen al atributo ruta_imagen.
    }

    public boolean isActivo() { // Declara el getter para el atributo activo.
        return activo; // Retorna el valor del atributo activo.
    }

    public void setActivo(boolean activo) { // Declara el setter para el atributo activo.
        this.activo = activo; // Asigna el valor del parámetro activo al atributo activo.
    }
}
