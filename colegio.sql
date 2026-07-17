-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.3.39-MariaDB - MariaDB Server
-- SO del servidor:              Linux
-- HeidiSQL Versión:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para colegio
CREATE DATABASE IF NOT EXISTS `colegio` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `colegio`;

-- Volcando estructura para tabla colegio.Agenda
CREATE TABLE IF NOT EXISTS `Agenda` (
  `id_agenda` int(11) NOT NULL AUTO_INCREMENT,
  `id_profesor` int(11) DEFAULT NULL,
  `id_curso` int(11) DEFAULT NULL,
  `fecha_creacion` date NOT NULL DEFAULT curdate(),
  `descripcion` text NOT NULL CHECK (octet_length(`descripcion`) >= 10),
  `estado` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_agenda`),
  KEY `id_profesor` (`id_profesor`),
  KEY `id_curso` (`id_curso`),
  CONSTRAINT `Agenda_ibfk_1` FOREIGN KEY (`id_profesor`) REFERENCES `Profesor` (`id_profesor`),
  CONSTRAINT `Agenda_ibfk_2` FOREIGN KEY (`id_curso`) REFERENCES `Curso` (`id_curso`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla colegio.Agenda: ~2 rows (aproximadamente)
INSERT INTO `Agenda` (`id_agenda`, `id_profesor`, `id_curso`, `fecha_creacion`, `descripcion`, `estado`) VALUES
	(1, 1, 1, '2024-12-03', 'Agenda de clase para Matemáticas', 1),
	(2, 2, 2, '2024-12-03', 'Agenda de clase para Lengua', 1);

-- Volcando estructura para tabla colegio.Apoderado
CREATE TABLE IF NOT EXISTS `Apoderado` (
  `id_apoderado` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_apoderado`),
  UNIQUE KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `Apoderado_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `Usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla colegio.Apoderado: ~4 rows (aproximadamente)
INSERT INTO `Apoderado` (`id_apoderado`, `id_usuario`) VALUES
	(1, 18),
	(2, 19),
	(3, 20),
	(4, 21);

