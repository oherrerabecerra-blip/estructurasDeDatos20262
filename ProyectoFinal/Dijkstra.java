package ProyectoFinal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.reflect.Field;

public class Dijkstra {

    private DistanciaEdi grafo;

    // Constructor
    public Dijkstra(DistanciaEdi grafo) {
        this.grafo = grafo;
    }

    // Calcular ruta más corta
    public void calcularRuta(String origen, String destino) {

        int inicio = buscarIndice(origen);
        int fin = buscarIndice(destino);

        if (inicio == -1 || fin == -1) {
            System.out.println("Edificio no encontrado.");
            return;
        }

        int n = grafo.cantidadEdificios();

        int[][] matriz = obtenerMatriz();

        int[] distancia = new int[n];
        int[] anterior = new int[n];

        boolean[] visitado = new boolean[n];

        int infinito = Integer.MAX_VALUE;

        // Inicializar arreglos
        for (int i = 0; i < n; i++) {

            distancia[i] = infinito;
            anterior[i] = -1;
        }

        distancia[inicio] = 0;

        // Algoritmo de Dijkstra
        for (int paso = 0; paso < n; paso++) {

            int actual = -1;
            int menor = infinito;

            // Buscar el edificio con menor distancia
            for (int i = 0; i < n; i++) {

                if (!visitado[i] && distancia[i] < menor) {

                    menor = distancia[i];
                    actual = i;
                }
            }

            if (actual == -1) {
                break;
            }

            visitado[actual] = true;

            // Revisar edificios vecinos
            for (int vecino = 0; vecino < n; vecino++) {

                if (matriz[actual][vecino] > 0
                    && !visitado[vecino]) {

                    int nuevaDistancia =
                        distancia[actual] + matriz[actual][vecino];

                    if (nuevaDistancia < distancia[vecino]) {

                        distancia[vecino] = nuevaDistancia;
                        anterior[vecino] = actual;
                    }
                }
            }
        }

        // Verificar si existe una ruta
        if (distancia[fin] == infinito) {

            System.out.println("No existe una ruta.");
            return;
        }

        // Reconstruir camino
        List<Integer> camino = new ArrayList<>();

        for (int actual = fin;
             actual != -1;
             actual = anterior[actual]) {

            camino.add(actual);
        }

        Collections.reverse(camino);

        // Mostrar resultado
        System.out.println("\n===== RUTA MAS CORTA =====");

        for (int i = 0; i < camino.size(); i++) {

            System.out.print(
                grafo.getEdificio(camino.get(i))
            );

            if (i < camino.size() - 1) {

                int siguiente = camino.get(i + 1);

                System.out.print(
                    " -> (" +
                    matriz[camino.get(i)][siguiente] +
                    " metros) -> "
                );
            }
        }

        System.out.println();

        System.out.println(
            "Distancia total: " + distancia[fin] + " metros."
        );
    }

    private int buscarIndice(String nombre) {
        if (nombre == null) {
            return -1;
        }

        for (int i = 0; i < grafo.cantidadEdificios(); i++) {
            if (nombre.equals(grafo.getEdificio(i))) {
                return i;
            }
        }

        return -1;
    }

    private int[][] obtenerMatriz() {
        int n = grafo.cantidadEdificios();

        for (Class<?> tipo = grafo.getClass(); tipo != null; tipo = tipo.getSuperclass()) {
            for (Field campo : tipo.getDeclaredFields()) {
                if (campo.getType() != int[][].class) {
                    continue;
                }

                try {
                    campo.setAccessible(true);
                    int[][] matriz = (int[][]) campo.get(grafo);
                    if (matriz != null && matriz.length == n) {
                        return matriz;
                    }
                } catch (IllegalAccessException | SecurityException e) {
                    throw new IllegalStateException("No se pudo acceder a la matriz del grafo.", e);
                }
            }
        }

        throw new IllegalStateException("DistanciaEdi no contiene una matriz int[][].");
    }
}
