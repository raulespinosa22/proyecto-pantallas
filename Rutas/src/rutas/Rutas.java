/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rutas;

import java.util.Scanner;

/**
 *
 * @author anita
 */
public class Rutas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
     
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el numero de ciudades: ");
        int numCiudades = scanner.nextInt();
        scanner.nextLine();

        Grafo grafo = new Grafo(numCiudades);

        System.out.println("Ingrese los nombres de las ciudades:");
        for (int i = 0; i < numCiudades; i++) {
            System.out.print("Ciudad " + (i + 1) + ": ");
            String nombreCiudad = scanner.nextLine();
            grafo.agregarCiudad(nombreCiudad, i);
        }

        System.out.println("Ingrese las rutas entre ciudades (Formato: Ciudad1 Ciudad2 Distancia) o escriba 'fin' para terminar:");
        while (true) {
            String entrada = scanner.nextLine();
            if (entrada.equalsIgnoreCase("fin")) break;
            String[] datos = entrada.split(" ");
            grafo.agregarRuta(datos[0], datos[1], Integer.parseInt(datos[2]));
        }

        System.out.print("Ingrese la ciudad inicial: ");
        String ciudadInicial = scanner.nextLine();
        grafo.dijkstra(ciudadInicial);
    }
    
}
