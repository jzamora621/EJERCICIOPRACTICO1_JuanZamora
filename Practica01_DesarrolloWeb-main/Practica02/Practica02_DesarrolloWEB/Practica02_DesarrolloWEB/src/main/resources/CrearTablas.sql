/* Eliminar la base de datos y el usuario si ya existen */
DROP SCHEMA IF EXISTS practica;
DROP USER IF EXISTS 'usuario_practica'@'%';

/* Crear la base de datos */
CREATE SCHEMA practica;

/* Crear el usuario con la contraseña especificada */
CREATE USER 'usuario_practica'@'%' IDENTIFIED BY 'la_Clave';

/* Otorgar todos los privilegios sobre la base de datos al nuevo usuario */
GRANT ALL PRIVILEGES ON practica.* TO 'usuario_practica'@'%';
FLUSH PRIVILEGES;

/* Usar la base de datos recién creada */
USE practica;

/* Crear la tabla arbol */
CREATE TABLE arbol (
  id_arbol INT NOT NULL AUTO_INCREMENT,
  nombre_comun VARCHAR(100) NOT NULL,
  tipo_flor VARCHAR(100),
  dureza_madera VARCHAR(50),
  altura_promedio DECIMAL(5,2),
  edad_promedio INT,
  ruta_imagen VARCHAR(1024),
  activo BOOLEAN NOT NULL DEFAULT TRUE,  -- Agregada la columna 'activo'
  PRIMARY KEY (id_arbol)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/* Insertar 5 registros en la tabla arbol */
INSERT INTO arbol (nombre_comun, tipo_flor, dureza_madera, altura_promedio, edad_promedio, ruta_imagen, activo) VALUES
('Roble', 'Sin flor visible', 'Dura', 30.50, 200, '/imagenes/roble.jpg', TRUE),
('Cerezo', 'Flores rosadas', 'Media', 7.00, 40, '/imagenes/cerezo.jpg', TRUE),
('Pino', 'Coníferas', 'Blanda', 25.00, 150, '/imagenes/pino.jpg', TRUE),
('Álamo', 'Flores pequeñas', 'Blanda', 20.00, 70, '/imagenes/alamo.jpg', TRUE),
('Caoba', 'Sin flor visible', 'Muy dura', 18.00, 350, '/imagenes/caoba.jpg', TRUE);

select * from arbol;
