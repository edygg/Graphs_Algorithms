package edu.unitec.adt;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.unitec.graphs.Edge;
import java.util.Arrays;

/**
 *
 * @author EdilsonFernando
 */
public class GraphsAlgorithms {

    public static double[] dijkstra(DirectedSparseGraph<String, Edge> graph, String vertex) {
        ADTSet S = new ListSet();
        S.addElement(vertex);

        ADTSet V = new ListSet();
        ADTSet V_S = new ListSet();

        Object[] vertices = graph.getVertices().toArray();
        Arrays.sort(vertices);
        for (int i = 0; i < vertices.length; i++) {
            V.addElement(vertices[i]);
            V_S.addElement(vertices[i]);
        }

        V_S.substraction(S);

        double[] shortPaths = new double[vertices.length];
        Arrays.fill(shortPaths, Double.POSITIVE_INFINITY);
        String currentShortVertex = "";
        double shortWeight = 0.0;

        //Situación inicial
        //----------------------------------------------------------------------

        Object[] neighbors = graph.getNeighbors(vertex).toArray();
        Arrays.sort(neighbors);
        for (int i = 0; i < neighbors.length; i++) {
            double w = graph.findEdge(vertex, (String) neighbors[i]).getWeight();

            shortPaths[Arrays.binarySearch(vertices, neighbors[i])] = w;

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
        //----------------------------------------------------------------------

        while (!V_S.isEmpty()) {
            S.addElement(currentShortVertex);
            V_S.substraction(S);

            ADTList tmp = ((ListSet) V_S).toList();

            for (int i = 0; i < tmp.getSize(); i++) {
                if (graph.findEdge(currentShortVertex, (String) tmp.get(i)) != null) {
                    shortPaths[Arrays.binarySearch(vertices, tmp.get(i))] =
                            Math.min(shortPaths[Arrays.binarySearch(vertices, tmp.get(i))],
                            shortWeight + graph.findEdge(currentShortVertex, (String) tmp.get(i)).getWeight());
                }
            }

            double[] currentPaths = new double[((ListSet) V_S).toList().getSize()];

            for (int i = 0; i < currentPaths.length; i++) {
                currentPaths[i] = shortPaths[Arrays.binarySearch(vertices, ((ListSet) V_S).toList().get(i))];
            }
            shortWeight = min(currentPaths);

            for (int i = 0; i < ((ListSet) V_S).toList().getSize(); i++) {
                if (shortWeight == shortPaths[Arrays.binarySearch(vertices, ((ListSet) V_S).toList().get(i))]) {
                    currentShortVertex = (String) ((ListSet) V_S).toList().get(i);
                }
            }

        }


        return shortPaths;
    }

    private static double min(double[] a) {
        if (a.length == 0) {
            return 0;
        } else if (a.length == 1) {
            return a[0];
        }

        double m = a[0];

        for (int i = 1; i < a.length; i++) {
            m = Math.min(m, a[i]);
        }

        return m;
    }
}
