/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Raul
 */
public class TSP1 {

    private int numCiudades;
    private String[] nombresCiudades;
    private int[][] matrizDistancias;
    private int mejorCosto = Integer.MAX_VALUE;
    private List<Integer> mejorRuta;

    public TSP1(int numCiudades) {
        this.numCiudades = numCiudades;
        this.nombresCiudades = new String[numCiudades];
        this.matrizDistancias = new int[numCiudades][numCiudades];
    }

    public void agregarCiudad(int indice, String nombre) {
        nombresCiudades[indice] = nombre;
    }

    public void agregarDistancia(int ciudad1, int ciudad2, int distancia) {
        matrizDistancias[ciudad1][ciudad2] = distancia;
        matrizDistancias[ciudad2][ciudad1] = distancia; 
    }

    public void resolverTSP(int ciudadInicial) {
        List<Integer> ciudades = new ArrayList<>();
        for (int i = 0; i < numCiudades; i++) {
            if (i != ciudadInicial) {
                ciudades.add(i);
            }
        }

        mejorCosto = Integer.MAX_VALUE;
        mejorRuta = new ArrayList<>();
        permutar(ciudades, 0, ciudadInicial);
        
        System.out.println("Mejor ruta encontrada: " + obtenerRutaComoTexto());
        System.out.println("Costo minimo: " + mejorCosto);
    }

    private void permutar(List<Integer> ciudades, int indice, int ciudadInicial) {
        if (indice == ciudades.size()) {
            evaluarRuta(ciudades, ciudadInicial);
            return;
        }

        for (int i = indice; i < ciudades.size(); i++) {
            Collections.swap(ciudades, i, indice);
            permutar(ciudades, indice + 1, ciudadInicial);
            Collections.swap(ciudades, i, indice);
        }
    }

    private void evaluarRuta(List<Integer> ciudades, int ciudadInicial) {
        int costoTotal = 0;
        int ciudadActual = ciudadInicial;

        for (int ciudad : ciudades) {
            costoTotal += matrizDistancias[ciudadActual][ciudad];
            ciudadActual = ciudad;
        }
        
        costoTotal += matrizDistancias[ciudadActual][ciudadInicial]; 

        if (costoTotal < mejorCosto) {
            mejorCosto = costoTotal;
            mejorRuta = new ArrayList<>(ciudades);
            mejorRuta.add(0, ciudadInicial);
            mejorRuta.add(ciudadInicial);
        }
    }

    private String obtenerRutaComoTexto() {
        StringBuilder sb = new StringBuilder();
        for (int ciudad : mejorRuta) {
            sb.append(nombresCiudades[ciudad]).append(" -> ");
        }
        return sb.substring(0, sb.length() - 4); // Elimina el último " -> "
    }

    public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el numero de ciudades: ");
        int numCiudades = scanner.nextInt();
        scanner.nextLine();

        TSP1 tsp = new TSP1(numCiudades);

        System.out.println("Ingrese los nombres de las ciudades:");
        for (int i = 0; i < numCiudades; i++) {
            System.out.print("Ciudad " + (i + 1) + ": ");
            String nombre = scanner.nextLine();
            tsp.agregarCiudad(i, nombre);
        }
        
        System.out.println("");
        System.out.println("Ingrese la matriz de distancias entre ciudades (numeros separados por espacio):");
        System.out.println("Ej: (distancia desde ciudad1: [distancia a ciudad1] [distancia a ciudad 2] etc)");
        for (int i = 0; i < numCiudades; i++) {
            System.out.print("Distancias desde " + tsp.nombresCiudades[i] + ": ");
            String[] valores = scanner.nextLine().split(" ");
            for (int j = 0; j < numCiudades; j++) {
                tsp.agregarDistancia(i, j, Integer.parseInt(valores[j]));
            }
        }

        System.out.print("Ingrese el nombre de la ciudad inicial: ");
        String ciudadInicialNombre = scanner.nextLine();
        int ciudadInicial = Arrays.asList(tsp.nombresCiudades).indexOf(ciudadInicialNombre);

        tsp.resolverTSP(ciudadInicial);
    }
}
