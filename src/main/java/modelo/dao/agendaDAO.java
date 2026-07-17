package modelo.dao;

import configuracion.Conexion;
import modelo.agenda;
import modelo.comunicados;
import modelo.notas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class agendaDAO {
    Conexion con = new Conexion();
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;

    public List<agenda> listaAgenda(int idEstudiante) {
        List<agenda> listaAgenda = new ArrayList<>();
        String sql = "SELECT \n" +
                "    A.id_agenda,\n" +
                "    A.fecha_creacion,\n" +
                "    A.descripcion,\n" +
                "    C.nombre_curso\n" +
                "FROM \n" +
                "    Agenda A\n" +
                "JOIN \n" +
                "    Curso C ON A.id_curso = C.id_curso\n" +
                "JOIN \n" +
                "    Profesor P ON A.id_profesor = P.id_profesor\n" +
                "JOIN \n" +
                "    Usuario U ON P.id_usuario = U.id_usuario\n" +
                "JOIN \n" +
                "    EstudianteCurso EC ON C.id_curso = EC.id_curso\n" +
                "JOIN \n" +
                "    Estudiante E ON EC.id_estudiante = E.id_estudiante\n" +
                "WHERE \n" +
                "    E.id_usuario = ?";
        try {
            cn = con.Conexion();
            if (cn != null) {
                ps = cn.prepareStatement(sql);
                ps.setInt(1, idEstudiante); // Establece el parámetro
                rs = ps.executeQuery();

                while (rs.next()) {
                    agenda agenda = new agenda();
                    agenda.setIdAgenda(rs.getInt("id_agenda"));
                    agenda.setFechaCreacion(rs.getString("fecha_creacion"));
                    agenda.setDescripcion(rs.getString("descripcion"));
                    agenda.setNombreCurso(rs.getString("nombre_curso"));
                    listaAgenda.add(agenda);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al listar la agenda: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return listaAgenda;
    }
    public List<agenda> listaAgendaProfesor(int idUsuario) {
        List<agenda> listaAgenda = new ArrayList<>();
        String sql = "SELECT \n" +
                "    A.id_agenda,\n" +
                "    A.fecha_creacion,\n" +
                "    A.descripcion,\n" +
                "    C.nombre_curso\n" +
                "FROM \n" +
                "    Agenda A\n" +
                "JOIN \n" +
                "    Curso C ON A.id_curso = C.id_curso\n" +
                "JOIN \n" +
                "    Profesor P ON A.id_profesor = P.id_profesor\n" +
                "JOIN \n" +
                "    Usuario U ON P.id_usuario = U.id_usuario\n" +
                "WHERE \n" +
                "    U.id_usuario = ?";

        try {
            cn = con.Conexion(); // Conexión a la base de datos
            if (cn != null) {
                ps = cn.prepareStatement(sql);
                ps.setInt(1, idUsuario); // Establece el parámetro
                rs = ps.executeQuery();

                while (rs.next()) {
                    agenda agenda = new agenda();
                    agenda.setIdAgenda(rs.getInt("id_agenda"));
                    agenda.setFechaCreacion(rs.getString("fecha_creacion"));
                    agenda.setDescripcion(rs.getString("descripcion"));
                    agenda.setNombreCurso(rs.getString("nombre_curso"));
                    listaAgenda.add(agenda);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al listar la agenda: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return listaAgenda;
    }

    public List<agenda> listarProfesorPorUsuario(int idusuario) {
        List<agenda> listaProfesores = new ArrayList<>();
        String sql = "SELECT \n" +
                "    Profesor.id_profesor\n" +
                "FROM \n" +
                "    Profesor\n" +
                "INNER JOIN \n" +
                "    Usuario ON Profesor.id_usuario = Usuario.id_usuario\n" +
                "WHERE \n" +
                "    Profesor.id_usuario = ?";

        try (Connection cn = con.Conexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idusuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    agenda agenda = new agenda();
                    agenda.setIdProfesor(rs.getInt("id_profesor"));

                    listaProfesores.add(agenda);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al listar profesores por curso: " + e.getMessage());
        }
        return listaProfesores;
    }

    public int agregaAgenda(agenda agenda) {
        String sql = "INSERT INTO Agenda (id_profesor, id_curso, descripcion, estado) VALUES (?, ?, ?, ?)";
        int filasInsertadas = 0;

        try (Connection cn = con.Conexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            // Establecer los parámetros de la consulta
            ps.setInt(1, agenda.getIdProfesor());        // id_profesor
            ps.setInt(2, agenda.getIdCurso());           // id_curso
            ps.setString(3, agenda.getDescripcion());    // descripcion
            ps.setInt(4, agenda.getEstado());        // estado

            // Ejecutar la inserción
            filasInsertadas = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Muestra errores para depuración
        }

        return filasInsertadas;
    }

    public boolean eliminarAgenda(int idAgenda) {
        String sql = "DELETE FROM Agenda\n" +
                "WHERE id_agenda = ?";
        boolean eliminado = false;

        try (Connection cn = con.Conexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idAgenda);
            int filasAfectadas = ps.executeUpdate();
            eliminado = (filasAfectadas > 0); // Si al menos una fila fue afectada, la eliminación fue exitosa.

        } catch (SQLException e) {
            e.printStackTrace(); // Muestra errores para depuración
        }

        return eliminado;
    }



    public static void main(String[] args) {
        agendaDAO dao = new agendaDAO();
        List<agenda> listaAgenda = dao.listaAgenda(6);

        if (listaAgenda != null && !listaAgenda.isEmpty()) {
            System.out.println("Agenda lista:");
            for (agenda agenda : listaAgenda) {
                System.out.println("ID: " + agenda.getIdAgenda());
                System.out.println("Descripción: " + agenda.getDescripcion());
                System.out.println("Curso: " + agenda.getNombreCurso());
                System.out.println("Fecha: " + agenda.getFechaCreacion());
                System.out.println("--------------------------");
            }
        } else {
            System.out.println("No se encontraron registros de la agenda para el estudiante.");
        }
    }

}
