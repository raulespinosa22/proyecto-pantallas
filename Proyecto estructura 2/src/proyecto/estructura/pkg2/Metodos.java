/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.estructura.pkg2;

import java.util.Scanner;
import static proyecto.estructura.pkg2.ProyectoEstructura2.INF;

/**
 *
 * @author Raul
 */
public class Metodos {
    final static int INF = 99999;
    int[][] graph;
     String[] nombresCiudades;
     int numCiudades;
     int ciudadInicial;
    public String ingresarDatos() {
        
        String Resultado;
        Scanner scanner = new Scanner(System.in);
        
        numCiudades = scanner.nextInt();
        scanner.nextLine(); 
        
        nombresCiudades = new String[numCiudades];
        System.out.println("Ingrese los nombres de las ciudades:");
        for (int i = 0; i < numCiudades; i++) {
            System.out.print("Ciudad " + (i + 1) + ": ");
            nombresCiudades[i] = scanner.nextLine();
        }
        
        graph = new int[numCiudades][numCiudades];
        System.out.println("Ingrese la matriz de distancias en kilometros(use " + INF + " para representar infinito):");
        for (int i = 0; i < numCiudades; i++) {
            for (int j = 0; j < numCiudades; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }
        
        System.out.print("Ingrese el nombre de la ciudad inicial: ");
        scanner.nextLine(); 
        String ciudadInicialNombre = scanner.nextLine();
        
        ciudadInicial = -1;
        for (int i = 0; i < numCiudades; i++) {
            if (nombresCiudades[i].equalsIgnoreCase(ciudadInicialNombre)) {
                ciudadInicial = i;
                break;
            }
        }
        
        if (ciudadInicial == -1) {
            System.out.println("Ciudad inicial no encontrada. Terminando el programa.");
            System.exit(1);
        }
        scanner.close();
        return null;
    }
    
    //Floyd-Warshall
    public String calcularRutasOptimas() {
        
        int[][] dist = new int[numCiudades][numCiudades];
        
        for (int i = 0; i < numCiudades; i++) {
            for (int j = 0; j < numCiudades; j++) {
                dist[i][j] = graph[i][j];
            }
        }
        
        for (int k = 0; k < numCiudades; k++) {
            for (int i = 0; i < numCiudades; i++) {
                for (int j = 0; j < numCiudades; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        return mostrarResultados(dist);
        
    }
    
    //Resultados
    public String mostrarResultados(int[][] dist) {
        for (int i = 0; i < numCiudades; i++) {
            System.out.print(nombresCiudades[i] + " ");
        }
        System.out.println();
        
        for (int i = 0; i < numCiudades; i++) {
            System.out.print(nombresCiudades[i] + " ");
            for (int j = 0; j < numCiudades; j++) {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
        return null;
    }
}
