
package edu.unitec.graphs;

/**
 * Clase arista que almacena el peso de la arista como <code>double</code>.
 * @author EdilsonFernando
 */
public class Edge {

    /**
     * Peso de la arista.
     */
    private double weight;

    /**
     * Constructor por defecto. Se inicializa con un peso real.
     * @param weight Peso de la arista.
     */
    public Edge(double weight) {
        this.weight = weight;
    }

    /**
     * Método accesor que retorna el peso de la arista.
     * @return Un <code>double</code> con el peso de la arista.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Método mutador para el peso de la arista.
     * @param weight Nuevo peso de la arista.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Representación <code>String</code> de la arista.
     * @return Retorna un <code>String</code> con los datos de la arista.
     */
    @Override
    public String toString() {
        return Double.toString(weight);
    }
    
}
