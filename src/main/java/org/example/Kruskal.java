package org.example;

import java.util.*;

//Алгоритм Краскала нам нужен для сверки результата у алгоритма Прима, если заранее ответ неизвестен
public class Kruskal {
    private final int[][] matrix;
    public int tree_value = -1;

    private ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
    private HashSet<Integer> linkedVertexes = new HashSet<>();
    private HashMap<Integer, ArrayList<Integer>> vertexGroup = new HashMap<>();
    private ArrayList<ArrayList<Integer>> minTree = new ArrayList<>();


    public Kruskal(int[][] m){
        matrix = m;
        CollectAllEdges();
        edges.sort(new EdgesComparator());
        KruskalAlgorithm();
        TreeValue();
    }

    private void CollectAllEdges(){
        for(int i = 0; i < matrix.length; i++){
            for(int j = i+1; j < matrix.length; j++){
                if(matrix[i][j] != 0)
                    edges.add(new ArrayList<>(Arrays.asList(matrix[i][j], i, j)));
            }
        }
    }

    private void KruskalAlgorithm(){
        for (ArrayList<Integer> edge : edges){
            if (!linkedVertexes.contains(edge.get(1)) || !linkedVertexes.contains(edge.get(2))){
                if (!linkedVertexes.contains(edge.get(1)) && !linkedVertexes.contains(edge.get(2))){
                    vertexGroup.put(edge.get(1), new ArrayList<>(Arrays.asList(edge.get(1), edge.get(2))));
                    vertexGroup.put(edge.get(2), vertexGroup.get(edge.get(1)));
                }
                else{
                    if(!vertexGroup.containsKey(edge.get(1))){
                        vertexGroup.get(edge.get(2)).add(edge.get(1));
                        vertexGroup.put(edge.get(1), vertexGroup.get(edge.get(2)));
                    }
                    else{
                        vertexGroup.get(edge.get(1)).add(edge.get(2));
                        vertexGroup.put(edge.get(2), vertexGroup.get(edge.get(1)));
                    }
                }
                minTree.add(edge);
                linkedVertexes.add(edge.get(1));
                linkedVertexes.add(edge.get(2));
            }
        }
        for(ArrayList<Integer> edge : edges){
            if(vertexGroup.get(edge.get(1)).contains(edge.get(1)) && !vertexGroup.get(edge.get(2)).contains(edge.get(1))){
                minTree.add(edge);
                ArrayList<Integer> temp = vertexGroup.get(edge.get(1));
                vertexGroup.get(edge.get(1)).addAll(vertexGroup.get(edge.get(2)));
                vertexGroup.get(edge.get(2)).addAll(temp);
            }
        }
    }

    private void TreeValue(){
        int result = 0;
        for(ArrayList<Integer> edge : minTree){
            result += edge.get(0);
        }
        tree_value = result;
    }
}