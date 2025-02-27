/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Raul
 */

//ARCHIVO PARA PANTALLA EN JRAME
public class TSP {
    private int numCiudades;
    private String[] nombresCiudades;
    private int[][] matrizDistancias;
    private int mejorCosto = Integer.MAX_VALUE;
    private List<Integer> mejorRuta;

    public TSP(int numCiudades, String[] nombresCiudades, int[][] matrizDistancias) {
        this.numCiudades = numCiudades;
        this.nombresCiudades = nombresCiudades;
        this.matrizDistancias = matrizDistancias;
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
        
        costoTotal += matrizDistancias[ciudadActual][ciudadInicial]; // Regreso a la ciudad inicial

        if (costoTotal < mejorCosto) {
            mejorCosto = costoTotal;
            mejorRuta = new ArrayList<>(ciudades);
            mejorRuta.add(0, ciudadInicial);
            mejorRuta.add(ciudadInicial);
        }
    }

    public String obtenerRutaComoTexto() {
        StringBuilder sb = new StringBuilder();
        for (int ciudad : mejorRuta) {
            sb.append(nombresCiudades[ciudad]).append(" -> ");
        }
        return sb.substring(0, sb.length() - 4); // Elimina el último " -> "
    }

    public int getMejorCosto() {
        return mejorCosto;
    }
}

