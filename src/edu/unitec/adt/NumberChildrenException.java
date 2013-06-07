package edu.unitec.adt;

/**
 * Excepción que se lanza cuando un árbol no podee la cantidad de hijos 
 * requeridos en sus respectivas creaciones.
 * @see ADTTree
 * @author EdilsonFernando
 */
public class NumberChildrenException extends RuntimeException {

    /**
     * Constructor por defecto
     */
    public NumberChildrenException() {
        super();
    }

    /**
     * Constructor con un string que contiene la descripcion del error.
     * @param string Descripción del error.
     */
    public NumberChildrenException(String string) {
        super(string);
    }
    
}
