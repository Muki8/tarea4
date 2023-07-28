/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author el_mi
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionMySQL {
    private static final String URL = "jdbc:mysql://localhost:3306/form";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return null;
    }

    public static void insertarUsuario(String nombre, String apellido, String usuario,
                                       String telefono, String correo, String contrasena) {
        String consulta = "INSERT INTO usuarios (nombre, apellido, usuario, telefono, correo, contrasena) " +
                          "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conexion = getConnection();
             PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setString(3, usuario);
            pstmt.setString(4, telefono);
            pstmt.setString(5, correo);
            pstmt.setString(6, contrasena);

            pstmt.executeUpdate();
            System.out.println("Nuevo usuario agregado con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al insertar el usuario: " + e.getMessage());
        }
    }

    public static void consultarUsuarios() {
        String consulta = "SELECT nombre, apellido, usuario, telefono, correo FROM form";
        try (Connection conexion = getConnection();
             PreparedStatement pstmt = conexion.prepareStatement(consulta);
             ResultSet resultado = pstmt.executeQuery()) {

            // Procesar el resultado
            while (resultado.next()) {
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                String usuario = resultado.getString("usuario");
                String telefono = resultado.getString("telefono");
                String correo = resultado.getString("correo");

                // Hacer lo que necesites con los datos recuperados
                System.out.println("Nombre: " + nombre);
                System.out.println("Apellido: " + apellido);
                System.out.println("Usuario: " + usuario);
                System.out.println("Teléfono: " + telefono);
                System.out.println("Correo: " + correo);
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar la consulta: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Ejemplo de uso para agregar un nuevo usuario a la tabla
        insertarUsuario("Juan", "Pérez", "juan123", "1234567890", "juan@example.com", "contraseña123");
        
        // Ejemplo de uso para consultar los usuarios en la tabla
        consultarUsuarios();
    }
}

