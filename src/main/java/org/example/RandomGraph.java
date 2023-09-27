package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class RandomGraph {
    private final static int ADDITIONAL_VERTEXES = 5;
    private final static int MIN_VERTEXES = 5;
    private final static int MAX_VALUE = 10;
    private final static double FILL_CHANCE = 0.5; //От 0 до 100 процентов
    private int n;
    private int[][] matrix;

    public RandomGraph(){
        n = (int)(Math.random() * ADDITIONAL_VERTEXES + MIN_VERTEXES); // к минимальному количеству вершин добавляем "добавочные" вершины
        CreateGraph();
    }
    public RandomGraph(int n){
        this.n = n;
        CreateGraph();
    }
    public int[][] getMatrix() {
        return matrix;
    }

    private void CreateGraph(){
        matrix = new int[n][n];
        CreateGraphBase();
        MatrixReflection();
        FillGraphLinks();
        MatrixReflection();
    }

    //Формируем костяк графа
    private void CreateGraphBase(){
        boolean[] visited = new boolean[n];
        int from_vertex = (int)(Math.random() * n);
        for(int i = 1; i < n ; i++){
            visited[from_vertex] = true;
            int next_vertex;
            do{
                next_vertex = (int)(Math.random() * n);
            } while(visited[next_vertex]);
            int rand_value = (int)(Math.random() * MAX_VALUE + 1);
            matrix[from_vertex][next_vertex] = rand_value;
            from_vertex = next_vertex;
        }
    }

    //Наращиваем граф дополнительными связями между вершинами
    private void FillGraphLinks(){
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if (matrix[i][j] == 0 && Math.random() < FILL_CHANCE)
                    matrix[i][j] = (int)(Math.random() * MAX_VALUE + 1);
            }
        }
    }

    private void MatrixReflection(){
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(matrix[i][j] == 0)
                    matrix[i][j] = matrix[j][i];
                else if(matrix[j][i] == 0)
                    matrix[j][i] = matrix[i][j];
            }
        }
    }

    public static boolean CheckCorrectMatrixCreationStep(int[][] matrix){
        int n = matrix.length;
        boolean isCorrect = true;
        for(int i = 0; i < n; i++){
            boolean isEmptyRow = true;
            for(int j = 0; j < n; j++){
                if(i==j && matrix[i][j] != 0){
                    isCorrect = false;
                    break;
                }
                if(matrix[i][j] != 0){
                    isEmptyRow = false;
                }
                if(matrix[i][j] != matrix[j][i]){
                    isCorrect = false;
                    break;
                }
            }
            if(isEmptyRow){
                isCorrect = false;
                break;
            }
        }
        return isCorrect;
    }
}
