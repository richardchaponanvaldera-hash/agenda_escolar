package modelo.dao;

import configuracion.Conexion;
import modelo.comunicados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class comunicadosDAO {
    Conexion con = new Conexion();
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;

    public List<comunicados> listaComunicados() {
        List<comunicados> listaComunicados = new ArrayList<>();
        String sql = "select * from Comunicados";
        try {
            cn = con.Conexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                comunicados comunicado = new comunicados();
                comunicado.setIdcomunicado(rs.getInt("id_comunicados"));
                comunicado.setContenido(rs.getString("contenido"));
                comunicado.setFecha(rs.getString("fecha"));
                listaComunicados.add(comunicado);
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
        return listaComunicados;
    }


    public int agregaComunicado(comunicados comunicado) {
        String sql = "INSERT INTO Comunicados (contenido) VALUES (?)";
        int filasInsertadas = 0;

        try (Connection cn = con.Conexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, comunicado.getContenido());
            filasInsertadas = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Muestra errores para depuración
        }

        return filasInsertadas;
    }

    // Método para eliminar un comunicado por su ID
    public boolean eliminarComunicado(int idComunicado) {
        String sql = "DELETE FROM Comunicados WHERE id_comunicados = ?";
        boolean eliminado = false;

        try (Connection cn = con.Conexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idComunicado);
            int filasAfectadas = ps.executeUpdate();
            eliminado = (filasAfectadas > 0); // Si al menos una fila fue afectada, la eliminación fue exitosa.

        } catch (SQLException e) {
            e.printStackTrace(); // Muestra errores para depuración
        }

        return eliminado;
    }


    public static void main(String[] args) {
        comunicadosDAO dao = new comunicadosDAO();
        List<comunicados> listaComunicados = dao.listaComunicados();

        if (listaComunicados != null && !listaComunicados.isEmpty()) {
            System.out.println("Lista de Comunicados:");
            for (comunicados comunicado : listaComunicados) {
                System.out.println("ID: " + comunicado.getIdcomunicado());
                System.out.println("Contenido: " + comunicado.getContenido());
                System.out.println("Fecha: " + comunicado.getFecha());
                System.out.println("--------------------------");
            }
        } else {
            System.out.println("No se encontraron comunicados.");
        }
    }
}
