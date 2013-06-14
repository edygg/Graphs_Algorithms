package edu.unitec.adt;

import java.util.Comparator;

/**
 * Clase comparadora de objetos, implementa <code>Comparator</code> para 
 * comparar objetos que no implementen Comparable ni sobrecarguen el método 
 * <code>compareTo()</code>. Con esta clase se mantienen ordenadas las listas
 * de este mismo paquete.
 * @see SortAlgorithms
 * @author EdilsonFernando
 */
public class DataNodeComparator implements Comparator<Object> {

    /**
     * Método que retorna 0 en caso de que <code>t</code> sea igual que 
     * <code>t1</code>, -1 si <code>t</code> es menor que <code>t1</code> y 
     * 1 si <code>t</code> es menor que <code>t1</code>.
     * @param t Primer objeto a comparar.
     * @param t1 Segundo Objeto a comparar.
     * @return Retorna 0, -1, 1 dependiendo de la relación que exista entre t y
     * t1.
     */
    @Override
    public int compare(Object t, Object t1) {
        //revisa que ambos objetos sean comparables
        if (t instanceof Comparable && t1 instanceof Comparable) {
            Comparable tmp1 = (Comparable) t;   //Castea los objetos a Comparables
            Comparable tmp2 = (Comparable) t1;
            //Retorna el resultado de comparar t con t1
            return tmp1.compareTo(tmp2);
        } else {
            throw new ClassCastException("One or many objets are not comparable");
        }       
    }
}
