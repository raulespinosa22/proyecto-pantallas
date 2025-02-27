/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rutas;
import java.util.*;

/**
 *
 * @author anita
 */
public class Grafo {
     private int numCiudades;
    private Map<String, List<Arista>> listaAdyacencia;
    private Map<String, Integer> indicesCiudades;
    private String[] nombresCiudades;

    public Grafo(int numCiudades) {
        this.numCiudades = numCiudades;
        this.listaAdyacencia = new HashMap<>();
        this.indicesCiudades = new HashMap<>();
        this.nombresCiudades = new String[numCiudades];
    }

    public void agregarCiudad(String nombre, int indice) {
        nombresCiudades[indice] = nombre;
        indicesCiudades.put(nombre, indice);
        listaAdyacencia.put(nombre, new ArrayList<>());
    }

    public void agregarRuta(String ciudad1, String ciudad2, int distancia) {
        listaAdyacencia.get(ciudad1).add(new Arista(ciudad2, distancia));
        listaAdyacencia.get(ciudad2).add(new Arista(ciudad1, distancia));
    }

    public void dijkstra(String ciudadInicial) {
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>(Comparator.comparingInt(n -> n.distancia));
        Map<String, Integer> distancias = new HashMap<>();
        Map<String, String> predecesores = new HashMap<>();

        for (String ciudad : listaAdyacencia.keySet()) {
            distancias.put(ciudad, Integer.MAX_VALUE);
            predecesores.put(ciudad, null);
        }

        distancias.put(ciudadInicial, 0);
        colaPrioridad.add(new Nodo(ciudadInicial, 0));

        while (!colaPrioridad.isEmpty()) {
            Nodo actual = colaPrioridad.poll();
            String ciudadActual = actual.nombre;

            for (Arista arista : listaAdyacencia.get(ciudadActual)) {
                int nuevaDistancia = distancias.get(ciudadActual) + arista.distancia;
                if (nuevaDistancia < distancias.get(arista.destino)) {
                    distancias.put(arista.destino, nuevaDistancia);
                    predecesores.put(arista.destino, ciudadActual);
                    colaPrioridad.add(new Nodo(arista.destino, nuevaDistancia));
                }
            }
        }
        mostrarResultados(ciudadInicial, distancias, predecesores);
    }

    public void mostrarResultados(String ciudadInicial, Map<String, Integer> distancias, Map<String, String> predecesores) {
        System.out.println("Ruta óptima desde " + ciudadInicial + ":");
        for (String ciudad : distancias.keySet()) {
            System.out.print("Destino: " + ciudad + " - Distancia: " + distancias.get(ciudad) + " km - Ruta: ");
            imprimirRuta(ciudad, predecesores);
            System.out.println();
        }
    }

    private void imprimirRuta(String ciudad, Map<String, String> predecesores) {
        if (predecesores.get(ciudad) != null) {
            imprimirRuta(predecesores.get(ciudad), predecesores);
        }
        System.out.print(ciudad + " -> ");
    }
    
}
