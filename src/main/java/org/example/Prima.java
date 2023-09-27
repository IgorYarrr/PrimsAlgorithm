package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
public class Prima {
    private final int[][] matrix;
    private int n;
    private static final int max = Integer.MAX_VALUE;

    private boolean[] used;
    public int[] min_cost;
    public int[] min_node;
    public int tree_value = -1;

    public Prima( int[][] m){
        matrix = m;
        n = matrix.length;
        used = new boolean[n];
        min_cost = new int[n];
        min_node = new int[n];
        for(int i = 0; i < n; i++){
            used[i] = false; min_cost[i] = max; min_node[i] = -1;
        }
        PrimaAlgorithm();
        TreeValue();
    }
    private void PrimaAlgorithm(){
        int r = (int)(Math.random() * n );
        ArrayList<ArrayList<Integer>> queue = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> start_pair = new ArrayList<Integer>();
        start_pair.add(0); start_pair.add(r);
        min_cost[r] = 0;
        queue.add(start_pair);
        while (!queue.isEmpty()){
            int v = queue.remove(FindMinValueInQ(queue)).get(1);
            used[v] = true;
            for(int i = 0; i < n; ++i){
                int destination = i;
                int cost = matrix[v][i];
                if(cost < min_cost[destination] && cost != 0 && !used[destination]){
                    min_cost[destination] = cost;
                    min_node[destination] = v;
                    ArrayList<Integer> new_pair = new ArrayList<>();
                    new_pair.add(cost); new_pair.add(destination);
                    queue.add(new_pair);
                }
            }
        }
    }

    private int FindMinValueInQ(ArrayList<ArrayList<Integer>> queue){
        int minPos = 0;
        for(int i = 1; i < queue.size(); i++){
            if (queue.get(i).get(0) < queue.get(minPos).get(0))
                minPos = i;
        }
        return minPos;
    }
    private void TreeValue(){
        int result = 0;
        for(int i = 0; i < n; i++){
            result += min_cost[i];
        }
        tree_value = result;
    }
}
