
package configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection con;
    String baseDeDatos = "colegio";
    String url = "jdbc:mariadb://localhost:3306/";
    String user = "colegio";
    String pass = "123456";

    public Connection Conexion() {
        try {
            Class.forName("org.mariadb.jdbc.Driver"); // Usar el driver de MariaDB
            con = DriverManager.getConnection(url + baseDeDatos, user, pass);
            System.out.println("Conexión exitosa a MariaDB");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver de MariaDB");
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return con;
    }

    public static void main(String[] args) {
        Conexion con = new Conexion();
        con.Conexion();
    }
}
