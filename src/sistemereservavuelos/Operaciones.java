/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemereservavuelos;
import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author jainer Said Garcia Gonzalez
 */

public class Operaciones {

    // 1. Buscar vuelos por ciudad
    public void buscarVuelosPorCiudad(String ciudad) {
        String sql = "SELECT v.idVuelo, v.numeroVuelo, v.fechaVuelo, v.horaSalida, v.horaLlegada, a.ciudad " +
                     "FROM Vuelo v " +
                     "JOIN Aeropuerto a ON v.idAeropuerto = a.idAeropuerto " +
                     "WHERE a.ciudad LIKE ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + ciudad + "%");
            ResultSet rs = stmt.executeQuery();
            System.out.println("\n--- Vuelos encontrados ---");
            while (rs.next()) {
                System.out.printf("ID: %d | Vuelo: %s | Fecha: %s | %s - %s | Ciudad: %s%n",
                    rs.getInt("idVuelo"),
                    rs.getString("numeroVuelo"),
                    rs.getDate("fechaVuelo"),
                    rs.getTime("horaSalida"),
                    rs.getTime("horaLlegada"),
                    rs.getString("ciudad"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. Crear reserva
    public void crearReserva() {
        Scanner sc = new Scanner(System.in);
        try (Connection conn = ConexionBD.getConnection()) {

            System.out.print("ID pasajero: ");
            int idPasajero = sc.nextInt();
            System.out.print("ID vuelo: ");
            int idVuelo = sc.nextInt();
            System.out.print("ID asiento: ");
            int idAsiento = sc.nextInt();
            System.out.print("ID pago: ");
            int idPago = sc.nextInt();
            System.out.print("ID empleado: ");
            int idEmpleado = sc.nextInt();
            sc.nextLine(); // limpiar buffer
            System.out.print("Fecha reserva (YYYY-MM-DD): ");
            String fechaReserva = sc.nextLine();
            System.out.print("Estado (Confirmada, Pendiente, Cancelada): ");
            String estado = sc.nextLine();

            String sql = "INSERT INTO Reserva (idPasajero, idVuelo, idAsiento, idPago, idEmpleado, fechaReserva, estadoReserva) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idPasajero);
                stmt.setInt(2, idVuelo);
                stmt.setInt(3, idAsiento);
                stmt.setInt(4, idPago);
                stmt.setInt(5, idEmpleado);
                stmt.setString(6, fechaReserva);
                stmt.setString(7, estado);
                stmt.executeUpdate();
                System.out.println("✅ Reserva creada correctamente.");
            } catch (SQLException e) {
                System.out.println("❌ Error al crear la reserva: " + e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Consultar itinerarios desde la vista
    public void consultarItinerarios() {
        String sql = "SELECT * FROM ReservasDetalle ORDER BY numeroVuelo, nroOrden";
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- Itinerarios ---");
            while (rs.next()) {
                System.out.printf("Reserva: %d | Pasajero: %s %s | Vuelo: %s | Asiento: %s | Fecha: %s | Estado: %s | Orden: %d%n",
                    rs.getInt("idReserva"),
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getString("numeroVuelo"),
                    rs.getString("numeroAsiento"),
                    rs.getDate("fechaReserva"),
                    rs.getString("estadoReserva"),
                    rs.getInt("nroOrden"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

