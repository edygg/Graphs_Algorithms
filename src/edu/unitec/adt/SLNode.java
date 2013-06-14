
package edu.unitec.adt;

import java.io.Serializable;

/**
 * Estructura nodo que encapsula los datos y mantiene un apuntador al siguiente
 * elemento nodo. Implementa <code>Serializable</code> para ser almacenado en 
 * archivos binarios.
 * @author EdilsonFernando
 */
public class SLNode implements Serializable {
    /**
     * Objeto que contiene los datos.
     */
    private Object data;
    
    /**
     * Referencia al siguiente nodo.
     */
    private SLNode next;
    
    /**
     * Constructor por defecto. Inicializa los datos y la referencia al
     * siguiente nodo en <code>null</code>.
     */
    public SLNode() {
        this(null);
    }
    
    /**
     * Constructor que recibe una referencia a un objeto para ser almacenado
     * en la estructura, inicializa la referencia al siguiente nodo en 
     * <code>null</code>.
     * @param data Dato que será almacenado dentro de la estructura nodo.
     */
    public SLNode(Object data) {
        this.data = data;
        this.next = null;
    }
    
    /**
     * Método mutador para los datos del nodo.
     * @param data Datos que se almacenarán en el nodo.
     */
    public void setData(Object data) {
        this.data = data;
    }
    
    /**
     * Método accesor para los datos del nodo.
     * @return Retornan el objeto que almacena el nodo.
     */
    public Object getData() {
        return this.data;
    }
    
    /**
     * Mutador para la referencia al siguiente nodo.
     * @param n Referencia del siguiente nodo.
     */
    public void setNext(SLNode n) {
        this.next = n;
    }
    
    /**
     * Método accesor para la referencia al siguiente nodo.
     * @return Retorna la referencia del siguiente nodo.
     */
    public SLNode getNext() {
        return this.next;
    }
}