-- Volcando estructura para tabla colegio.Asistencia
CREATE TABLE IF NOT EXISTS `Asistencia` (
  `id_asistencia` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `id_estudiante` int(11) DEFAULT NULL,
  `id_curso` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_asistencia`),
  KEY `id_estudiante` (`id_estudiante`),
  KEY `id_curso` (`id_curso`),
  CONSTRAINT `Asistencia_ibfk_1` FOREIGN KEY (`id_estudiante`) REFERENCES `Estudiante` (`id_estudiante`),
  CONSTRAINT `Asistencia_ibfk_2` FOREIGN KEY (`id_curso`) REFERENCES `Curso` (`id_curso`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla colegio.Asistencia: ~8 rows (aproximadamente)
INSERT INTO `Asistencia` (`id_asistencia`, `fecha`, `estado`, `id_estudiante`, `id_curso`) VALUES
	(1, '2024-11-21', 1, 1, 1),
	(2, '2024-11-21', 1, 1, 2),
	(3, '2024-11-21', 1, 1, 3),
	(4, '2024-11-21', 1, 1, 4),
	(5, '2024-11-21', 1, 2, 5),
	(6, '2024-11-21', 1, 3, 6),
	(7, '2024-11-21', 0, 4, 1),
	(8, '2024-11-21', 1, 5, 2);

-- Volcando estructura para tabla colegio.Calificacion
CREATE TABLE IF NOT EXISTS `Calificacion` (
  `id_calificacion` int(11) NOT NULL AUTO_INCREMENT,
  `calificacion_1` decimal(5,2) NOT NULL CHECK (`calificacion_1` between 0 and 20),
  `calificacion_2` decimal(5,2) NOT NULL CHECK (`calificacion_2` between 0 and 20),
  `calificacion_3` decimal(5,2) NOT NULL CHECK (`calificacion_3` between 0 and 20),
  `calificacion_4` decimal(5,2) NOT NULL CHECK (`calificacion_4` between 0 and 20),
  `id_estudiante` int(11) DEFAULT NULL,
  `id_curso` int(11) DEFAULT NULL,
  `fecha` date NOT NULL DEFAULT curdate(),
  PRIMARY KEY (`id_calificacion`),
  KEY `id_estudiante` (`id_estudiante`),
  KEY `id_curso` (`id_curso`),
  CONSTRAINT `Calificacion_ibfk_1` FOREIGN KEY (`id_estudiante`) REFERENCES `Estudiante` (`id_estudiante`),
  CONSTRAINT `Calificacion_ibfk_2` FOREIGN KEY (`id_curso`) REFERENCES `Curso` (`id_curso`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla colegio.Calificacion: ~15 rows (aproximadamente)
INSERT INTO `Calificacion` (`id_calificacion`, `calificacion_1`, `calificacion_2`, `calificacion_3`, `calificacion_4`, `id_estudiante`, `id_curso`, `fecha`) VALUES
	(1, 18.50, 17.00, 16.00, 15.00, 1, 1, '2024-11-21'),
	(2, 18.50, 17.00, 16.00, 15.00, 1, 2, '2024-11-21'),
	(3, 18.50, 17.00, 16.00, 15.00, 1, 3, '2024-11-21'),
	(4, 18.50, 17.00, 16.00, 15.00, 2, 1, '2024-11-21'),
	(5, 18.50, 17.00, 16.00, 15.00, 2, 4, '2024-11-21'),
	(6, 18.50, 17.00, 16.00, 15.00, 3, 2, '2024-11-21'),
	(7, 18.50, 17.00, 16.00, 15.00, 4, 2, '2024-11-21'),
	(8, 18.50, 17.00, 16.00, 15.00, 5, 1, '2024-11-21'),
	(9, 14.50, 15.50, 16.50, 17.00, 6, 2, '2024-11-21'),
	(10, 14.50, 15.50, 16.50, 17.00, 7, 2, '2024-11-21'),
	(11, 14.50, 15.50, 16.50, 17.00, 8, 2, '2024-11-21'),
	(12, 14.50, 15.50, 16.50, 17.00, 9, 2, '2024-11-21'),
	(13, 14.50, 15.50, 16.50, 17.00, 10, 2, '2024-11-21'),
	(14, 14.50, 15.50, 16.50, 17.00, 11, 2, '2024-11-21'),
	(15, 14.50, 15.50, 16.50, 17.00, 12, 2, '2024-11-21');

-- Volcando estructura para tabla colegio.Comunicados
CREATE TABLE IF NOT EXISTS `Comunicados` (
  `id_comunicados` int(11) NOT NULL AUTO_INCREMENT,
  `contenido` text NOT NULL CHECK (octet_length(`contenido`) >= 10),
  `fecha` date NOT NULL DEFAULT curdate(),
  PRIMARY KEY (`id_comunicados`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla colegio.Comunicados: ~1 rows (aproximadamente)
INSERT INTO `Comunicados` (`id_comunicados`, `contenido`, `fecha`) VALUES
	(1, 'Vacaciones de medio año iniciarán el 15 de diciembre.', '2024-12-03');

-- Volcando estructura para tabla colegio.Curso
CREATE TABLE IF NOT EXISTS `Curso` (
  `id_curso` int(11) NOT NULL AUTO_INCREMENT,
  `id_profesor` int(11) DEFAULT NULL,
  `id_turno` int(11) DEFAULT NULL,
  `id_seccion` int(11) DEFAULT NULL,
  `silabo` varchar(255) DEFAULT NULL,
  `nombre_curso` varchar(100) NOT NULL CHECK (octet_length(`nombre_curso`) >= 3),
  `periodo_academico` varchar(10) NOT NULL CHECK (`periodo_academico` regexp '^[0-9]{4}-[0-9]{4}$'),
  PRIMARY KEY (`id_curso`),
  KEY `id_profesor` (`id_profesor`),
  KEY `id_turno` (`id_turno`),
  KEY `id_seccion` (`id_seccion`),
  CONSTRAINT `Curso_ibfk_1` FOREIGN KEY (`id_profesor`) REFERENCES `Profesor` (`id_profesor`),
  CONSTRAINT `Curso_ibfk_2` FOREIGN KEY (`id_turno`) REFERENCES `Turno` (`id_turno`),
  CONSTRAINT `Curso_ibfk_3` FOREIGN KEY (`id_seccion`) REFERENCES `Seccion` (`id_seccion`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla colegio.Curso: ~6 rows (aproximadamente)
INSERT INTO `Curso` (`id_curso`, `id_profesor`, `id_turno`, `id_seccion`, `silabo`, `nombre_curso`, `periodo_academico`) VALUES
	(1, 1, 1, 1, 'Silabo de Matemáticas', 'Matemáticas', '2024-2025'),
	(2, 2, 2, 1, 'Silabo de Lengua', 'Lengua', '2024-2025'),
	(3, 3, 1, 2, 'Silabo de Historia', 'Historia', '2024-2025'),
	(4, 4, 2, 2, 'Silabo de Ciencias', 'Ciencias', '2024-2025'),
	(5, 1, 1, 1, 'Silabo de Geografía', 'Geografía', '2024-2025'),
	(6, 2, 2, 2, 'Silabo de Física', 'Física', '2024-2025');

-- Volcando estructura para tabla colegio.Estudiante
CREATE TABLE IF NOT EXISTS `Estudiante` (
  `id_estudiante` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) DEFAULT NULL,
  `id_grado` int(11) DEFAULT NULL,
  `periodo_academico` varchar(10) NOT NULL CHECK (`periodo_academico` regexp '^[0-9]{4}-[0-9]{4}$'),
  PRIMARY KEY (`id_estudiante`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_grado` (`id_grado`),
  CONSTRAINT `Estudiante_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `Usuario` (`id_usuario`),
  CONSTRAINT `Estudiante_ibfk_2` FOREIGN KEY (`id_grado`) REFERENCES `Grado` (`id_grado`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla colegio.Estudiante: ~16 rows (aproximadamente)
INSERT INTO `Estudiante` (`id_estudiante`, `id_usuario`, `id_grado`, `periodo_academico`) VALUES
	(1, 6, 1, '2024-2025'),
	(2, 7, 1, '2024-2025'),
	(3, 8, 1, '2024-2025'),
	(4, 9, 1, '2024-2025'),
	(5, 10, 1, '2024-2025'),
	(6, 11, 1, '2024-2025'),
	(7, 12, 1, '2024-2025'),
	(8, 13, 1, '2024-2025'),
	(9, 14, 1, '2024-2025'),
	(10, 15, 1, '2024-2025'),
	(11, 16, 1, '2024-2025'),
	(12, 17, 1, '2024-2025'),
	(13, 18, 1, '2024-2025'),
	(14, 19, 1, '2024-2025'),
	(15, 20, 1, '2024-2025'),
	(16, 21, 1, '2024-2025');

-- Volcando estructura para tabla colegio.EstudianteCurso
CREATE TABLE IF NOT EXISTS `EstudianteCurso` (
  `id_estudiante` int(11) DEFAULT NULL,
  `id_curso` int(11) DEFAULT NULL,
  KEY `id_estudiante` (`id_estudiante`),
  KEY `id_curso` (`id_curso`),
  CONSTRAINT `EstudianteCurso_ibfk_1` FOREIGN KEY (`id_estudiante`) REFERENCES `Estudiante` (`id_estudiante`),
  CONSTRAINT `EstudianteCurso_ibfk_2` FOREIGN KEY (`id_curso`) REFERENCES `Curso` (`id_curso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla colegio.EstudianteCurso: ~15 rows (aproximadamente)
INSERT INTO `EstudianteCurso` (`id_estudiante`, `id_curso`) VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(2, 1),
	(2, 4),
	(3, 3),
	(4, 4),
	(5, 5),
	(6, 6),
	(7, 1),
	(8, 2),
	(9, 3),
	(10, 4),
	(11, 5),
	(12, 6);

-- Volcando estructura para tabla colegio.Grado
CREATE TABLE IF NOT EXISTS `Grado` (
  `id_grado` int(11) NOT NULL AUTO_INCREMENT,
  `grado` enum('1ero','2do','3ro','4to','5to') NOT NULL,
  PRIMARY KEY (`id_grado`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla colegio.Grado: ~5 rows (aproximadamente)
INSERT INTO `Grado` (`id_grado`, `grado`) VALUES
	(1, '1ero'),
	(2, '2do'),
	(3, '3ro'),
	(4, '4to'),
	(5, '5to');

-- Volcando estructura para tabla colegio.Hijos
CREATE TABLE IF NOT EXISTS `Hijos` (
  `id_hijos` int(11) NOT NULL AUTO_INCREMENT,
  `id_apoderado` int(11) DEFAULT NULL,
  `id_estudiante` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_hijos`),
  KEY `id_apoderado` (`id_apoderado`),
  KEY `id_estudiante` (`id_estudiante`),
  CONSTRAINT `Hijos_ibfk_1` FOREIGN KEY (`id_apoderado`) REFERENCES `Apoderado` (`id_apoderado`),
  CONSTRAINT `Hijos_ibfk_2` FOREIGN KEY (`id_estudiante`) REFERENCES `Estudiante` (`id_estudiante`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla colegio.Hijos: ~4 rows (aproximadamente)
INSERT INTO `Hijos` (`id_hijos`, `id_apoderado`, `id_estudiante`) VALUES
	(1, 3, 5),
	(2, 3, 2),
	(3, 4, 9),
	(4, 4, 10);

-- Volcando estructura para tabla colegio.Horario
CREATE TABLE IF NOT EXISTS `Horario` (
  `id_horario` int(11) NOT NULL AUTO_INCREMENT,
  `dia` int(11) NOT NULL CHECK (`dia` between 1 and 31),
  `mes` int(11) NOT NULL CHECK (`mes` between 1 and 12),
  `ano` int(11) NOT NULL CHECK (`ano` >= 2000),
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL CHECK (`hora_fin` > `hora_inicio`),
  `id_curso` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_horario`),
  KEY `id_curso` (`id_curso`),
  CONSTRAINT `Horario_ibfk_1` FOREIGN KEY (`id_curso`) REFERENCES `Curso` (`id_curso`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla colegio.Horario: ~5 rows (aproximadamente)
INSERT INTO `Horario` (`id_horario`, `dia`, `mes`, `ano`, `hora_inicio`, `hora_fin`, `id_curso`) VALUES
	(1, 1, 11, 2024, '08:00:00', '10:00:00', 1),
	(2, 2, 11, 2024, '10:00:00', '12:00:00', 2),
	(3, 3, 11, 2024, '12:00:00', '14:00:00', 3),
	(4, 4, 11, 2024, '14:00:00', '16:00:00', 4),
	(5, 4, 11, 2024, '14:00:00', '16:00:00', 5);

-- Volcando estructura para tabla colegio.Profesor
CREATE TABLE IF NOT EXISTS `Profesor` (
  `id_profesor` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_profesor`),
  UNIQUE KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `Profesor_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `Usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla colegio.Profesor: ~4 rows (aproximadamente)
INSERT INTO `Profesor` (`id_profesor`, `id_usuario`) VALUES
	(1, 2),
	(2, 3),
	(3, 4),
	(4, 5);

-- Volcando estructura para tabla colegio.Rol
CREATE TABLE IF NOT EXISTS `Rol` (
  `id_rol` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_rol` varchar(50) NOT NULL,
  PRIMARY KEY (`id_rol`),
  UNIQUE KEY `tipo_rol` (`tipo_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla colegio.Rol: ~4 rows (aproximadamente)
INSERT INTO `Rol` (`id_rol`, `tipo_rol`) VALUES
	(4, 'Apoderado'),
	(1, 'Director'),
	(3, 'Estudiante'),
	(2, 'Profesor');

-- Volcando estructura para tabla colegio.Seccion
CREATE TABLE IF NOT EXISTS `Seccion` (
  `id_seccion` int(11) NOT NULL AUTO_INCREMENT,
  `seccion` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id_seccion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla colegio.Seccion: ~2 rows (aproximadamente)
INSERT INTO `Seccion` (`id_seccion`, `seccion`) VALUES
	(1, 'A'),
	(2, 'B');

-- Volcando estructura para tabla colegio.Turno
CREATE TABLE IF NOT EXISTS `Turno` (
  `id_turno` int(11) NOT NULL AUTO_INCREMENT,
  `turno` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_turno`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla colegio.Turno: ~2 rows (aproximadamente)
INSERT INTO `Turno` (`id_turno`, `turno`) VALUES
	(1, 'Mañana'),
	(2, 'Tarde');

-- Volcando estructura para tabla colegio.Usuario
CREATE TABLE IF NOT EXISTS `Usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL CHECK (octet_length(`nombre`) >= 2),
  `apellido` varchar(50) NOT NULL CHECK (octet_length(`apellido`) >= 2),
  `correo` varchar(100) NOT NULL,
  `contrasena` varchar(100) NOT NULL CHECK (octet_length(`contrasena`) >= 8),
  `fecha_nacimiento` date NOT NULL,
  `dni` varchar(15) NOT NULL CHECK (`dni` regexp '^[0-9]{8}$'),
  `id_rol` int(10) unsigned DEFAULT NULL,
  `fecha_registro` date NOT NULL DEFAULT curdate(),
  `activo` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `correo` (`correo`),
  UNIQUE KEY `dni` (`dni`),
  KEY `id_rol` (`id_rol`),
  KEY `idx_usuario_dni` (`dni`),
  CONSTRAINT `Usuario_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `Rol` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla colegio.Usuario: ~21 rows (aproximadamente)
INSERT INTO `Usuario` (`id_usuario`, `nombre`, `apellido`, `correo`, `contrasena`, `fecha_nacimiento`, `dni`, `id_rol`, `fecha_registro`, `activo`) VALUES
	(1, 'Luis', 'Velez', 'director@abl.pe', '$2a$10$1M0QxVnYGVo6h0kOSyqMfui2lMd2s59oUxml2kMsWO2EzfMxeMnfK', '2000-05-12', '12345678', 1, '2024-12-02', 1),
	(2, 'Justin', 'Jimenez', 'profesor.justin@abl.pe', '$2a$10$YGcpTmJvn.cUHpoYeVXOYe1rZG94dC.PaAOvUdoBvDXzla7mbd9rm', '2000-05-12', '12245678', 2, '2024-12-03', 1),
	(3, 'Maria', 'Lopez', 'profesor.maria@abl.pe', '$2a$10$hXYIwN9dU9ZA9YQBzA.LFeY/84a6a/hVhI.ybJwV8X2hbjtMrySP2', '2001-04-10', '23456789', 2, '2024-12-03', 1),
	(4, 'Juan', 'Martinez', 'profesor.juan@abl.pe', '$2a$10$mzJYHz.Hk9mdwTUYEGo57us7xDt89lXzW46ZpBgP3g526RPuo6MJC', '1999-11-22', '34567890', 2, '2024-12-03', 1),
	(5, 'Junior', 'Baldera', 'profesor.junior@abl.pe', '$2a$10$ytIJaPZevVN5jMr72sE8JOu6wIgH4TKCHPD/3v3iyvCjzg51YLhB6', '1985-06-15', '45678901', 2, '2024-12-03', 1),
	(6, 'Richard', 'Chapoñan', 'estudiante.richard@abl.pe', '$2a$10$jVpnSUJcava.ZjUA9988rOeLF0IzftDdmR0hAIKx38BYin22mSBB.', '1975-03-30', '56789012', 3, '2024-12-03', 1),
	(7, 'Lucia', 'Fernandez', 'estudiante.lucia@abl.pe', '$2a$10$5.Oyv207kXNLUY7qvxIUEet0zBqPRKvLsjvQS/edyFGTP4kamFpuK', '1998-08-19', '67890123', 3, '2024-12-03', 1),
	(8, 'Jose', 'Ramirez', 'estudiante.jose@abl.pe', '$2a$10$6eYFvX0a61oCgYe0.j2Dz.4nqbb5E8./T4TJwdOIoDXi6JM/s.07.', '2003-01-09', '78901234', 3, '2024-12-03', 1),
	(9, 'Laura', 'Martinez', 'estudiante.laura@abl.pe', '$2a$10$K6GALjtm7JqON6ppYGzXDer.cu4lsKjwS6JtKo3rZy6g9kS2CoJUK', '1997-12-11', '89012345', 3, '2024-12-03', 1),
	(10, 'David', 'Reyes', 'estudiante.david@abl.pe', '$2a$10$8upAdzS8GE9WAaEl1XINwOIMbZeM93ssvu2/g.BmDyDHlnUVrM2SK', '2000-03-15', '90123456', 3, '2024-12-03', 1),
	(11, 'Carmen', 'Jimenez', 'estudiante.carmen@abl.pe', '$2a$10$.cZgMXq.2GJ8.4G7/R23pO6XFbmLOGQFxdYeo4aAizTQla83Txshi', '2002-06-23', '01234567', 3, '2024-12-03', 1),
	(12, 'Juan', 'Jimenez', 'estudiante.juan@abl.pe', '$2a$10$3sdUh1vTY5BuNxCxRETFNO3.2db5kLejLxd6QRq.vPdHqQs8xfzru', '2001-07-17', '12345012', 3, '2024-12-03', 1),
	(13, 'Sofia', 'Gonzalez', 'estudiante.sofia@abl.pe', '$2a$10$Oq7f.WoAfSwTVMmz.VkAXepvR.TQH9oyPTYjxhFmWfJ9W9mZX9yQO', '1999-09-21', '23456123', 3, '2024-12-03', 1),
	(14, 'Jorge', 'Ruiz', 'estudiante.jorge@abl.pe', '$2a$10$mUuDoc5aAT5HDIYhlC6RGu75UIqRG14hP4Iy6ycpaSnwyRd8iwcB6', '1995-04-10', '34567234', 3, '2024-12-03', 1),
	(15, 'Elena', 'Alvarez', 'estudiante.elena@abl.pe', '$2a$10$yTnqrvaO.aA8hJ1YhujbPuh3c0.G62fzRw22.txEHE08PLCJjgjRu', '1988-01-30', '45678345', 3, '2024-12-03', 1),
	(16, 'Diego', 'Alvarez', 'estudiante.diego@abl.pe', '$2a$10$HdfyoikNSGnzFBl5hlMHueIMxkQ.tBNbON4frxiYxAzBm/xEXTq1u', '2000-02-25', '56789456', 3, '2024-12-03', 1),
	(17, 'Julia', 'Alvarez', 'estudiante.julia@abl.pe', '$2a$10$PvvBoymjcC6.OvFAZglJSu0dnNvhX9hnsUxzJGHgTCk8.YicFgYBG', '2001-12-14', '67890567', 3, '2024-12-03', 1),
	(18, 'Oscar', 'Lopez', 'apoderado.oscar@abl.pe', '$2a$10$z2yA7KRLSjEuYt.R0aD7i.XxDXOEBGGXCRGxblNE/VqppapPQeZUK', '2002-09-13', '78901678', 4, '2024-12-03', 1),
	(19, 'Felipe', 'Alvarez', 'apoderado.felipe@abl.pe', '$2a$10$8ZltYICulNCzEpXRW1LUYuVInmImhCgac14w1ObT0WekQGorZCquS', '1996-05-18', '89012789', 4, '2024-12-03', 1),
	(20, 'Ricardo', 'Jimenez', 'apoderado.ricardo@abl.pe', '$2a$10$5Gv9n2hIm.qAYQxl/aHYOutfAC2pXxA.CsVK2wkVYMuqK6klGZwI.', '1994-10-02', '90123890', 4, '2024-12-03', 1),
	(21, 'Isabel', 'Rios', 'apoderado.isabel@abl.pe', '$2a$10$2Pz9W.Rx1nDFwft/NHRKe.6l8HiDo91vldM.YBLCgAGCxUWHbDYVa', '2003-11-27', '01234901', 4, '2024-12-03', 1);

