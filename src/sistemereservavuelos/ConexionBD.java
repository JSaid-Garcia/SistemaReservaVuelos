/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemereservavuelos;

/**
 *
 * @author jainer Said Garcia Gonzalez
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=SistemaReservasVuelo;encrypt=false";
    private static final String USER = "UsuarioEscritura"; // Cambia si usas otro usuario
    private static final String PASSWORD = "Escritura123!"; // Cambia si usas otra clave

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

