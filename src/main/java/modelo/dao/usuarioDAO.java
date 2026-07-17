package modelo.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import configuracion.Conexion;
import modelo.usuario;
import org.mindrot.jbcrypt.BCrypt;

public class usuarioDAO {

    Conexion con = new Conexion();
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;

    public usuario validar(String correo, String contrasena) {
        usuario usuario = null;
        String sql = "select * from Usuario where correo=?";
        try {
            cn = con.Conexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, correo);
            rs = ps.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("contrasena");
                if (BCrypt.checkpw(contrasena, hashedPassword)) {
                    usuario = new usuario();

                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellido(rs.getString("apellido"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setContrasena(hashedPassword);
                    usuario.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                    usuario.setDni(rs.getString("dni"));
                    usuario.setIdRol(rs.getInt("id_rol"));
                    usuario.setEstado(rs.getString("activo"));
                }
            }
        } catch (Exception e) {
                e.printStackTrace();
            }finally{
                try{
                    if (ps != null) ps.close();
                    if (rs != null) rs.close();
                    if (cn != null) cn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
         }
}

        return usuario;
    }


    public static void main(String[] args) {
        usuarioDAO test = new usuarioDAO();

        usuario usuarioValidado = test.validar("", "");

        if (usuarioValidado.getIdUsuario() != 0) {
            System.out.println("Usuario encontrado:");
            System.out.println("ID Usuario: " + usuarioValidado.getIdUsuario());
            System.out.println("Nombre: " + usuarioValidado.getNombre());
            System.out.println("Apellido: " + usuarioValidado.getApellido());
            System.out.println("Correo: " + usuarioValidado.getCorreo());
            System.out.println("Contraseña: " + usuarioValidado.getContrasena());
            System.out.println("Fecha de Nacimiento: " + usuarioValidado.getFechaNacimiento());
            System.out.println("DNI: " + usuarioValidado.getDni());
            System.out.println("ID Rol: " + usuarioValidado.getIdRol());
            System.out.println("Estado: " + usuarioValidado.getEstado());
        } else {
            System.out.println("Usuario no encontrado o credenciales incorrectas.");
        }
    }
}
