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
import java.util.LinkedList;

public class BinarySearch {
    public static int indexOf(ArrayList<TinySearchEngine.Node> l, String key) {
        int lo = 0;
        if (l.size()==0)return 0;
        int hi = l.size()-1;
        while (lo <= hi) {
            //System.out.println(hi + " " + lo);
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (hi==lo&&l.size()>1) {
                if(l.get(hi).word.word.compareTo(key)<=0) return hi+1;
                if(l.get(lo).word.word.compareTo(key)>0) return lo;
                else return hi+1;
            }
            if (hi - lo == 1) {
                if(l.get(hi).word.word.compareTo(key)<=0) return hi+1;
                if(l.get(hi).word.word.compareTo(key)>0) return hi;
                else return lo;
            }
            if      (l.get(mid).word.word.compareTo(key)>=0) hi = mid;
            else if (l.get(mid).word.word.compareTo(key)<=0) lo = mid;
            //else return mid;
        }
        return 0;
    }
}