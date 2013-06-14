
package edu.unitec.adt;

/**
 * Procesador de árboles
 * @author EdilsonFernando
 */
public interface TreeProcessor {
    
    /**
     * Las clases que implementen este método tendrán las instrucciones de 
     * como procesar cada nodo de un árbol específico.
     * @param obj Árbol a procesar.
     */
    public void process(ADTTree obj);
}
