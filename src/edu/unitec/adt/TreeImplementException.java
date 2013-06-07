package edu.unitec.adt;

/**
 * Excepción que es deisparada cuando un árbol recibe una implementación 
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
     * Contructor que recibe como parámetro un String con la descrición del
     * problema.
     * @param string Descripción del problema 
     */
    public TreeImplementException(String string) {
        super(string);
    }
    
}
