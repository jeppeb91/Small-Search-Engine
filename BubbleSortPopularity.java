import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by jeppe on 2016-12-02.
 */
public class BubbleSortPopularity {
    public class Counter {
        public int count;
    }

    public static void swap(ArrayList<TinySearchEngine.properties> a, int i, int j) {
        TinySearchEngine.properties temporary = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temporary);
    }

    public static ArrayList<TinySearchEngine.properties> bubblesort(ArrayList<TinySearchEngine.properties> a, String s) {
        int r = a.size() - 2;
        boolean swapped = true;
        while (r >= 0 && swapped) {
            swapped = false;
            int before=0;
            int after=0;
            for (int i = 0; i <= r; i++) {
                if      (s.equalsIgnoreCase("popularity")){before=a.get(i).attr.document.popularity; after=a.get(i + 1).attr.document.popularity;}
                else if (s.equalsIgnoreCase("occurrence")){before=a.get(i).attr.occurrence; after=a.get(i + 1).attr.occurrence;}
                else if (s.equalsIgnoreCase("count")){before=a.get(i).count; after=a.get(i + 1).count;}
                if (before >after) {
                    swapped = true;
                    swap(a, i, i + 1);
                }
            }
            r--;
        }
        return a;
    }
}
