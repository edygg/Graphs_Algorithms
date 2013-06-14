
package edu.unitec.adt;

/**
 * Implementación de ADTSet que utiliza listas ordenadas para representa un 
 * conjunto.
 * @author EdilsonFernando
 */
public class ListSet extends ADTSet {

    /**
     * Lista ordenada de elementos.
     */
    private ADTList elements;

    /**
     * Constructor por defecto de una ListSet.s
     */
    public ListSet() {
        elements = new SLList();
    }

    /**
     * Implementación de método <code>addElement(E)</code> de la clase ADTSet
     * agrega un elemento a la lista para luego ordenarla. No se aceptan
     * duplicados.
     * @param E Elemento a insertar en el conjunto
     * @return retorna <code>true</code> si el elemento se agregó al conjunto
     * con éxito, <code>false</code> en caso contrario.
     * @see ADTSet#addElement(java.lang.Object) 
     */
    @Override
    public boolean addElement(Object E) {
        if (elements.isEmpty()) {
            return elements.insert(E, elements.getSize());
        } else {
            if (SortAlgorithms.binarySearch(elements, E, new DataNodeComparator()) != -1) {
                return false;
            }

            boolean retVal = elements.insert(E, elements.getSize());
            elements = SortAlgorithms.quicksort(elements, new DataNodeComparator());

            return retVal;
        }
    }

    /**
     * Implementación de método <code>contains(E)</code> de la clase ADTSet. 
     * Verifica si un elemento pertenece al conjunto.
     * @param E Objeto a verificar si pertenece o no al conjunto
     * @return Retorna <code>true</code> si el elemento se encuentra en el
     * conjunto, <code>false</code> en caso contrario.
     */
    @Override
    public boolean contains(Object E) {
        //Si la lista está vacía no hay elementos que comparar.
        if (elements.isEmpty()) {
            return false;
        }

        //Aprovechando que la lista está ordenada es posible hacer una búsqueda
        //binaria del elemento dentro de la lista. Si se encuentra retorna true
        //caso contrario false.
        if (SortAlgorithms.binarySearch(elements, E, new DataNodeComparator()) != -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Implementación de método <code>removeElement(E)</code> de la clase ADTSet.
     * Elimina un elemento del conjunto si este pertenece a él, caso contrario
     * el conjunto no se verá afectado.
     * @param E Elemento a eliminar del conjunto.
     * @return Retorna <code>true</code> si el elemento se removió con éxito
     * del conjunto, <code>false</code> en caso contrario.
     */
    @Override
    public boolean removeElement(Object E) {
        //Si la lista está vacía no hay elementos que eleminar
        if (elements.isEmpty()) {
            return false;
        }

        //Aprovechando que la lista está ordenada se puede buscar el elemento
        //mediante una búsqueda binaria para eliminarlo de la lista
        if (elements.remove(SortAlgorithms.binarySearch(elements, E, new DataNodeComparator())) != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Implementación de método <code>subtraction(Set)</code> de la clase 
     * ADTSet. Retorna la diferencia entre conjuntos afectando al conjunto 
     * que hace el llamado de la función.
     * @param set Conjunto con los elementos a sustraer del conjunto que hace
     * el llamado
     * @return Retorna una referencia al conjunto que hizo el llamado con los
     * cambios aplicados.
     */
    @Override
    public ADTSet substraction(ADTSet set) {
        //si el conjunto que entra como parámetro no es del mismo tipo
        //retorna un null y no modifica el conjunto.
        if (!(set instanceof ListSet)) {
            return null;
        }

        //conjunto de parámetro
        ListSet tmp = (ListSet) set;

        //Eliminar los elementos
        for (int i = 0; i < tmp.elements.getSize(); i++) {
            this.removeElement(tmp.elements.get(i));
        }

        return this;
    }

    /**
     * Implementación del método <code>union(Set)</code> de la clase ADTSet. 
     * Une dos conjuntos y modifica que conjunto que llama a la función.
     * @param set Conjunto para unirlo al que hace el llamado.
     * @return Retorna una referencia al <code>this</code> con las
     * modificaciones pertinentes.
     */
    @Override
    public ADTSet union(ADTSet set) {
        //Verifica que el parámetro sera una implementación de ListSet
        if (!(set instanceof ListSet)) {
            return null;
        }

        //Agrega los elementos al conjunto
        for (int i = 0; i < ((ListSet) set).elements.getSize(); i++) {
            this.addElement(((ListSet) set).elements.get(i));
        }

        return this;
    }

    /**
     * Implementación del método <code>intersection(Set)</code>. Hace una
     * intersección de dos conjuntos. Crea un nuevo conjunto para retornarlo.
     * @param set Conjunto con que se desea hacer la intersección.
     * @return Conjunto resultado de la intersección
     */
    @Override
    public ADTSet intersection(ADTSet set) {
        //Verificando que el conjunto sera un ListSet
        if (!(set instanceof ListSet)) {
            return null;
        }

        //Creando el conjunto que se retornará
        ADTSet inter = new ListSet();

        //Intersectando ambos conjuntos
        for (int i = 0; i < ((ListSet) set).elements.getSize(); i++) {
            if (this.contains(((ListSet) set).elements.get(i))) {
                inter.addElement(((ListSet) set).elements.get(i));
            }
        }

        return inter;
    }

    /**
     * Implementación del método <code>isEmpty()</code>. Retorna un booleano
     * que representa si es un conjunto vacío o no.
     * @return Retorna <code>true</code> si el conjunto es vacío, <code>false</code>
     * en caso contrario.
     */
    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * Retorna la representación en lista del conjunto.
     * @return Retorna un <code>ADTList</code> con los elementos dentro del
     * conjunto.
     */
    public ADTList toList() {
        return elements;
    }

}
