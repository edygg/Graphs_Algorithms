
package edu.unitec.adt;

import java.io.Serializable;

/**
 * Clase Padre Abstracta ADTStrack que modela el comportamiento de una pila.
 * Implementa <code>Serializable</code> para almacenarse en archivos binarios.
 * @author EdilsonFernando
 */
public abstract class ADTStack implements Serializable {
    
    /**
     * Mantiene el tamaño de la pila.
     */
    protected int size;
    
    /**
     * Constructor por defecto de la clase. Inicializa el tamaño en cero.
     */
    public ADTStack() {
        size = 0;
    }
    
    /**
     * Ingresa un elemento a la pila.
     * @param E Elemento que será ingresado a la pila.
     * @return Retorna <code>true</code> si es posible agregar el elemento, 
     * <code>false</code> en caso contrario.
     */
    public abstract boolean push(Object E);
    
    /**
     * Saca el último elemento ingresado a la pila. Retorna <code>null</code> 
     * si la pila está vacía.
     * @return Retorna el último objeto ingresado a la pila, si está vacía
     * retorna <code>null</code>.
     */
    public abstract Object pop();
    
    /**
     * Retorna el último elemento ingresado a la pila sin extraerlo de ésta.
     * @return Retorna el último elemento ingresado a la pila.
     */
    public abstract Object peek();
    
    /**
     * Método accesor para el tamaño de la pila.
     * @return Retorna el tamaño de la pila.
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Método que devuelve una representación booleana del estado de la pila.
     * @return Retorna <code>true</code> si la pila está vacía, <code>false</code> 
     * en caso contrario.
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
}
