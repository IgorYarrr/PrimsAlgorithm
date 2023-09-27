package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Utils {
    private final static String CORRECT_MATRIX_PATH = "src/test/resources/CorrectResult/CorrectResult.csv";
    private final static String INCORRECT_MATRIX_PATH = "src/test/resources/IncorrectResult/IncorrectResult.csv";
    public static void SaveCorrectMatrix(int[][] matrix, int result){
        File file = new File(CORRECT_MATRIX_PATH);
        try {
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReaderBuilder(fileReader).build();
            List<String[]> data = csvReader.readAll();
            data.add(new String[]{MatrixToCompleteString(matrix), Integer.toString(result)});

            FileWriter fileWriter = new FileWriter(file);
            CSVWriter writer = new CSVWriter(fileWriter, ',', CSVWriter.NO_QUOTE_CHARACTER);
            writer.writeAll(data);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void SaveIncorrectMatrix(int[][] matrix, int kruskal, int prima){
        File file = new File(INCORRECT_MATRIX_PATH);
        try {
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReaderBuilder(fileReader).build();
            List<String[]> data = csvReader.readAll();
            data.add(new String[]{MatrixToCompleteString(matrix), Integer.toString(kruskal), Integer.toString(prima)});

            FileWriter fileWriter = new FileWriter(file);
            CSVWriter writer = new CSVWriter(fileWriter, ',', CSVWriter.NO_QUOTE_CHARACTER);
            writer.writeAll(data);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int[][] StringToMatrix(String string){
        String[] strArray = string.split(" ");
        int n = (int)Math.sqrt(strArray.length);
        int[][] matrix = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = Integer.parseInt(strArray[i*n + j]);
            }
        }
        return matrix;
    }

    public static String MatrixToString(int[][] matrix){
        String matrixToString = "";
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j< matrix.length; j++){
                matrixToString += (Integer.toString(matrix[i][j]) + "\t");
            }
            matrixToString += "\n";
        }
        return matrixToString;
    }

    private static String MatrixToCompleteString(int[][] matrix){
        String matrixToString = "";
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j< matrix.length; j++){
                matrixToString += (Integer.toString(matrix[i][j]) + " ");
            }
        }
        return matrixToString.substring(0, matrixToString.length()-1);
    }


}
