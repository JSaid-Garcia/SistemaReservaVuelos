/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemereservavuelos;

import java.util.Scanner;

/**
 *
 * @author jainer said Garcia
 */

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Operaciones op = new Operaciones();
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n=== Sistema de Reservas de Vuelo ===");
            System.out.println("1. Buscar vuelos por ciudad");
            System.out.println("2. Crear reserva");
            System.out.println("3. Consultar itinerarios");
            System.out.println("0. Salir");
            System.out.print("Seleccione: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese ciudad: ");
                    String ciudad = sc.nextLine();
                    op.buscarVuelosPorCiudad(ciudad);
                    break;
                case 2:
                    op.crearReserva();
                    break;
                case 3:
                    op.consultarItinerarios();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }
}
