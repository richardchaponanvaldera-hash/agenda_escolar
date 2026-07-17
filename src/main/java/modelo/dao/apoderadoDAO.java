package modelo.dao;

import configuracion.Conexion;
import modelo.apoderado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class apoderadoDAO {
    Conexion con = new Conexion();
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;

    public Map<Integer, List<apoderado>> listaHijosAgrupadosPorApoderado(int idUsuario) {
        Map<Integer, List<apoderado>> hijosAgrupados = new HashMap<>();
        String sql = " SELECT \n" +
                "A.id_apoderado, \n" +
                "    H.id_hijos,\n" +
                "    E.id_estudiante,\n" +
                "    U.nombre AS nombre_estudiante,\n" +
                "    U.apellido AS apellido_estudiante,\n" +
                "    G.grado,\n" +
                "    C.id_curso, \n" +
                "    C.nombre_curso,\n" +
                "    C.periodo_academico\n" +
                "FROM \n" +
                "    Hijos H\n" +
                "INNER JOIN \n" +
                "    Apoderado A ON H.id_apoderado = A.id_apoderado\n" +
                "INNER JOIN \n" +
                "    Usuario U_apoderado ON A.id_usuario = U_apoderado.id_usuario\n" +
                "INNER JOIN \n" +
                "    Estudiante E ON H.id_estudiante = E.id_estudiante\n" +
                "INNER JOIN \n" +
                "    Usuario U ON E.id_usuario = U.id_usuario\n" +
                "INNER JOIN \n" +
                "    Grado G ON E.id_grado = G.id_grado\n" +
                "LEFT JOIN \n" +
                "    EstudianteCurso EC ON E.id_estudiante = EC.id_estudiante\n" +
                "LEFT JOIN \n" +
                "    Curso C ON EC.id_curso = C.id_curso\n" +
                "WHERE \n" +
                "    U_apoderado.id_usuario = ?";
        try {
            cn = con.Conexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                apoderado apoderado = new apoderado();
                apoderado.setIdApoderado(rs.getInt("id_apoderado"));
                apoderado.setIdHijos(rs.getInt("id_hijos"));
                apoderado.setIdEstudiante(rs.getInt("id_estudiante"));
                apoderado.setNombreEstudiante(rs.getString("nombre_estudiante"));
                apoderado.setApellidoEstudiante(rs.getString("apellido_estudiante"));
                apoderado.setGrado(rs.getString("grado"));
                apoderado.setIdCurso(rs.getInt("id_curso"));
                apoderado.setNombreCurso(rs.getString("nombre_curso"));
                apoderado.setPeridoAcademico(rs.getString("periodo_academico"));

                // Agrupar por idHijos
                hijosAgrupados.putIfAbsent(apoderado.getIdHijos(), new ArrayList<>());
                hijosAgrupados.get(apoderado.getIdHijos()).add(apoderado);
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
        return hijosAgrupados;
    }

    public Map<Integer, List<apoderado>> listaAsistenciaApoderado(int idUsuario) {
        Map<Integer, List<apoderado>> estudiantesAgrupados = new LinkedHashMap<>();
        String sql = "SELECT  \n" +
                "    A.id_apoderado, \n" +
                "    H.id_hijos,\n" +
                "    E.id_estudiante,\n" +
                "    U_estudiante.nombre AS nombre_estudiante,\n" +
                "    U_estudiante.apellido AS apellido_estudiante,\n" +
                "    G.grado,\n" +
                "    C.id_curso,\n" +
                "    C.nombre_curso,\n" +
                "    C.periodo_academico,\n" +
                "    ASIS.fecha AS fecha_asistencia,\n" +
                "    ASIS.estado AS estado_asistencia,\n" +
                "    H_curso.dia AS dia_clase\n" +
                "FROM \n" +
                "    Hijos H\n" +
                "INNER JOIN \n" +
                "    Apoderado A ON H.id_apoderado = A.id_apoderado\n" +
                "INNER JOIN \n" +
                "    Usuario U_apoderado ON A.id_usuario = U_apoderado.id_usuario\n" +
                "INNER JOIN \n" +
                "    Estudiante E ON H.id_estudiante = E.id_estudiante\n" +
                "INNER JOIN \n" +
                "    Usuario U_estudiante ON E.id_usuario = U_estudiante.id_usuario\n" +
                "INNER JOIN \n" +
                "    Grado G ON E.id_grado = G.id_grado\n" +
                "LEFT JOIN \n" +
                "    EstudianteCurso EC ON E.id_estudiante = EC.id_estudiante\n" +
                "LEFT JOIN \n" +
                "    Curso C ON EC.id_curso = C.id_curso\n" +
                "LEFT JOIN \n" +
                "    Asistencia ASIS ON E.id_estudiante = ASIS.id_estudiante AND C.id_curso = ASIS.id_curso\n" +
                "LEFT JOIN \n" +
                "    Horario H_curso ON C.id_curso = H_curso.id_curso\n" +
                "WHERE \n" +
                "    U_apoderado.id_usuario = ? \n" +
                "ORDER BY \n" +
                "    ASIS.fecha DESC, H_curso.dia ASC";

        try {
            cn = con.Conexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                apoderado apoderado = new apoderado();
                apoderado.setIdApoderado(rs.getInt("id_apoderado"));
                apoderado.setIdHijos(rs.getInt("id_hijos"));
                apoderado.setIdEstudiante(rs.getInt("id_estudiante"));
                apoderado.setNombreEstudiante(rs.getString("nombre_estudiante"));
                apoderado.setApellidoEstudiante(rs.getString("apellido_estudiante"));
                apoderado.setGrado(rs.getString("grado"));
                apoderado.setIdCurso(rs.getInt("id_curso"));
                apoderado.setNombreCurso(rs.getString("nombre_curso"));
                apoderado.setPeridoAcademico(rs.getString("periodo_academico"));
                apoderado.setFecha(rs.getString("fecha_asistencia"));
                apoderado.setEstado(rs.getInt("estado_asistencia"));
                apoderado.setDiaClase(rs.getString("dia_clase"));

                // Agrupar por id_estudiante
                int idEstudiante = rs.getInt("id_estudiante");
                estudiantesAgrupados.putIfAbsent(idEstudiante, new ArrayList<>());
                estudiantesAgrupados.get(idEstudiante).add(apoderado);
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
        return estudiantesAgrupados;
    }



    public Map<Integer, List<apoderado>> listaNotasApoderado(int idUsuario) {
        Map<Integer, List<apoderado>> estudiantesAgrupados = new LinkedHashMap<>();
        String sql = "  select\n" +
                " E.id_estudiante, \n" +
                "    U.nombre,\n" +
                "    U.apellido,\n" +
                "    C.nombre_curso,\n" +
                "    CAL.calificacion_1,\n" +
                "    CAL.calificacion_2,\n" +
                "    CAL.calificacion_3,\n" +
                "    CAL.calificacion_4,\n" +
                "    ROUND((CAL.calificacion_1 + CAL.calificacion_2 + CAL.calificacion_3 + CAL.calificacion_4) / 4, 2) AS Promedio\n" +
                "FROM \n" +
                "    Apoderado A\n" +
                "INNER JOIN \n" +
                "    Hijos H ON A.id_apoderado = H.id_apoderado\n" +
                "INNER JOIN \n" +
                "    Estudiante E ON H.id_estudiante = E.id_estudiante\n" +
                "INNER JOIN \n" +
                "    Usuario U ON E.id_usuario = U.id_usuario\n" +
                "INNER JOIN \n" +
                "    Calificacion CAL ON E.id_estudiante = CAL.id_estudiante\n" +
                "INNER JOIN \n" +
                "    Curso C ON CAL.id_curso = C.id_curso\n" +
                "WHERE \n" +
                "    A.id_usuario = ?";

        try {
            cn = con.Conexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                apoderado apoderado = new apoderado();
                apoderado.setIdEstudiante(rs.getInt("id_estudiante"));
                apoderado.setNombreEstudiante(rs.getString("nombre"));
                apoderado.setApellidoEstudiante(rs.getString("apellido"));
                apoderado.setNombreCurso(rs.getString("nombre_curso"));
                apoderado.setCalificacion1(rs.getDouble("calificacion_1"));
                apoderado.setCalificacion2(rs.getDouble("calificacion_2"));
                apoderado.setCalificacion3(rs.getDouble("calificacion_3"));
                apoderado.setCalificacion4(rs.getDouble("calificacion_4"));
                apoderado.setPromedio(rs.getDouble("promedio"));

                // Agrupar por id_estudiante
                int idEstudiante = rs.getInt("id_estudiante");
                estudiantesAgrupados.putIfAbsent(idEstudiante, new ArrayList<>());
                estudiantesAgrupados.get(idEstudiante).add(apoderado);
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
        return estudiantesAgrupados;
    }



    public Map<Integer, List<apoderado>> listaHorarioApoderado(int idUsuario) {
        Map<Integer, List<apoderado>> estudiantesAgrupados = new LinkedHashMap<>();
        String sql = " SELECT \n" +
                "E.id_estudiante,\n" +
                "H.id_horario, \n" +
                "    U.nombre,\n" +
                "    U.apellido,\n" +
                "    H.dia,\n" +
                "    C.nombre_curso,\n" +
                "    H.hora_inicio,\n" +
                "    H.hora_fin\n" +
                "FROM \n" +
                "    Usuario AS UA\n" +
                "    INNER JOIN Apoderado AS A ON UA.id_usuario = A.id_usuario\n" +
                "    INNER JOIN Hijos AS HJO ON A.id_apoderado = HJO.id_apoderado\n" +
                "    INNER JOIN Estudiante AS E ON HJO.id_estudiante = E.id_estudiante\n" +
                "    INNER JOIN EstudianteCurso AS EC ON E.id_estudiante = EC.id_estudiante\n" +
                "    INNER JOIN Curso AS C ON EC.id_curso = C.id_curso\n" +
                "    INNER JOIN Horario AS H ON C.id_curso = H.id_curso\n" +
                "    INNER JOIN Seccion AS SE ON C.id_seccion = SE.id_seccion\n" +
                "    INNER JOIN Turno AS T ON C.id_turno = T.id_turno\n" +
                "    INNER JOIN Usuario AS U ON E.id_usuario = U.id_usuario\n" +
                "WHERE \n" +
                "    UA.id_usuario = ?";

        try {
            cn = con.Conexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                apoderado apoderado = new apoderado();
                apoderado.setIdEstudiante(rs.getInt("id_estudiante"));
                apoderado.setIdHorario(rs.getInt("id_horario"));
                apoderado.setNombreEstudiante(rs.getString("nombre"));
                apoderado.setApellidoEstudiante(rs.getString("apellido"));
                apoderado.setDiaClase(rs.getString("dia"));
                apoderado.setNombreCurso(rs.getString("nombre_curso"));
                apoderado.setHoraInicio(rs.getString("hora_inicio"));
                apoderado.setHoraFin(rs.getString("hora_fin"));

                // Agrupar por id_estudiante
                int idEstudiante = rs.getInt("id_estudiante");
                estudiantesAgrupados.putIfAbsent(idEstudiante, new ArrayList<>());
                estudiantesAgrupados.get(idEstudiante).add(apoderado);
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
        return estudiantesAgrupados;
    }


    public Map<Integer, List<apoderado>> listaAgendaApoderado(int idUsuario) {
        Map<Integer, List<apoderado>> estudiantesAgrupados = new LinkedHashMap<>();
        String sql = "select\n" +
                "                   E.id_estudiante, \n" +
                "    U.nombre,\n" +
                "    U.apellido ,\n" +
                "    C.nombre_curso,\n" +
                "    A.descripcion, \n" +
                "    A.fecha_creacion\n" +
                "FROM \n" +
                "    Hijos H\n" +
                "JOIN \n" +
                "    Apoderado AP ON H.id_apoderado = AP.id_apoderado\n" +
                "JOIN \n" +
                "    Usuario UA ON AP.id_usuario = UA.id_usuario\n" +
                "JOIN \n" +
                "    Estudiante E ON H.id_estudiante = E.id_estudiante\n" +
                "JOIN \n" +
                "    Usuario U ON E.id_usuario = U.id_usuario\n" +
                "JOIN \n" +
                "    EstudianteCurso EC ON E.id_estudiante = EC.id_estudiante\n" +
                "JOIN \n" +
                "    Curso C ON EC.id_curso = C.id_curso\n" +
                "JOIN \n" +
                "    Agenda A ON C.id_curso = A.id_curso\n" +
                "WHERE \n" +
                "    UA.id_usuario = ?";

        try {
            cn = con.Conexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                apoderado apoderado = new apoderado();
                apoderado.setIdEstudiante(rs.getInt("id_estudiante"));
                apoderado.setNombreEstudiante(rs.getString("nombre"));
                apoderado.setApellidoEstudiante(rs.getString("apellido"));
                apoderado.setNombreCurso(rs.getString("nombre_curso"));
                apoderado.setDescripcion(rs.getString("descripcion"));
                apoderado.setFecha(rs.getString("fecha_creacion"));

                // Agrupar por id_estudiante
                int idEstudiante = rs.getInt("id_estudiante");
                estudiantesAgrupados.putIfAbsent(idEstudiante, new ArrayList<>());
                estudiantesAgrupados.get(idEstudiante).add(apoderado);
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
        return estudiantesAgrupados;
    }




    public static void main(String[] args) {
        apoderadoDAO dao = new apoderadoDAO();
        Map<Integer, List<apoderado>> estudiantesAgrupados = dao.listaAgendaApoderado(18);

        for (Map.Entry<Integer, List<apoderado>> entry : estudiantesAgrupados.entrySet()) {
            int idEstudiante = entry.getKey();
            List<apoderado> cursos = entry.getValue();

            if (!cursos.isEmpty()) {
                apoderado estudiante = cursos.get(0);
                System.out.println("ID Estudiante: " + idEstudiante);
                System.out.println("Nombre Estudiante: " + estudiante.getNombreEstudiante() + " " + estudiante.getApellidoEstudiante());
            }

            for (apoderado curso : cursos) {
                System.out.println("  Curso: " + curso.getNombreCurso());
                System.out.println("  hora inicio: " + curso.getNombreCurso());
                System.out.println("  hora fin: " + curso.getDescripcion());

            }
            System.out.println("------------------------------");
        }


    }
}
