
package edu.unitec.adt;

import sun.awt.datatransfer.DataTransferer;

/**
 *
 * @author EdilsonFernando
 */
public class ListSet extends ADTSet {
    
    private ADTList elements;
    
    public ListSet() {
        elements = new SLList();
    }

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

    @Override
    public boolean contains(Object E) {
        if (elements.isEmpty()) {
            return false;
        }
        
        if (SortAlgorithms.binarySearch(elements, E, new DataNodeComparator()) != -1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeElement(Object E) {
        if (elements.isEmpty()) {
            return false;
        }
        
        if (elements.remove(SortAlgorithms.binarySearch(elements, E, new DataNodeComparator())) != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public void print() {
        for (int i = 0; i < elements.getSize(); i++) {
            System.out.printf("%4s", elements.get(i).toString());
        }
        System.out.println();
    }
    
}
