package org.example;

import java.util.ArrayList;

public class EdgesComparator implements java.util.Comparator<ArrayList<Integer>>{
    @Override
    public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
        return o1.get(0) - o2.get(0);
    }
}
