import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jeppe on 2016-12-01.
 */
public class TinySearchEngine implements TinySearchEngineBase {
    public static class Node {
        public Word word;
        public Attributes attr;

        public Node(Word word, Attributes attr) {
            this.word = word;
            this.attr = attr;
        }
    }

    ArrayList<Node> l = new ArrayList<Node>(1000);

    //Build the index
    public void insert(Word word, Attributes attr) {
        if (l.size() == 1) {
            if (l.get(0).word.word.compareTo(word.word) < 0) l.add(new Node(word, attr));
            else {
                l.add(0, new Node(word, attr));
            }
        }
        if (l.size() == 0) l.add(new Node(word, attr));
        else {
            int i = BinarySearch.indexOf(l, word.word);
            if (i >= l.size()) l.add(new Node(word, attr));
            else {
                l.add(i, new Node(word, attr));
            }
        }
    }

    //Searching
    public List<Document> search(String query) {
        //will need to split
        ArrayList<properties> props = new ArrayList<properties>();
        ArrayList<Document> docs = new ArrayList<Document>();
        ArrayList<String> strings = toParse(query);
        System.out.println("strs\n" + strings);
        //find orderby
        int orderindex = -1;
        for (int i = 0; i < strings.size(); i++) {
            if (strings.get(i).equals("orderby")) {
                orderindex = i;
                break;
            }
        }
        //adapt the lists after orderby
        ArrayList<String> orderby = new ArrayList<String>();
        if (orderindex > 0) {
            for (int i = orderindex + 1; i < strings.size(); i++) {
                orderby.add(strings.get(i));
                if (orderby.size() == 2) break;
            }
            for (int i = strings.size() - 1; i >= orderindex; i--) {
                strings.remove(i);
            }
            System.out.println(orderby);
            System.out.println("strings" + strings);
        }

        //adds all hits to l
        for (int j = 0; j < strings.size(); j++) {
            int index = BinarySearch2.search(strings.get(j), l);
            int at=0;
            if (index >= 0) {
                for (int i = index; i < l.size() && l.get(i).word.word.equals(l.get(index).word.word); i++) {
                    properties pr=new properties(l.get(i).attr, 1);
                    boolean in=false;
                    for(int k=0; k<props.size(); k++){
                        if(props.get(k).attr.document.equals(pr.attr.document)){
                            in=true;
                            at=k;
                        }

                    }
                    if(!in){
                        props.add(pr);
                    }
                    else props.get(at).count++;
                }
                for (int i = index - 1; i > 0 && l.get(i).word.word.equals(l.get(index).word.word); i--) {
                    properties pr=new properties(l.get(i).attr, 1);
                    boolean in=false;
                    for(int k=0; k<props.size(); k++){
                        if(props.get(k).attr.document.equals(pr.attr.document)){
                            in=true;
                            at=k;
                        }
                    }
                    if(!in){
                        props.add(pr);
                    }
                    else props.get(at).count++;
                }
            }
        }
        if (orderby.size() != 0) props = sortresults(props, orderby);
        for(int i=0; i<props.size(); i++) docs.add(props.get(i).attr.document);
        return docs;
    }

    public ArrayList<String> toParse(String query) {
        ArrayList<String> strings = new ArrayList<String>();
        for (String count : query.split(" ")) {
            strings.add(count);
        }
        return strings;
    }

    public ArrayList<properties> sortresults(ArrayList<properties> props, ArrayList<String> orderby) {
        System.out.println("Sorting resl");
        boolean desc = false;
        if (orderby.size() == 2) {
            if (orderby.get(1).equals("desc")) desc=true;
        }
        System.out.println("sorted");
        BubbleSortPopularity.bubblesort(props, orderby.get(0));
        if (desc) {
            for (int i = 0; i < props.size() / 2; i++) {
                swap(props, i, props.size() - 1 - i);
            }
        }
        return props;
    }
    public static void swap(ArrayList<properties> a, int i, int j) {
        properties temporary = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temporary);
    }

    public class properties{
        Attributes attr;
        int count;
        public properties(Attributes attr, int count){
            this.attr=attr; this.count=count;
        }
    }
}
