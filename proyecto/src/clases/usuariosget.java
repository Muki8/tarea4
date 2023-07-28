package clases;

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

    // Constructor (puedes agregarlo si lo necesitas)

    // Getters y Setters
    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    // Método para guardar un nuevo usuario en la base de datos
    public void guardarUsuario() throws SQLException {
        // Asegúrate de que las propiedades hayan sido inicializadas antes de guardar el usuario
        if (nombre == null || apellido == null || usuario == null || correo_electronico == null || telefono == null || contraseña == null) {
            System.out.println("Error: Faltan datos del usuario para guardar en la base de datos.");
            return;
        }

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
        // Asegúrate de que las propiedades hayan sido inicializadas antes de actualizar el usuario
        if (nombre == null || apellido == null || usuario == null || correo_electronico == null || telefono == null || contraseña == null) {
            System.out.println("Error: Faltan datos del usuario para actualizar en la base de datos.");
            return;
        }

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
        // Asegúrate de que la propiedad idusuario haya sido inicializada antes de eliminar el usuario
        if (idusuario == 0) {
            System.out.println("Error: El ID del usuario no ha sido especificado para eliminar de la base de datos.");
            return;
        }

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
}
