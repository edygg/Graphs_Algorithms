package edu.unitec.graphs;

/**
 *
 * @author EdilsonFernando
 */
public class Edge {

    private double weight;

    public Edge(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return Double.toString(weight);
    }
    
    
}
