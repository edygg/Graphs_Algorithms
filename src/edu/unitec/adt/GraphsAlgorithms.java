
package edu.unitec.adt;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.unitec.graphs.Edge;
import java.util.Arrays;

/**
 * Clase que contiene métodos para encontrar caminos más cortos a través de 
 * dos algoritmos muy conocidos: Dijkstra y Floyd. Esta clase es implementada
 * específicamente para grafos que contienen vértices que sean objetos 
 * <code>String</code> y aristas que implementen la clase <code>Edge</code>.
 * @see String;
 * @see Edge
 * @author EdilsonFernando
 */
public class GraphsAlgorithms {

    /**
     * Algoritmo de Dijkstra que encuentra los caminos más cortos entre un
     * vértice y el resto pertenecientes al conjunto de vértices del grafo.
     * @param graph Grafo a analizar. Éste tiene que ser un grafo dirigido que
     * tenga vértices tipo <code>String</code> y aristas tipo <code>Edge</code>
     * para asegurar que los pesos de cada arista existan y pueda almacenarse 
     * y operarse.
     * @param vertex Vértice de partida.
     * @return Retorna una matriz de <code>double<code> con el camino más corto
     * para cada uno de los vértices dentro del gráfo.
     */
    public static double[] dijkstra(DirectedSparseGraph<String, Edge> graph, String vertex) {
        //Conjunto de aristas donde se ha encontrado en camino mínimo
        ADTSet S = new ListSet();
        //Añadiendo el vértice de partida al conjunto S
        S.addElement(vertex);

        //Conjunto de vértices
        ADTSet V = new ListSet();
        //Conjunto diferencia entre el conjunto de vértices y el conjunto S
        ADTSet V_S = new ListSet();

        //Obteniendo la lista de vértices.
        Object[] vertices = graph.getVertices().toArray();
        //Ordenando los vértices.
        Arrays.sort(vertices);
        //Añadiendo cada vértice al conjunto de vértices.
        for (int i = 0; i < vertices.length; i++) {
            V.addElement(vertices[i]);
            V_S.addElement(vertices[i]);
        }

        //Realizando la diferencia entre el conjuntos.
        V_S.substraction(S);

        //Inicializando la matriz de los caminos más cortos
        double[] shortPaths = new double[vertices.length];
        //Rellenando de infinitos la matriz de caminos para asegurar que para
        //los vérices que no existe camino se expresen de manera apropiada.
        Arrays.fill(shortPaths, Double.POSITIVE_INFINITY);
        //vértice actual que contienen el camino más corto.
        String currentShortVertex = "";
        //Peso mínimo actual.
        double shortWeight = 0.0;

        //Situación inicial
        //----------------------------------------------------------------------

        //Vecinos del vértice inicial.
        Object[] neighbors = graph.getNeighbors(vertex).toArray();
        Arrays.sort(neighbors);
        //Encontrando los caminos hacia todos lo vertices a partir de los vecinos
        for (int i = 0; i < neighbors.length; i++) {

            if (graph.findEdge(vertex, (String) neighbors[i]) != null) {
                double w = graph.findEdge(vertex, (String) neighbors[i]).getWeight();
                shortPaths[Arrays.binarySearch(vertices, neighbors[i])] = w;
                
                //Encontrando el camino más corto
                if (i == 0) {
                    shortWeight = w;
                    currentShortVertex = (String) neighbors[i];
                } else {
                    if (w < shortWeight) {
                        shortWeight = w;
                        currentShortVertex = (String) neighbors[i];
                    }
                }
            }
        }
        //----------------------------------------------------------------------

        //Viendo si se visitaron todos los nodos y se encontraron sus caminos 
        //más cortos.
        while (!V_S.isEmpty()) {
            //Actualizando los conjuntos
            S.addElement(currentShortVertex);
            V_S.substraction(S);

            //Obteniendo una lista con los vertices a los cuales no se ha
            //encontrado su camino más corto.
            ADTList tmp = ((ListSet) V_S).toList();

            //Verificando el nuevo camino más corto.
            for (int i = 0; i < tmp.getSize(); i++) {
                if (graph.findEdge(currentShortVertex, (String) tmp.get(i)) != null) {
                    shortPaths[Arrays.binarySearch(vertices, tmp.get(i))] =
                            Math.min(shortPaths[Arrays.binarySearch(vertices, tmp.get(i))],
                            shortWeight + graph.findEdge(currentShortVertex, (String) tmp.get(i)).getWeight());
                }
            }
            
            //Matriz con los caminos actualmente procesados
            double[] currentPaths = new double[((ListSet) V_S).toList().getSize()];

            for (int i = 0; i < currentPaths.length; i++) {
                currentPaths[i] = shortPaths[Arrays.binarySearch(vertices, ((ListSet) V_S).toList().get(i))];
            }
            //Encontrando el nuevo peso mínimo
            shortWeight = min(currentPaths);

            //Encontrando el vértice al cual pertenecen el camino más corto
            for (int i = 0; i < ((ListSet) V_S).toList().getSize(); i++) {
                if (shortWeight == shortPaths[Arrays.binarySearch(vertices, ((ListSet) V_S).toList().get(i))]) {
                    currentShortVertex = (String) ((ListSet) V_S).toList().get(i);
                }
            }

        }

        //Retornando la matriz con los caminos más cortos finalizada.
        return shortPaths;
    }

