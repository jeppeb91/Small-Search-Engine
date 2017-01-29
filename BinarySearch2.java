/******************************************************************************
 *  Compilation:  javac BinarySearch.java
 *  Execution:    java BinarySearch wordlist.txt < input.txt
 *  Data files:   http://www.cs.princeton.edu/introcs/43sort/emails.txt
 *                http://www.cs.princeton.edu/introcs/43sort/whitelist.txt
 *
 *  Read in an alphabetical list of words from the given file.
 *  Then prompt the user to enter words. The program reports which
 *  words are *not* in the wordlist.
 *
 *  % java BinarySearch whitelist.txt < emails.html
 *  marvin@spam
 *  mallory@spam
 *  eve@airport
 *
 ******************************************************************************/

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BinarySearch2 {

    // return the index of the key in the sorted array a[]; -1 if not found
    public static int search(String key, ArrayList<TinySearchEngine.Node> a) {
        return search(key, a, 0, a.size());
    }
    public static int search(String key, ArrayList<TinySearchEngine.Node> a, int lo, int hi) {
        // possible key indices in [lo, hi)
        if (hi <= lo) return -1;
        int mid = lo + (hi - lo) / 2;
        int cmp = a.get(mid).word.word.compareTo(key);
        if      (cmp > 0) return search(key, a, lo, mid);
        else if (cmp < 0) return search(key, a, mid+1, hi);
        else              return mid;
    }
}
