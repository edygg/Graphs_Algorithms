package edu.unitec.graphs;

/**
 *
 * @author EdilsonFernando
 */
public class Vertex implements Comparable<Vertex> {

    private Comparable data;

    public Vertex(Comparable data) {
        this.data = data;
    }

    public Comparable getData() {
        return data;
    }

    public void setData(Comparable data) {
        this.data = data;
    }

    @Override
    public int compareTo(Vertex t) {
        return data.compareTo(t.data);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Vertex) {
            return false;
        }
        
        return data.equals(((Vertex) o).data);
    }

    @Override
    public String toString() {
        return data.toString();
    }
    
    
}
