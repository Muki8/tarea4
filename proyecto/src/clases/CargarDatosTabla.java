/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author el_mi
 */
public class CargarDatosTabla {

    public static void cargarDatosTabla(JTable jTable1) {
        String url = "jdbc:mysql://localhost:3306/users"; // Cambia la URL si es necesario
        String user = "tu_usuario"; // Cambia por el usuario de tu base de datos
        String password = "tu_contraseña"; // Cambia por la contraseña de tu base de datos

        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT Nombre, Apellido, Telefono, Correo, Usuario FROM users")) {

            // Borra los datos existentes en la tabla
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            // Agrega los nuevos datos desde la base de datos a la tabla
            while (rs.next()) {
                String nombre = rs.getString("Nombre");
                String apellido = rs.getString("Apellido");
                String telefono = rs.getString("Telefono");
                String correo = rs.getString("Correo");
                String usuario = rs.getString("Usuario");
                model.addRow(new Object[]{nombre, apellido, telefono, correo, usuario});
            }
        } catch (Exception e) {
            // Manejo de errores, muestra un mensaje de error si hay un problema con la conexión o la consulta.
             JOptionPane.showMessageDialog(null, "Error al cargar los datos desde la base de datos:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}