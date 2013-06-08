package edu.unitec.adt;

import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author EdilsonFernando
 */
public class SortAlgorithms {

    public static ADTList quicksort(ADTList input, Comparator comp) {
        if (input.getSize() <= 1) {
            return input;
        }
        
        //---------------------------------------------------------------------
        int middle = new Random().nextInt(input.getSize());
        //---------------------------------------------------------------------
        
        Object pivot = input.get(middle);

        ADTList less = new SLList();
        ADTList greater = new SLList();

        for (int i = 0; i < input.getSize(); i++) {
            if (comp.compare(input.get(i), pivot) == 0 || comp.compare(input.get(i), pivot) == -1) {
                if (i != middle) {
                    less.insert(input.get(i), less.getSize());
                }
            } else {
                greater.insert(input.get(i), greater.getSize());
            }
        }

        
        return concatenate(quicksort(less, comp), pivot, quicksort(greater, comp));
    }

    private static ADTList concatenate(ADTList less, Object pivot, ADTList greater) {
        ADTList neo = new SLList();
        for (int i = 0; i < less.getSize(); i++) {
            neo.insert(less.get(i), neo.getSize());
        }

        neo.insert(pivot, neo.getSize());

        for (int i = 0; i < greater.getSize(); i++) {
            neo.insert(greater.get(i), neo.getSize());
        }

        return neo;
    }
    
    public static int binarySearch(ADTList list, Object key, Comparator comp) {
        if (list.isEmpty()) {
            return -1;
        }
        
        int min = 0, max = list.getSize() - 1;
        
        while (min <= max) {
            int middle = (max + min) / 2;
            
            if (comp.compare(list.get(middle), key) == 0) {
                return middle;
            } else if (comp.compare(list.get(middle), key) == -1) {
                min = middle + 1;
            } else {
                max = middle - 1;
            }
        }
        
        return -1;
    }
}
