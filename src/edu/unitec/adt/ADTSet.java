
package edu.unitec.adt;

/**
 * Clase padre conjunto. Esta estructura de datos se utiliza para agregar
 * claves pertenecientes a un conjunto. Contienen métodos para comparar si
 * una clave es perteneciente al conjunto, eliminar claves y hacer uniones,
 * intersecciones y diferencias entre éstos.
 * @author EdilsonFernando
 */
public abstract class ADTSet {
    /**
     * Añade un objeto al conjunto.
     * @param E Objeto que se agregará al conjunto.
     * @return Retorna <code>true</code> si el elemento se agregó exitosamente
     * al conjunto, <code>false</code> en caso contrario.
     */
    public abstract boolean addElement(Object E);
    
    /**
     * Verifica si un objeto está dentro del conjunto.
     * @param E Objeto que se verificará su existencia en el conjunto.
     * @return Retorna <code>true</code> si el objeto pertenece al conjunto, 
     * <code>false</code> en caso contrario.
     */
    public abstract boolean contains(Object E);
    
    /**
     * Elimina un objeto para que deje de pertenecer al conjunto.
     * @param E Objeto a eliminar del conjunto.
     * @return Retorna <code>true</code> si el objeto se eliminó con éxito, 
     * retorna <code>false</code> en caso contrario. Retorna <code>false</code>
     * si el objeto no pertenecía al conjunto.
     */
    public abstract boolean removeElement(Object E);
    
    /**
     * Unión de dos conjuntos, modifica el <code>this</code> y retorna una
     * referencia de éste.
     * @param set Conjunto con que se quiere realizar la unión.
     * @return Retorna una referencia al <code>this</code> del conjunto
     * modificado, o sea,´el método que invocó la función.
     */
    public abstract ADTSet union(ADTSet set);
    
    /**
     * Intersección entre dos conjuntos, retorna un nuevo conjunto con el resultado.
     * @param set Conjunto con que se desea hacer la intersección.
     * @return Retorna una referencia al <code>this</code> de conjunto
     * modificado, o sea, el método que invocó la función.
     */
    public abstract ADTSet intersection(ADTSet set);
    
    /**
     * Diferencia entre dos conjuntos, modifica el objeto <code>this</code> y
     * retorna una referencia a éste con las modificaciones pertinentes.
     * @param set Conjunto que se va a sustraer del objeto que llamó al método.
     * @return Retorna una referencia a <code>this</code> con la operación ya
     * realizada.
     */
    public abstract ADTSet substraction(ADTSet set);
    
    /**
     * Retorna un valor booleano que indica si el conjunto es un conjunto vacío
     * o no.
     * @return Retorna <code>true</code> si el conjunto es vacío, <code>false</code>
     * en caso contrario.
     */
    public abstract boolean isEmpty();
}
