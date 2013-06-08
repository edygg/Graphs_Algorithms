package edu.unitec.adt;

import java.util.Random;

/**
 *
 * @author EdilsonFernando
 */
public class SortAlgorithms {

    public static ADTList quicksort(ADTList input) {
        if (input.getSize() <= 1) {
            return input;
        }
        
        //---------------------------------------------------------------------
        int middle = new Random().nextInt(input.getSize());
        //---------------------------------------------------------------------
        
        Comparable pivot = input.get(middle);

        ADTList less = new SLList();
        ADTList greater = new SLList();

        for (int i = 0; i < input.getSize(); i++) {
            if (input.get(i).compareTo(pivot) == 0 || input.get(i).compareTo(pivot) == -1) {
                if (i != middle) {
                    less.insert(input.get(i), less.getSize());
                }
            } else {
                greater.insert(input.get(i), greater.getSize());
            }
        }

        
        return concatenate(quicksort(less), pivot, quicksort(greater));
    }

    private static ADTList concatenate(ADTList less, Comparable pivot, ADTList greater) {
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
}
