
package edu.unitec.adt;


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

    @Override
    public ADTSet substraction(ADTSet set) {
        if (!(set instanceof ListSet)){
            return null;
        }
        
        ListSet tmp = (ListSet) set;
        
        for (int i = 0; i < tmp.elements.getSize(); i++) {
            this.removeElement(tmp.elements.get(i));
        }
        
        return this;
    }

    @Override
    public ADTSet union(ADTSet set) {
        if (!(set instanceof ListSet)) {
            return null;
        }
        
        for (int i = 0; i < ((ListSet) set).elements.getSize(); i++) {
            this.addElement(((ListSet) set).elements.get(i));
        }
        
        return this;
    }

    @Override
    public ADTSet intersection(ADTSet set) {
        if (!(set instanceof ListSet)) {
            return null;
        }
        
        ADTSet inter = new ListSet();
        
        for (int i = 0; i < ((ListSet) set).elements.getSize(); i++) {
            if (this.contains(((ListSet) set).elements.get(i))) {
              inter.addElement(((ListSet) set).elements.get(i));
            }
        }
        
        return inter;
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }
    
    public void print() {
        for (int i = 0; i < elements.getSize(); i++) {
            System.out.printf("%4s", elements.get(i).toString());
        }
        System.out.println();
    }
    
    public ADTList toList() {
        return elements;
    }
}
