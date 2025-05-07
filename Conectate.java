package clinicarehab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectate {
    private Connection conn;

    public Conectate() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Rehabtec?useSSL=false";
            String user = "root";
            String password = "";
            
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("¡Conexión exitosa!");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar: " + e.getMessage());
        }
    }
}
