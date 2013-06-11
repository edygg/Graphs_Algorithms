
package edu.unitec.adt;

/**
 *
 * @author EdilsonFernando
 */
public abstract class ADTSet {
    public abstract boolean addElement(Object E);
    public abstract boolean contains(Object E);
    public abstract boolean removeElement(Object E);
    public abstract ADTSet union(ADTSet set);
    public abstract ADTSet intersection(ADTSet set);
    public abstract ADTSet substraction(ADTSet set);
    public abstract boolean isEmpty();
}
