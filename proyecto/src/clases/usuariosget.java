package clases;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author el_mi
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class usuariosget {

    private int idusuario;
    private String usuario;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo_electronico;
    private String contraseña;

    // ... (getters y setters, que ya has definido en tu código)

    // Método para guardar un nuevo usuario en la base de datos
    public void guardarUsuario() throws SQLException {
        String query = "INSERT INTO form (nombre, apellido, usuario, correo, telefono, contrasena) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, usuario);
            statement.setString(4, correo_electronico);
            statement.setString(5, telefono);
            statement.setString(6, contraseña);
            statement.executeUpdate();
        }
    }

    // Método para actualizar un usuario existente en la base de datos
    public void actualizarUsuario() throws SQLException {
        String query = "UPDATE form SET nombre = ?, apellido = ?, correo = ?, telefono = ?, contrasena = ? WHERE idusuario = ?";
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, correo_electronico);
            statement.setString(4, telefono);
            statement.setString(5, contraseña);
            statement.setInt(6, idusuario);
            statement.executeUpdate();
        }
    }

    // Método para eliminar un usuario de la base de datos
    public void eliminarUsuario() throws SQLException {
        String query = "DELETE FROM form WHERE idusuario = ?";
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idusuario);
            statement.executeUpdate();
        }
    }

    // Método para obtener una lista de todos los usuarios en la base de datos
    public static List<usuariosget> obtenerTodosUsuarios() throws SQLException {
        List<usuariosget> usuarios = new ArrayList<>();
        String query = "SELECT * FROM form";
        try (Connection connection = ConexionMySQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                usuariosget usuario = new usuariosget();
                usuario.setIdusuario(resultSet.getInt("idusuario"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setApellido(resultSet.getString("apellido"));
                usuario.setUsuario(resultSet.getString("usuario"));
                usuario.setCorreo_electronico(resultSet.getString("correo"));
                usuario.setTelefono(resultSet.getString("telefono"));
                usuario.setContraseña(resultSet.getString("contrasena"));
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public void setContraseña(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setTelefono(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setUsuario(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setApellido(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setIdusuario(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setNombre(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setCorreo_electronico(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getContraseña() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getTelefono() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getCorreo_electronico() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getApellido() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getNombre() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}


