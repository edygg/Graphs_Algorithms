
package edu.unitec.adt;

import java.util.Comparator;
import java.util.Random;

/**
 * Clase que contiene algoritmos para ordenamiento y búsqueda en listas.
 * @see ADTList
 * @author EdilsonFernando
 */
public class SortAlgorithms {

    /**
     * Algoritmo de ordenamiento rápido para listas.
     * @param input Lista con los elementos desordenados.
     * @param comp Criterio de comparación entre los elementos
     * @return Retorna una lista ordenada.
     */
    public static ADTList quicksort(ADTList input, Comparator comp) {
        //Retorna la lista si posee solamente uno o cero elementos
        if (input.getSize() <= 1) {
            return input;
        }
        
        //---------------------------------------------------------------------
        int middle = new Random().nextInt(input.getSize());
        //---------------------------------------------------------------------
        
        //Pivote 
        Object pivot = input.get(middle);

        //Lista con los menores
        ADTList less = new SLList();
        //Lista con los mayores
        ADTList greater = new SLList();

        //Llenando las listas con los elementos mayores y menores al pivote
        for (int i = 0; i < input.getSize(); i++) {
            if (comp.compare(input.get(i), pivot) == 0 || comp.compare(input.get(i), pivot) < 0) {
                if (i != middle) {
                    less.insert(input.get(i), less.getSize());
                }
            } else {
                greater.insert(input.get(i), greater.getSize());
            }
        }

        //Concatena las listas a las cuales les aplica quicksort de nuevo
        return concatenate(quicksort(less, comp), pivot, quicksort(greater, comp));
    }

    /**
     * Concatena dos listas con su respectivo pivote;
     * @param less Lista con los elementos menores que el pivote.
     * @param pivot El pivote.
     * @param greater Lista con los elementos mayores que el pivote.
     * @return Retorna una lista con los elementos concatenados.
     */
    private static ADTList concatenate(ADTList less, Object pivot, ADTList greater) {
        //Nueva lista con los elementos concatenados 
        ADTList neo = new SLList();
        //Inserta los elementos menores primero en la lista
        for (int i = 0; i < less.getSize(); i++) {
            neo.insert(less.get(i), neo.getSize());
        }

        //Inserta el pivote
        neo.insert(pivot, neo.getSize());

        //Agrega los elementos mayores que el pivote a la lista concatenada
        for (int i = 0; i < greater.getSize(); i++) {
            neo.insert(greater.get(i), neo.getSize());
        }

        //Retorna la lista concatenada
        return neo;
    }
    
    /**
     * Búsqueda binaria. Como requisito la lista debe de estar ordenada.
     * @param list Lista en la que se buscará el elemento.
     * @param key El objeto a buscar.
     * @param comp Comparador para los objetos.
     * @return Retorna el índice donde se encuentra el elemento buscado dentro 
     * de la lista. Si este no se encuentra retorna -1.
     */
    public static int binarySearch(ADTList list, Object key, Comparator comp) {
        //Si la lista está vacía no hay elementos para buscar
        if (list.isEmpty()) {
            return -1;
        }
        
        //Inicializando las variables necesarias
        int min = 0, max = list.getSize() - 1;
        
        //Buscando el elemento dentro de la lista
        while (min <= max) {
            //Elemento medio con el que se compara el elemento a buscar
            int middle = (max + min) / 2;
            
            //Si el elemento es igual fue encontrado
            if (comp.compare(list.get(middle), key) == 0) {
                return middle;
            //Si es menor entonces se modifica el mínimo
            } else if (comp.compare(list.get(middle), key) < 0) {
                min = middle + 1;
            //Si es mayor entonces se modifica el máximo
            } else {
                max = middle - 1;
            }
        }
        //Si no fue encontrado retorna -1
        return -1;
    }
}
