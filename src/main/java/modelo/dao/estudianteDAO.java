package modelo.dao;

import configuracion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import modelo.estudiante;

public class estudianteDAO {
    Conexion con = new Conexion();
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;

    public List<estudiante> listaEstudiantesPorCurso(int idCurso) {
        List<estudiante> listaEstudiantes = new ArrayList<>();
        String sql = "SELECT \n" +
                "    E.id_estudiante, \n" +
                "    U.nombre, \n" +
                "    U.apellido, \n" +
                "    U.correo, \n" +
                "    U.dni \n" +
                "FROM \n" +
                "    Estudiante E \n" +
                "JOIN \n" +
                "    Usuario U ON E.id_usuario = U.id_usuario \n" +
                "JOIN \n" +
                "    EstudianteCurso EC ON E.id_estudiante = EC.id_estudiante \n" +
                "WHERE \n" +
                "    EC.id_curso = ?";

        try {
            cn = con.Conexion(); // Conexión a la base de datos
            if (cn != null) {
                ps = cn.prepareStatement(sql);
                ps.setInt(1, idCurso); // Establece el parámetro
                rs = ps.executeQuery();

                while (rs.next()) {
                    estudiante estudiante = new estudiante();
                    estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                    estudiante.setNombre(rs.getString("nombre"));
                    estudiante.setApellido(rs.getString("apellido"));
                    estudiante.setCorreo(rs.getString("correo"));
                    estudiante.setDni(rs.getString("dni"));
                    listaEstudiantes.add(estudiante);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al listar estudiantes: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return listaEstudiantes;
    }


    public static void main(String[] args) {

    }

}
