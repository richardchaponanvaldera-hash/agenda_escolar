package modelo.dao;

import configuracion.Conexion;
import modelo.comunicados;
import modelo.director;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class directorDAO {

    Conexion con = new Conexion();
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;

    public List<director> listaCursosDirector() {
        List<director> listaCursos = new ArrayList<>();
        String sql = "select * from Curso";
        try {
            cn = con.Conexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                director director = new director();
                director.setIdCurso(rs.getInt("id_curso"));
                director.setIdProfessor(rs.getInt("id_profesor")); // Asegúrate que sea el nombre correcto
                director.setIdTurno(rs.getInt("id_turno"));
                director.setIdSeccion(rs.getInt("id_seccion"));
                director.setSilabo(rs.getString("silabo"));
                director.setNombreCurso(rs.getString("nombre_curso"));
                director.setPeriodoAcademico(rs.getString("periodo_academico")); // Cambiado a String si el tipo de dato no es DATE
                listaCursos.add(director);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Muestra errores para depuración
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaCursos;
    }

    public List<director> listaEstudiantesDirector() {
        List<director> listaEstudiantes = new ArrayList<>();
        String sql = "SELECT \n" +
                "                  E.id_estudiante,\n" +
                "                  U.id_usuario,\n" +
                "                      U.dni, \n" +
                "                       U.correo, \n" +
                "                        U.contrasena, \n" +
                "    U.nombre AS estudiante_nombre, \n" +
                "    U.apellido AS estudiante_apellido,\n" +
                "    UA.nombre AS apoderado_nombre,\n" +
                "    UA.apellido AS apoderado_apellido\n" +
                "FROM Usuario U\n" +
                "JOIN Estudiante E ON U.id_usuario = E.id_usuario\n" +
                "LEFT JOIN Hijos H ON E.id_estudiante = H.id_estudiante\n" +
                "LEFT JOIN Apoderado A ON H.id_apoderado = A.id_apoderado\n" +
                "LEFT JOIN Usuario UA ON A.id_usuario = UA.id_usuario\n" +
                "where U.id_rol = 3";
        try {
            cn = con.Conexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                director director = new director();
                director.setIdEstudiante(rs.getInt("id_estudiante"));
                director.setIdUsuario(rs.getInt("id_usuario"));
                director.setDni(rs.getString("dni"));
                director.setCorreo(rs.getString("correo"));
                director.setContrasena(rs.getString("contrasena"));
                director.setEstudianteNombre(rs.getString("estudiante_nombre"));
                director.setEstudianteApellido(rs.getString("estudiante_apellido"));
                director.setApoderadoNombre(rs.getString("apoderado_nombre"));
                director.setApoderadoApellido(rs.getString("apoderado_apellido"));
                listaEstudiantes.add(director);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Muestra errores para depuración
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaEstudiantes;
    }


    public List<director> listaApoderadosDirector() {
        List<director> listaEstudiantes = new ArrayList<>();
        String sql = "SELECT \n" +
                "    A.id_apoderado,\n" +
                "    UA.id_usuario,\n" +
                "    UA.dni,\n" +
                "    UA.correo,\n" +
                "    UA.contrasena,\n" +
                "    UA.nombre AS apoderado_nombre,\n" +
                "    UA.apellido AS apoderado_apellido,\n" +
                "    E.id_estudiante,\n" +
                "    U.nombre AS estudiante_nombre,\n" +
                "    U.apellido AS estudiante_apellido\n" +
                "FROM Apoderado A\n" +
                "JOIN Usuario UA ON A.id_usuario = UA.id_usuario\n" +
                "LEFT JOIN Hijos H ON A.id_apoderado = H.id_apoderado\n" +
                "LEFT JOIN Estudiante E ON H.id_estudiante = E.id_estudiante\n" +
                "LEFT JOIN Usuario U ON E.id_usuario = U.id_usuario";
        try {
            cn = con.Conexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                director director = new director();
                director.setIdApoderado(rs.getInt("id_apoderado"));
                director.setIdUsuario(rs.getInt("id_usuario"));
                director.setDni(rs.getString("dni"));
                director.setCorreo(rs.getString("correo"));
                director.setContrasena(rs.getString("contrasena"));
                director.setApoderadoNombre(rs.getString("apoderado_nombre"));
                director.setApoderadoApellido(rs.getString("apoderado_apellido"));
                director.setIdEstudiante(rs.getInt("id_estudiante"));
                director.setEstudianteNombre(rs.getString("estudiante_nombre"));
                director.setEstudianteApellido(rs.getString("estudiante_apellido"));
                listaEstudiantes.add(director);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Muestra errores para depuración
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaEstudiantes;
    }


    public List<director> listaApodDirector() {
        List<director> listaEstudiantes = new ArrayList<>();
        String sql = "SELECT \n" +
                "    A.id_apoderado,\n" +
                "    UA.id_usuario,\n" +
                "    UA.dni,\n" +
                "    UA.correo,\n" +
                "    UA.contrasena,\n" +
                "    UA.nombre AS apoderado_nombre,\n" +
                "    UA.apellido AS apoderado_apellido,\n" +
                "    E.id_estudiante,\n" +
                "    U.nombre AS estudiante_nombre,\n" +
                "    U.apellido AS estudiante_apellido\n" +
                "FROM Apoderado A\n" +
                "JOIN Usuario UA ON A.id_usuario = UA.id_usuario\n" +
                "LEFT JOIN Hijos H ON A.id_apoderado = H.id_apoderado\n" +
                "LEFT JOIN Estudiante E ON H.id_estudiante = E.id_estudiante\n" +
                "LEFT JOIN Usuario U ON E.id_usuario = U.id_usuario group by(A.id_apoderado)";
        try {
            cn = con.Conexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                director director = new director();
                director.setIdApoderado(rs.getInt("id_apoderado"));
                director.setIdUsuario(rs.getInt("id_usuario"));
                director.setDni(rs.getString("dni"));
                director.setCorreo(rs.getString("correo"));
                director.setContrasena(rs.getString("contrasena"));
                director.setApoderadoNombre(rs.getString("apoderado_nombre"));
                director.setApoderadoApellido(rs.getString("apoderado_apellido"));
                director.setIdEstudiante(rs.getInt("id_estudiante"));
                director.setEstudianteNombre(rs.getString("estudiante_nombre"));
                director.setEstudianteApellido(rs.getString("estudiante_apellido"));
                listaEstudiantes.add(director);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Muestra errores para depuración
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaEstudiantes;
    }

    public List<director> listaProfesoresDirector() {
        List<director> listaEstudiantes = new ArrayList<>();
        String sql = "SELECT \n" +
                "    P.id_profesor,\n" +
                "    UP.id_usuario,\n" +
                "    UP.dni,\n" +
                "    UP.correo,\n" +
                "    UP.contrasena, \n" +
                "    UP.nombre AS profesor_nombre,\n" +
                "    UP.apellido AS profesor_apellido,\n" +
                "    C.id_curso,\n" +
                "    C.nombre_curso,\n" +
                "    T.turno,\n" +
                "    S.seccion\n" +
                "FROM Profesor P\n" +
                "JOIN Usuario UP ON P.id_usuario = UP.id_usuario\n" +
                "LEFT JOIN Curso C ON P.id_profesor = C.id_profesor\n" +
                "LEFT JOIN Turno T ON C.id_turno = T.id_turno\n" +
                "LEFT JOIN Seccion S ON C.id_seccion = S.id_seccion";
        try {
            cn = con.Conexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                director director = new director();
                director.setIdProfessor(rs.getInt("id_profesor"));
                director.setIdUsuario(rs.getInt("id_usuario"));
                director.setDni(rs.getString("dni"));
                director.setCorreo(rs.getString("correo"));
                director.setContrasena(rs.getString("contrasena"));
                director.setProfessorNombre(rs.getString("profesor_nombre"));
                director.setProfessorApellido(rs.getString("profesor_apellido"));
                director.setIdCurso(rs.getInt("id_curso"));
                director.setNombreCurso(rs.getString("nombre_curso"));
                director.setTurno(rs.getString("turno"));
                director.setSeccion(rs.getString("seccion"));
                listaEstudiantes.add(director);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Muestra errores para depuración
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaEstudiantes;
    }

    public List<director> listaAgendaDirector() {
        List<director> listaEstudiantes = new ArrayList<>();
        String sql = "SELECT\n" +
                "                    A.id_agenda,\n" +
                "                    A.fecha_creacion,\n" +
                "                    A.descripcion,\n" +
                "                    C.nombre_curso\n" +
                "                FROM \n" +
                "                    Agenda A\n" +
                "                JOIN \n" +
                "                    Curso C ON A.id_curso = C.id_curso";
        try {
            cn = con.Conexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                director director = new director();
                director.setIdAgenda(rs.getInt("id_agenda"));
                director.setFecha(rs.getString("fecha_creacion"));
                director.setDescripcion(rs.getString("descripcion"));
                director.setNombreCurso(rs.getString("nombre_curso"));
                listaEstudiantes.add(director);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Muestra errores para depuración
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaEstudiantes;
    }


    public List<director> listaGradoDirector() {
        List<director> listaEstudiantes = new ArrayList<>();
        String sql = "select * from Grado";
        try {
            cn = con.Conexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                director director = new director();
                director.setIdGrado(rs.getString("id_grado"));
                director.setGrado(rs.getString("grado"));
                listaEstudiantes.add(director);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Muestra errores para depuración
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaEstudiantes;
    }

    // Método para agregar un estudiante
    public boolean agregarEstudiante(director director) {
        String sqlUsuario = "INSERT INTO Usuario (nombre, apellido, correo, contrasena, dni, fecha_nacimiento, id_rol) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlEstudiante = "INSERT INTO Estudiante (id_usuario, id_grado, periodo_academico) VALUES (?, ?, ?)";
        boolean resultado = false;

        try {
            cn = con.Conexion();
            cn.setAutoCommit(false); // Iniciar transacción

            // Insertar en la tabla Usuario
            ps = cn.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, director.getEstudianteNombre());
            ps.setString(2, director.getEstudianteApellido());
            ps.setString(3, director.getCorreo());

            String hashedPassword = BCrypt.hashpw(director.getContrasena(), BCrypt.gensalt());
            ps.setString(4, hashedPassword);

            ps.setString(5, director.getDni());
            ps.setString(6, director.getFecha());
            ps.setInt(7, director.getIdRol());
            int filasUsuario = ps.executeUpdate();

            if (filasUsuario > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int idUsuario = rs.getInt(1);
                    System.out.println("ID Usuario generado: " + idUsuario);

                    // Insertar en la tabla Estudiante
                    ps = cn.prepareStatement(sqlEstudiante);
                    ps.setInt(1, idUsuario);
                    ps.setInt(2, Integer.parseInt(director.getGrado()));
                    ps.setString(3, director.getPeriodoAcademico());
                    int filasEstudiante = ps.executeUpdate();

                    if (filasEstudiante > 0) {
                        resultado = true;
                        System.out.println("Estudiante agregado correctamente.");
                    }
                }
            }
            cn.commit(); // Confirmar transacción

        } catch (Exception e) {
            try {
                cn.rollback(); // Revertir transacción
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            resultado = false;

        } finally {
            try {
                cn.setAutoCommit(true);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
                if (cn != null) cn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return resultado;
    }


    // Método para eliminar un estudiante
    public boolean eliminarEstudiante(int idEstudiante) {
        String sqlEstudiante = "DELETE FROM Estudiante WHERE id_estudiante = ?";
        String sqlUsuario = "DELETE FROM Usuario WHERE id_usuario = (SELECT id_usuario FROM Estudiante WHERE id_estudiante = ?)";
        boolean resultado = false;

        try {
            cn = con.Conexion();
            cn.setAutoCommit(false); // Iniciar transacción

            // Eliminar del registro en Estudiante
            ps = cn.prepareStatement(sqlEstudiante);
            ps.setInt(1, idEstudiante);
            int filasEstudiante = ps.executeUpdate();

            if (filasEstudiante > 0) {
                // Eliminar del registro en Usuario relacionado con el estudiante
                ps = cn.prepareStatement(sqlUsuario);
                ps.setInt(1, idEstudiante);
                int filasUsuario = ps.executeUpdate();

                if (filasUsuario > 0) {
                    resultado = true;
                    System.out.println("Estudiante eliminado correctamente.");
                }
            }
            cn.commit(); // Confirmar transacción

        } catch (Exception e) {
            try {
                cn.rollback(); // Revertir transacción en caso de error
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            resultado = false;

        } finally {
            try {
                cn.setAutoCommit(true);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
                if (cn != null) cn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return resultado;
    }


    // Método para agregar un profesor
    public boolean agregarProfesor(director director) {
        String sqlUsuario = "INSERT INTO Usuario (nombre, apellido, correo, contrasena, dni, fecha_nacimiento, id_rol) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlProfesor = "INSERT INTO Profesor (id_usuario) VALUES (?)";
        boolean resultado = false;

        try {
            cn = con.Conexion();
            cn.setAutoCommit(false); // Iniciar transacción

            // Insertar en la tabla Usuario
            ps = cn.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, director.getProfessorNombre());
            ps.setString(2, director.getProfessorApellido());
            ps.setString(3, director.getCorreo());

            String hashedPassword = BCrypt.hashpw(director.getContrasena(), BCrypt.gensalt());
            ps.setString(4, hashedPassword);

            ps.setString(5, director.getDni());
            ps.setString(6, director.getFecha());
            ps.setInt(7, director.getIdRol());
            int filasUsuario = ps.executeUpdate();

            if (filasUsuario > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int idUsuario = rs.getInt(1);
                    System.out.println("ID Usuario generado: " + idUsuario);

                    // Insertar en la tabla Estudiante
                    ps = cn.prepareStatement(sqlProfesor);
                    ps.setInt(1, idUsuario);
                    int filasEstudiante = ps.executeUpdate();

                    if (filasEstudiante > 0) {
                        resultado = true;
                        System.out.println("Profesor agregado correctamente.");
                    }
                }
            }
            cn.commit(); // Confirmar transacción

        } catch (Exception e) {
            try {
                cn.rollback(); // Revertir transacción
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            resultado = false;

        } finally {
            try {
                cn.setAutoCommit(true);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
                if (cn != null) cn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    // Método para eliminar un profesor
    public boolean eliminarProfesor(int idProfesor) {
        String sqlProfesor = "DELETE FROM Profesor WHERE id_profesor = ?";
        String sqlUsuario = "DELETE FROM Usuario WHERE id_usuario = (SELECT id_usuario FROM Profesor WHERE id_profesor = ?)";
        boolean resultado = false;

        try {
            cn = con.Conexion();
            cn.setAutoCommit(false); // Iniciar transacción

            // Eliminar de la tabla Profesor
            ps = cn.prepareStatement(sqlProfesor);
            ps.setInt(1, idProfesor);
            int filasProfesor = ps.executeUpdate();

            if (filasProfesor > 0) {
                // Eliminar de la tabla Usuario, asegurándose de que se borre solo si el profesor fue eliminado
                ps = cn.prepareStatement(sqlUsuario);
                ps.setInt(1, idProfesor);
                int filasUsuario = ps.executeUpdate();

                if (filasUsuario > 0) {
                    resultado = true;
                    System.out.println("Profesor y su usuario eliminados correctamente.");
                }
            }
            cn.commit(); // Confirmar transacción

        } catch (Exception e) {
            try {
                cn.rollback(); // Revertir transacción en caso de error
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            resultado = false;

        } finally {
            try {
                cn.setAutoCommit(true);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
                if (cn != null) cn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return resultado;
    }


    // Método para agregar un apoderado
    public boolean agregarApoderado(director director) {
        String sqlUsuario = "INSERT INTO Usuario (nombre, apellido, correo, contrasena, dni, fecha_nacimiento, id_rol) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlApoderado = "INSERT INTO Apoderado (id_usuario) VALUES (?)";
        boolean resultado = false;

        try {
            cn = con.Conexion();
            cn.setAutoCommit(false); // Iniciar transacción

            // Insertar en la tabla Usuario
            ps = cn.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, director.getApoderadoNombre());
            ps.setString(2, director.getApoderadoApellido());
            ps.setString(3, director.getCorreo());

            String hashedPassword = BCrypt.hashpw(director.getContrasena(), BCrypt.gensalt());
            ps.setString(4, hashedPassword);

            ps.setString(5, director.getDni());
            ps.setString(6, director.getFecha());
            ps.setInt(7, director.getIdRol());
            int filasUsuario = ps.executeUpdate();

            if (filasUsuario > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int idUsuario = rs.getInt(1);
                    System.out.println("ID Usuario generado: " + idUsuario);

                    // Insertar en la tabla Estudiante
                    ps = cn.prepareStatement(sqlApoderado);
                    ps.setInt(1, idUsuario);
                    int filasEstudiante = ps.executeUpdate();

                    if (filasEstudiante > 0) {
                        resultado = true;
                        System.out.println("Apoderado agregado correctamente.");
                    }
                }
            }
            cn.commit(); // Confirmar transacción

        } catch (Exception e) {
            try {
                cn.rollback(); // Revertir transacción
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            resultado = false;

        } finally {
            try {
                cn.setAutoCommit(true);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
                if (cn != null) cn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    // Método para agregar un apoderado - Hijo
    public boolean agregarApoderadoHijo(director director) {
        String sqlHijo = "INSERT INTO Hijos (id_apoderado, id_estudiante) VALUES (?, ?)";

        boolean resultado = false;

        try {
            cn = con.Conexion();
            cn.setAutoCommit(false); // Iniciar transacción

            // Insertar en la tabla Hijos
            ps = cn.prepareStatement(sqlHijo);
            ps.setInt(1, director.getIdApoderado());
            ps.setInt(2, director.getIdEstudiante());
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                resultado = true;  // Inserción exitosa
                System.out.println("Relación Apoderado-Hijo agregada correctamente.");
            }

            cn.commit(); // Confirmar transacción

        } catch (Exception e) {
            try {
                cn.rollback(); // Revertir transacción en caso de error
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            resultado = false;  // En caso de error

        } finally {
            try {
                cn.setAutoCommit(true);  // Restablecer el auto commit
                if (ps != null) ps.close();
                if (rs != null) rs.close();
                if (cn != null) cn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    // Método para eliminar un apoderado
    public boolean eliminarApoderado(int idApoderado) {
        String sqlEliminarHijos = "DELETE FROM Hijos WHERE id_apoderado = ?";
        String sqlEliminarApoderado = "DELETE FROM Apoderado WHERE id_apoderado = ?";
        boolean resultado = false;

        try {
            cn = con.Conexion();
            cn.setAutoCommit(false); // Iniciar transacción

            // Eliminar los registros en la tabla Hijos
            ps = cn.prepareStatement(sqlEliminarHijos);
            ps.setInt(1, idApoderado);
            int filasHijos = ps.executeUpdate();

            // Eliminar el apoderado de la tabla Apoderado
            ps = cn.prepareStatement(sqlEliminarApoderado);
            ps.setInt(1, idApoderado);
            int filasApoderado = ps.executeUpdate();

            // Si ambas eliminaciones fueron exitosas
            if (filasHijos > 0 && filasApoderado > 0) {
                resultado = true;
                System.out.println("Apoderado y sus hijos eliminados correctamente.");
            }

            cn.commit(); // Confirmar transacción

        } catch (Exception e) {
            try {
                cn.rollback(); // Revertir transacción en caso de error
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            resultado = false;  // En caso de error

        } finally {
            try {
                cn.setAutoCommit(true);  // Restaurar el auto commit
                if (ps != null) ps.close();
                if (rs != null) rs.close();
                if (cn != null) cn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return resultado;
    }


    // Listar turno
    public List<director> listaTurnoDirector() {
        List<director> listaTurno = new ArrayList<>();
        String sql = "select id_turno, turno from Turno ";

        try {
            cn = con.Conexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                director director = new director();
                director.setIdTurno(rs.getInt("id_turno"));
                director.setTurno(rs.getString("turno"));
                listaTurno.add(director);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Muestra errores para depuración
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaTurno;
    }

    public List<director> listaProfesorDirector() {
        List<director> listaProfesor = new ArrayList<>();
        String sql = "select P.id_profesor, U.id_usuario, U.nombre ,U.apellido from Profesor P \n" +
                "inner join Usuario U on P.id_usuario = U.id_usuario \n" +
                "where U.id_rol = 2";

        try {
            cn = con.Conexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                director director = new director();
                director.setIdProfessor(rs.getInt("id_profesor"));
                director.setIdUsuario(rs.getInt("id_usuario"));
                director.setProfessorNombre(rs.getString("nombre"));
                director.setProfessorApellido(rs.getString("apellido"));
                listaProfesor.add(director);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Muestra errores para depuración
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaProfesor;
    }

    public List<director> listaSeccionDirector() {
        List<director> listaSeccion = new ArrayList<>();
        String sql = "select id_seccion, seccion from Seccion";

        try {
            cn = con.Conexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                director director = new director();
                director.setIdSeccion(rs.getInt("id_seccion"));
                director.setSeccion(rs.getString("seccion"));
                listaSeccion.add(director);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Muestra errores para depuración
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaSeccion;
    }

    public int agregaCurso(director director) {
        String sql = "INSERT INTO Curso (id_profesor, id_turno, id_seccion, silabo, nombre_curso, periodo_academico) VALUES(?,?,?,?,?,?)";
        int filasInsertadas = 0;

        try (Connection cn = con.Conexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, director.getIdProfessor());
            ps.setInt(2, director.getIdTurno());
            ps.setInt(3, director.getIdSeccion());
            ps.setString(4, director.getSilabo());
            ps.setString(5, director.getNombreCurso());
            ps.setString(6, director.getPeriodoAcademico());

            filasInsertadas = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Muestra errores para depuración
        }

        return filasInsertadas;
    }

    public int agregaSeccion(director director) {
        String sql = "INSERT INTO Seccion (seccion) VALUES(?)";
        int filasInsertadas = 0;

        try (Connection cn = con.Conexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, director.getSeccion());

            filasInsertadas = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Muestra errores para depuración
        }

        return filasInsertadas;
    }

    public int agregaTurno(director director) {
        String sql = "INSERT INTO Turno (turno) VALUES(?)";
        int filasInsertadas = 0;

        try (Connection cn = con.Conexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, director.getTurno());

            filasInsertadas = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Muestra errores para depuración
        }

        return filasInsertadas;
    }

    public int agregaCursoEstudiante(director director) {
        String sql = "INSERT INTO EstudianteCurso (id_estudiante, id_curso) VALUES(?,?)";
        int filasInsertadas = 0;

        try (Connection cn = con.Conexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, director.getIdEstudiante());
            ps.setInt(2, director.getIdCurso());


            filasInsertadas = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Muestra errores para depuración
        }

        return filasInsertadas;
    }
    public int agregaHorario(director director) {
        String sql = "INSERT INTO Horario (dia, mes, ano, hora_inicio, hora_fin, id_curso) values (?,?, ?,?, ?,?)";
        int filasInsertadas = 0;

        try (Connection cn = con.Conexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, director.getDia());
            ps.setInt(2, director.getMes());
            ps.setInt(3, director.getYear());
            ps.setString(4, director.getHoraInicio());
            ps.setString(5, director.getHoraFin());
            ps.setInt(6, director.getIdCurso());


            filasInsertadas = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Muestra errores para depuración
        }

        return filasInsertadas;
    }
    public static void main(String[] args) {
        directorDAO dao = new directorDAO();
        List<director> lista = dao.listaProfesoresDirector();

        if(lista != null) {
            System.out.println("Profesor lista:");
            for (director d : lista) {
                System.out.println("ID PROFESOR: " + d.getIdProfessor());
                System.out.println("ID USUARIO: " + d.getIdUsuario());
                System.out.println("PROFESOR NOMBRE: " + d.getProfessorNombre());
                System.out.println("PROFESOR APELLIDO: " + d.getProfessorApellido());
                System.out.println("--------------------------");
 }
}
    }
}