    /**
     * Función de apoyo para encontrar el mínimo de los caminos dentro de
     * una matriz con los caminos actuales.
     * @param a Vector que contiene los caminos actuales
     * @return retorna un <code>double</code> con el camino más corto. 
     */
    private static double min(double[] a) {
        //Si la matriz está vacía retorna una tendencia al infinito negativo
        if (a.length == 0) {
            return Double.NEGATIVE_INFINITY;
        //Si la matriz es de un solo elemento retorna ese elemento como mínimo
        } else if (a.length == 1) {
            return a[0];
        }
        
        //Si no comprar cada uno de los valores para encontrar el mínimo.
        double m = a[0];

        for (int i = 1; i < a.length; i++) {
            m = Math.min(m, a[i]);
        }

        return m;
    }
    
    /**
     * Algoritmo de Floyd. Recibe como parámetro un grafo y retorna una matriz
     * con las distancias mínimas hacia todos los pares de vértices.
     * @param graph Grafo a analizar. Éste debe de tener vértices tipo 
     * <code>String</code> y aristas tipo <code>Edge</code>.
     * @return Retorna una matriz de <code>double</code> con las distancias 
     * más cortas entre pares de vértices.
     */
    public static double[][] floyd(DirectedSparseGraph<String, Edge> graph) {
        //Inicializa la matriz de caminos más cortos
        double[][] shortPaths = new double[graph.getVertexCount()][graph.getVertexCount()];
        
        //Rellena la matriz con infinitos para asegurar que en los vértices
        //donde no haya camino se represente correctamente.
        for (int i = 0; i < shortPaths.length; i++) {
            Arrays.fill(shortPaths[i], Double.POSITIVE_INFINITY);
            shortPaths[i][i] = 0;
        }
        
        //Obtiene lo vértices del grafo y los ordena.
        Object[] vertices = graph.getVertices().toArray();
        Arrays.sort(vertices);
        
        //Verifica el camino hacia cada uno de los vértices.
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                if (i != j && graph.findEdge((String) vertices[i], (String) vertices[j]) != null) {
                    shortPaths[i][j] = graph.findEdge((String) vertices[i], (String) vertices[j]).getWeight();
                } 
            }
        }
        
        //Corriendo las comparaciones del algoritmo de Floyd
        for (int k = 0; k < shortPaths.length; k++) {
            for (int i = 0; i < shortPaths.length; i++) {
                for (int j = 0; j < shortPaths.length; j++) {
                    if (shortPaths[i][k] + shortPaths[k][j] < shortPaths[i][j]) {
                        shortPaths[i][j] = shortPaths[i][k] + shortPaths[k][j];
                    }
                }
            }
        }
        
        //Retorna la matriz con los caminos más cortos.
        return shortPaths;
    }
    
}
