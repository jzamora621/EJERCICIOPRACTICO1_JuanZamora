package domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
}

