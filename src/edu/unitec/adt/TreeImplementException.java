
package edu.unitec.adt;

/**
 * Excepción que es dispara cuando un árbol recibe una implementación 
 * diferente en alguno de sus métodos.
 * @author EdilsonFernando
 */
public class TreeImplementException extends RuntimeException {

    /**
     * Constructor por defecto
     */
    public TreeImplementException() {
        super();
    }

    /**
     * Constructor que recibe como parámetro un <code>String</code> con la 
     * descripción del problema.
     * @param string Descripción del problema 
     */
    public TreeImplementException(String string) {
        super(string);
    }
    
}
