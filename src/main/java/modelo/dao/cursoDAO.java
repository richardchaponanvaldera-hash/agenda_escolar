package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import configuracion.Conexion;
import modelo.curso;
import modelo.informacionCurso;

public class cursoDAO {

    Conexion con = new Conexion();
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;


    public List<curso> listaCursos(int idEstudiante) {
        List<curso> listaCursos = new ArrayList<>();
        String sql = "SELECT \n" +
                "    C.id_curso, \n" +
                "    C.id_profesor, \n" +
                "    C.id_turno, \n" +
                "    C.id_seccion, \n" +
                "    C.silabo, \n" +
                "    C.nombre_curso, \n" +
                "   C.periodo_academico \n" +
                "FROM \n" +
                "    EstudianteCurso E\n" +
                "INNER JOIN \n" +
                "    Curso C ON E.id_curso = C.id_curso\n" +
                "INNER JOIN \n" +
                "    Estudiante EST ON E.id_estudiante = EST.id_estudiante\n" +
                "WHERE \n" +
                "    EST.id_usuario = ?";
        try {
            cn = con.Conexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idEstudiante);
            rs = ps.executeQuery();

            while (rs.next()) {
                curso curso = new curso();
                curso.setIdCurso(rs.getInt("id_curso"));
                curso.setIdProfessor(rs.getInt("id_profesor")); // Asegúrate que sea el nombre correcto
                curso.setIdTurno(rs.getInt("id_turno"));
                curso.setIdSeccion(rs.getInt("id_seccion"));
                curso.setSilabo(rs.getString("silabo"));
                curso.setNombreCurso(rs.getString("nombre_curso"));
                curso.setPreriodoAcademico(rs.getString("periodo_academico")); // Cambiado a String si el tipo de dato no es DATE
                listaCursos.add(curso);
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

    public List<curso> listaCursosPorProfesor(int idProfesor) {
        List<curso> listaCursos = new ArrayList<>();
        String sql = "SELECT \n" +
                "C.id_curso, \n" +
                "C.nombre_curso\n" +
                " FROM Curso C \n" +
                " INNER JOIN Profesor P ON C.id_profesor = P.id_profesor \n" +
                " INNER JOIN Seccion S ON C.id_seccion = S.id_seccion \n" +
                "   INNER JOIN Turno T ON C.id_turno = T.id_turno \n" +
                "   WHERE P.id_usuario = ?";

        try {
            cn = con.Conexion(); // Conexión a la base de datos
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idProfesor);
            rs = ps.executeQuery();

            while (rs.next()) {
                curso curso = new curso();
                curso.setIdCurso(rs.getInt("id_curso"));
                curso.setNombreCurso(rs.getString("nombre_curso"));
                listaCursos.add(curso);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprime errores para depuración
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



    public informacionCurso obtenerCursoPorId(int idCurso) {
        informacionCurso inCurso = null;
        String sql = "SELECT \n" +
                "    C.id_curso,\n" +
                "    C.nombre_curso,\n" +
                "    C.silabo,\n" +
                "    C.periodo_academico,\n" +
                "    T.turno AS Turno,\n" +
                "    S.seccion AS Seccion,\n" +
                "    CONCAT(U.nombre, ' ', U.apellido) AS profesor_nombre\n" +
                "FROM \n" +
                "    Curso C\n" +
                "INNER JOIN \n" +
                "    Profesor P ON C.id_profesor = P.id_profesor\n" +
                "INNER JOIN \n" +
                "    Usuario U ON P.id_usuario = U.id_usuario\n" +
                "INNER JOIN \n" +
                "    Turno T ON C.id_turno = T.id_turno\n" +
                "INNER JOIN \n" +
                "    Seccion S ON C.id_seccion = S.id_seccion\n" +
                "WHERE \n" +
                "    C.id_curso = ?"; // Usamos ? como marcador de posición para el idCurso

        try {
            cn = con.Conexion(); // Asumo que 'con' es una instancia de tu clase de conexión
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idCurso); // Establecemos el valor de idCurso
            rs = ps.executeQuery();

            if (rs.next()) {
                inCurso = new informacionCurso();

                inCurso.setIdCurso(rs.getInt("id_curso"));
                inCurso.setNombreCurso(rs.getString("nombre_curso"));
                inCurso.setSilabo(rs.getString("silabo"));
                inCurso.setSeccion(rs.getString("seccion"));
                inCurso.setTurno(rs.getString("turno"));
                inCurso.setPeriodoAcademico(rs.getString("periodo_academico"));
                inCurso.setProfesorNombre(rs.getString("profesor_nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return inCurso;
    }

    public static void main(String[] args) {
        cursoDAO dao = new cursoDAO();

    }

}
