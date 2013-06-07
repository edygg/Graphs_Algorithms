package edu.unitec.adt;

import java.io.Serializable;

/**
 * Clase padre abtracta de los árboles. Implementa los métodos necesarios para
 * operar datos específicos de un árbol. Téngase en cuenta que los recorridos
 * de los árboles se definen en las implementaciones específicas, esto es para
 * mayor facilidad para algunas de éstas, también se pueden usar los métodos 
 * de este ADT para operar dichos recorridos. Implementa Comparable para
 * comprar dos árboles si es que la implementación los necesita ordenados y
 * Serializable para ser guardada en archivos binarios.
 * @see Journeys
 * @author EdilsonFernando
 */
public abstract class ADTTree implements Comparable<ADTTree>, Serializable {
    /**
     * Método que retorna el árbol padre del árbol en cuestión.
     * @return Una referencia al padre del árbol.
     */
    public abstract ADTTree father();
    
    /**
     * Método que retorna el árbol hijo más a la izquierda de dicho árbol.
     * @return El hijo más a la izquierda del árbol.
     */
    public abstract ADTTree leftestSon();
    
    /**
     * Método que retorna el árbol que es hermano de la derecha del árbol 
     * en cuestión.
     * @return El hermano de la derecha del árbol.
     */
    public abstract ADTTree rightBrother();
    
    /**
     * Método que retorna la raíz del árbol, si el árbol es la raíz, retorna
     * una referencia de si mismo.
     * @return Una referencia con la raíz del árbol.
     */
    public abstract ADTTree root();
    
    /**
     * Limpia el árbol.
     */
    public abstract void clear();
    
    /**
     * Método que recorre el nodo y sus hijos para luego procesarlos con lo
     * especificado en el TreeProcessor.
     * @param j Recorrido que se usará.
     * @param p Contienen la información de cómo se procesa cada nodo.
     * @see Journeys
     * @see TreeProcessor
     */
    public abstract void traverse(Journeys j, TreeProcessor p);
        
}
