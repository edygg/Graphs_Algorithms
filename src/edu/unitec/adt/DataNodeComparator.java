package edu.unitec.adt;

import java.util.Comparator;

/**
 *
 * @author EdilsonFernando
 */
public class DataNodeComparator implements Comparator<Object> {

    @Override
    public int compare(Object t, Object t1) {
        if (t instanceof Comparable && t1 instanceof Comparable) {
            Comparable tmp1 = (Comparable) t;
            Comparable tmp2 = (Comparable) t1;
            
            return tmp1.compareTo(tmp2);
        } else {
            return 0;
        }
        
    }
}
