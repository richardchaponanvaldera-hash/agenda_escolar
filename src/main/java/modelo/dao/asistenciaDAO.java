package modelo.dao;

import configuracion.Conexion;
import modelo.asistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class asistenciaDAO {
    Conexion con = new Conexion();
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;

    public List<asistencia> listaAsistencia(int idEstudiante) {
        List<asistencia> listaAsistencia = new ArrayList<>();
        String sql = "SELECT \n" +
                "    A.id_asistencia, \n" +
                "    E.id_estudiante,\n" +
                "    C.nombre_curso,\n" +
                "    H.dia, \n" +
                "    A.fecha,\n" +
                "    A.estado\n" +
                "FROM \n" +
                "    Asistencia A\n" +
                "JOIN \n" +
                "    Estudiante E \n" +
                "    ON A.id_estudiante = E.id_estudiante\n" +
                "JOIN \n" +
                "    Usuario U \n" +
                "    ON E.id_usuario = U.id_usuario\n" +
                "JOIN \n" +
                "    Curso C \n" +
                "    ON A.id_curso = C.id_curso\n" +
                "JOIN \n" +
                "    Horario H \n" +
                "    ON C.id_curso = H.id_curso \n" +
                "WHERE \n" +
                "    U.id_usuario = ? \n" +
                "ORDER BY \n" +
                "    C.nombre_curso, \n" +
                "    H.dia, \n" +
                "    A.fecha;";
        try {
            cn = con.Conexion();
            if (cn != null) {
                ps = cn.prepareStatement(sql);
                ps.setInt(1, idEstudiante);
                rs = ps.executeQuery();

                while (rs.next()) {
                    asistencia asistencia = new asistencia();
                    asistencia.setIdAsistencia(rs.getInt("id_asistencia"));
                    asistencia.setIdEstudiante(rs.getInt("id_estudiante"));
                    asistencia.setDia(rs.getInt("dia"));
                    asistencia.setNombreCurso(rs.getString("nombre_curso"));
                    asistencia.setFecha(rs.getString("fecha"));
                    asistencia.setEstado(rs.getInt("estado"));
                    listaAsistencia.add(asistencia);
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
        return listaAsistencia;
    }

    public boolean guardarAsistencias(List<asistencia> asistencias) {
        String sql = "INSERT INTO Asistencia (fecha, estado, id_estudiante, id_curso) VALUES (?, ?, ?, ?)";
        try {
            cn = con.Conexion();
            if (cn != null) {
                ps = cn.prepareStatement(sql);

                for (asistencia a : asistencias) {
                    ps.setString(1, a.getFecha());
                    ps.setInt(2, a.getEstado());
                    ps.setInt(3, a.getIdEstudiante());
                    ps.setInt(4, a.getIdCurso());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            return true;
        } catch (Exception e) {
            System.err.println("Error al guardar asistencias: " + e.getMessage());
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        asistenciaDAO dao = new asistenciaDAO();
        List<asistencia> listaAsistencia = dao.listaAsistencia(6);

        if (listaAsistencia != null && !listaAsistencia.isEmpty()) {
            System.out.println("horariolista:");
            for (asistencia asistencia : listaAsistencia) {
                System.out.println("ID: " + asistencia.getIdAsistencia());
                System.out.println("ID Estudiante: " + asistencia.getIdEstudiante());
                System.out.println("Curso: " + asistencia.getNombreCurso());
                System.out.println("Fecha: " + asistencia.getFecha());
                System.out.println("Estado: " + asistencia.getEstado());
                System.out.println("--------------------------");
            }
        } else {
            System.out.println("No se encontraron registros en horario para el estudiante.");
        }
    }
}
