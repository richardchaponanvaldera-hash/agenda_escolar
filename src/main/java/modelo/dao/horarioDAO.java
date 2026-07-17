package modelo.dao;

import configuracion.Conexion;
import modelo.horario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class horarioDAO {
    Conexion con = new Conexion();
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;

    public List<horario> listaHorario(int idEstudiante) {
        List<horario> listaHorario = new ArrayList<>();
        String sql = "SELECT \n" +
                "    H.id_horario, \n" +
                "    H.dia,\n" +
                "    C.nombre_curso AS Curso,\n" +
                "    H.hora_inicio,\n" +
                "    H.hora_fin\n" +
                "FROM \n" +
                "    Horario H\n" +
                "JOIN \n" +
                "    Curso C ON H.id_curso = C.id_curso\n" +
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
                    horario horario = new horario();
                    horario.setIdhorario(rs.getInt("id_horario"));
                    horario.setDia(rs.getInt("dia"));
                    horario.setCurso(rs.getString("curso"));
                    horario.setHoraInicio(rs.getString("hora_inicio"));
                    horario.setHoraFin(rs.getString("hora_fin"));
                    listaHorario.add(horario);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al listar horario: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return listaHorario;
    }
    public List<horario> listaHorarioProfesor(int idUsuario) {
        List<horario> listaHorario = new ArrayList<>();
        String sql = "SELECT \n" +
                "    H.id_horario, \n" +
                "    H.dia,\n" +
                "    C.nombre_curso AS Curso,\n" +
                "    H.hora_inicio,\n" +
                "    H.hora_fin\n" +
                "FROM \n" +
                "    Horario H\n" +
                "JOIN \n" +
                "    Curso C ON H.id_curso = C.id_curso\n" +
                "JOIN \n" +
                "    Profesor P ON C.id_profesor = P.id_profesor\n" +
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
                    horario horario = new horario();
                    horario.setIdhorario(rs.getInt("id_horario"));
                    horario.setDia(rs.getInt("dia"));
                    horario.setCurso(rs.getString("curso"));
                    horario.setHoraInicio(rs.getString("hora_inicio"));
                    horario.setHoraFin(rs.getString("hora_fin"));
                    listaHorario.add(horario);
                }
            }
        }catch (Exception e) {
            System.err.println("Error al listar horario: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return listaHorario;
    }

        public static void main(String[] args) {
        horarioDAO dao = new horarioDAO();
        List<horario> listaHorario = dao.listaHorario(1);

        if (listaHorario != null && !listaHorario.isEmpty()) {
            System.out.println("horariolista:");
            for (horario horario : listaHorario) {
                System.out.println("ID: " + horario.getIdhorario());
                System.out.println("dia: " + horario.getDia());
                System.out.println("Curso: " + horario.getCurso());
                System.out.println("hora inicio: " + horario.getHoraInicio());
                System.out.println("horar fin: " + horario.getHoraFin());
                System.out.println("--------------------------");
            }
        } else {
            System.out.println("No se encontraron registros de la agenda para el estudiante.");
        }
    }
}
