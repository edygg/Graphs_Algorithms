
package edu.unitec.adt;

import java.io.Serializable;

/**
 * Clase Padre Abstracta ADTList. Posee las definiciones de los métodos que 
 * una lista conlleva. Implementa <code>Serializable</code> para ser guardada 
 * en archivos binarios.
 * @author EdilsonFernando
 */
public abstract class ADTList implements Serializable {
    /**
     * Mantiene el tamaño de la lista.
     */
    protected int size;
    
    /**
     * Constructor de la lista. Inicializa el tamaño en 0.
     */
    public ADTList() {
        this.size = 0;
    }
    
    /**
     * Inserta un elemento en la lista.
     * @param E Elemento a insertar
     * @param p Posición donde se insertará el elemento
     * @return Retorna <code>true</code> si se logró insertar el elemento en 
     * una posición válida, <code>false</code> el caso contrario.
     */
    public abstract boolean insert(Object E, int p);
    
    /**
     * Elimina un elemento de la lista.
     * @param p Posición donde se encuentra el elemento a remover
     * @return Retorna <code>true</code> si el elemento se removió exitosamente, 
     * <code>false</code> en caso contrario.
     */
    public abstract Object remove(int p);
    
    /**
     * Retorna el objeto que se encuentra en la primera posición de la lista.
     * @return Retorna el elemento en la primer posición, <code>null</code> si la
     * lista está vacía.
     */
    public abstract Object first();
    
    /**
     * Retorna el último objeto de la lista.
     * @return Retorna el último elemento de la lista, <code>null</code> si la 
     * lista está vacía.
     */
    public abstract Object last();
    
    /**
     * Retorna la capacidad actual de la lista
     * @return Retorna un entero que indica la capacidad actual de la lista.
     */
    public abstract int getCapacity();
    
    /**
     * Método que retorna un indicador booleano de si la lista está llena o no.
     * @return Retorna un <code>true</code> si la lista está llena o un 
     * <code>false</code> en caso contrario.
     */
    public abstract boolean isFull();
    
    /**
     * Elimina todo el contenido de la lista.
     */
    public abstract void clear();
    
    /**
     * Busca la posición de un objeto específico de la lista. Si lo encuentra 
     * retorna su posición, caso contrario retorna -1. Le recomendamos 
     * sobreescriba el método <code>equals()</code> del objeto para la 
     * comparación de igualdad entre dos objetos.
     * @param E Objeto a comparar.
     * @return Retorna él índice dentro de la lista si se encuentra, caso 
     * contrario retorna -1.
     */
    public abstract int indexOf(Object E);
    
    /**
     * Retorna el objeto que está en la posición especificada como parámetro 
     * del método, si ésta es válida, caso contrario retorna <code>null</code>.
     * @param p Posición del elemento en la lista.
     * @return Retorna el objeto si la posición es válida, <code>null</code> 
     * en el caso contrario.
     */
    public abstract Object get(int p);    
            
    /**
     * Retorna un booleano que identifica si la lista está vacía o no.
     * @return Retorna <code>true</code> si la lista está vacía, 
     * <code>false</code> en caso contrario.
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Método accesor para el tamaño de una lista.
     * @return Retorna un entero con el tamaño de la lista.
     */
    public int getSize() {
        return size;
    }

}
