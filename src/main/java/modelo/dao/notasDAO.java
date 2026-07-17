package modelo.dao;

import configuracion.Conexion;
import modelo.director;
import modelo.estudiante;
import modelo.notas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class notasDAO {

    Conexion con = new Conexion();
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;

    public List<notas> listaNotas(int idEstudiante) {
        List<notas> listaNotas = new ArrayList<>();
        String sql = " SELECT \n" +
                "               CAL.id_calificacion, \n" +
                "    E.id_estudiante,\n" +
                "    C.id_curso, \n" +
                "    C.nombre_curso,\n" +
                "    CAL.calificacion_1,\n" +
                "    CAL.calificacion_2,\n" +
                "    CAL.calificacion_3,\n" +
                "    CAL.calificacion_4,\n" +
                "    ROUND((CAL.calificacion_1 + CAL.calificacion_2 + CAL.calificacion_3 + CAL.calificacion_4) / 4, 2) AS Promedio,\n" +
                "    CAL.fecha\n" +
                "FROM \n" +
                "    Calificacion CAL\n" +
                "JOIN \n" +
                "    Estudiante e ON CAL.id_estudiante = E.id_estudiante\n" +
                "JOIN \n" +
                "    Usuario U ON E.id_usuario = U.id_usuario\n" +
                "JOIN \n" +
                "    Curso C ON CAL.id_curso = C.id_curso\n" +
                "WHERE \n" +
                "    U.id_usuario = ?";
        try {
            cn = con.Conexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idEstudiante);
            rs = ps.executeQuery();

            while (rs.next()) {
                notas nota = new notas();
                nota.setIdCalificacion(rs.getInt("id_calificacion"));
                nota.setIdEstudiante(rs.getInt("id_estudiante"));
                nota.setIdCurso(rs.getInt("id_curso"));
                nota.setNombreCurso(rs.getString("nombre_curso"));
                nota.setCalificacion1(rs.getDouble("calificacion_1"));
                nota.setCalificacion2(rs.getDouble("calificacion_2"));
                nota.setCalificacion3(rs.getDouble("calificacion_3"));
                nota.setCalificacion4(rs.getDouble("calificacion_4"));
                nota.setPromedio(rs.getDouble("promedio"));
                nota.setFecha(rs.getString("fecha"));
                listaNotas.add(nota);
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
        return listaNotas;
    }

    public List<notas> listaEstudiantesConNotasPorCurso(int idCurso) {
        List<notas> listaEstudiantes = new ArrayList<>();
        String sql = "SELECT \n" +
                "                                E.id_estudiante,\n" +
                "                                CAL.id_calificacion, \n" +
                "                    U.nombre, \n" +
                "                    U.apellido,\n" +
                "                   CAL.calificacion_1,\n" +
                "                   CAL.calificacion_2,\n" +
                "                   CAL.calificacion_3,\n" +
                "                  CAL.calificacion_4,\n" +
                "                   ROUND((CAL.calificacion_1 + CAL.calificacion_2 + CAL.calificacion_3 + CAL.calificacion_4) / 4, 2) AS Promedio\n" +
                "                FROM \n" +
                "                    Calificacion CAL\n" +
                "                JOIN \n" +
                "                    Estudiante E ON CAL.id_estudiante = E.id_estudiante\n" +
                "                JOIN \n" +
                "                    Usuario U ON E.id_usuario = U.id_usuario\n" +
                "                JOIN \n" +
                "                    Curso C ON CAL.id_curso = C.id_curso\n" +
                "                WHERE \n" +
                "                    CAL.id_curso  = ?";

        try (Connection cn = con.Conexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idCurso);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    notas notas = new notas();
                    notas.setIdEstudiante(rs.getInt("id_estudiante"));
                    notas.setIdCalificacion(rs.getInt("id_calificacion"));
                    notas.setNombre(rs.getString("nombre"));
                    notas.setApellido(rs.getString("apellido"));
                    notas.setCalificacion1(rs.getDouble("calificacion_1"));
                    notas.setCalificacion2(rs.getDouble("calificacion_2"));
                    notas.setCalificacion3(rs.getDouble("calificacion_3"));
                    notas.setCalificacion4(rs.getDouble("calificacion_4"));
                    notas.setPromedio(rs.getDouble("promedio"));
                    listaEstudiantes.add(notas);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al listar estudiantes con notas: " + e.getMessage());
        }
        return listaEstudiantes;
    }



    public int eliminarNota(int idCalificacion) {
        String sql = "DELETE FROM Calificacion WHERE id_calificacion = ?";
        int filasInsertadas = 0;

        try (Connection cn = con.Conexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idCalificacion);

            filasInsertadas = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Muestra errores para depuración
        }

        return filasInsertadas;
    }

    public List<notas> listaEstudiantesPorCurso(int idCurso) {
        List<notas> listaEstudiantes = new ArrayList<>();
        String sql = "SELECT " +
                "   E.id_estudiante, " +
                "   U.nombre, " +
                "   U.apellido, " +
                "   C.calificacion_1, " +
                "   C.calificacion_2, " +
                "   C.calificacion_3, " +
                "   C.calificacion_4, " +
                "    ROUND((C.calificacion_1 + C.calificacion_2 + C.calificacion_3 + C.calificacion_4) / 4, 2) AS Promedio\n" +
                "FROM " +
                "   Estudiante E " +
                "JOIN " +
                "   Usuario U ON E.id_usuario = U.id_usuario " +
                "JOIN " +
                "   EstudianteCurso EC ON E.id_estudiante = EC.id_estudiante " +
                "LEFT JOIN " +
                "   Calificacion C ON C.id_estudiante = E.id_estudiante AND C.id_curso = EC.id_curso " +
                "WHERE " +
                "   EC.id_curso = ?";

        try (Connection cn = con.Conexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idCurso);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    notas notas = new notas();
                    notas.setIdEstudiante(rs.getInt("id_estudiante"));
                    notas.setNombre(rs.getString("nombre"));
                    notas.setApellido(rs.getString("apellido"));
                    notas.setCalificacion1(rs.getDouble("calificacion_1"));
                    notas.setCalificacion2(rs.getDouble("calificacion_2"));
                    notas.setCalificacion3(rs.getDouble("calificacion_3"));
                    notas.setCalificacion4(rs.getDouble("calificacion_4"));
                    notas.setPromedio(rs.getDouble("promedio"));
                    listaEstudiantes.add(notas);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al listar estudiantes con notas: " + e.getMessage());
        }
        return listaEstudiantes;
    }

    public boolean agregarNota(notas notas) {
        String sqlnota = "INSERT INTO Calificacion (calificacion_1, calificacion_2, calificacion_3, calificacion_4, id_estudiante, id_curso) VALUES (?, ?,? ,?, ?,?)";

        boolean resultado = false;

        try {
            cn = con.Conexion();
            cn.setAutoCommit(false); // Iniciar transacción

            // Insertar en la tabla Hijos
            ps = cn.prepareStatement(sqlnota);
            ps.setDouble(1, notas.getCalificacion1());
            ps.setDouble(2, notas.getCalificacion2());
            ps.setDouble(3, notas.getCalificacion3());
            ps.setDouble(4, notas.getCalificacion4());
            ps.setInt(5, notas.getIdEstudiante());
            ps.setInt(6,notas.getIdCurso());
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                resultado = true;  // Inserción exitosa
                System.out.println("Nota agregada correctamente.");
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






    public static void main(String[] args) {


        notasDAO miClaseDAO = new notasDAO();

        // Asignar el id del curso a consultar
        int idCurso = 1; // Cambia este valor según tu base de datos

        // Invocar el método
        List<notas> estudiantesConNotas = miClaseDAO.listaEstudiantesConNotasPorCurso(idCurso);

        // Recorrer e imprimir los resultados
        if (estudiantesConNotas.isEmpty()) {
            System.out.println("No se encontraron estudiantes para el curso con id: " + idCurso);
        } else {
            System.out.println("Lista de estudiantes y sus calificaciones:");
            for (notas estudiante : estudiantesConNotas) {
                System.out.println("ID: " + estudiante.getIdEstudiante() +
                        ", Nombre: " + estudiante.getNombre() +
                        ", Apellido: " + estudiante.getApellido() +
                        ", Nota 1: " + estudiante.getCalificacion1() +
                        ", Nota 2: " + estudiante.getCalificacion2() +
                        ", Nota 3: " + estudiante.getCalificacion3() +
                        ", Nota 4: " + estudiante.getCalificacion4());
            }
        }
    }
}
