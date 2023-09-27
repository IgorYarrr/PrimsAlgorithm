package org.example;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.qameta.allure.Allure.addAttachment;
import static org.junit.jupiter.api.Assertions.*;

public class Steps {
    @Step("Проверка корректности матрицы")
    public static void CheckMatrixStep(int[][] matrix){
        assertTrue(RandomGraph.CheckCorrectMatrixCreationStep(matrix));
        String strM = Utils.MatrixToString(matrix);
        addAttachment("Матрица", "text/plain", strM);
    }
    @Step("Решение задачи нахождения минимального остова алгоритмом Прима - {prima}")
    public static void PrimaStep(int prima){
        assertNotEquals(prima, -1);
        String resStr = Integer.toString(prima);
        addAttachment("Решение алгоритмом Прима", "text/plain", resStr);
    }
    @Step("Визуализация графа по матрице")
    public static void GraphVisualization(String uid){
        try {
            getBytes(uid+".png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Step("Визуализация решенной задачи нахождения минимального остова по матрице")
    public static void GraphVisualizationSolved(String uid){
        try {
            getBytes(uid+".png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Step("Решение задачи нахождения минимального остова алгоритмом Краскала - {kruskal}")
    public static void KruskalStep(int kruskal){
        assertNotEquals(kruskal, -1);
        String resStr = Integer.toString(kruskal);
        addAttachment("Решение алгоритмом Краскала", "text/plain", resStr);
    }

    @Step("Проверка результата алгоритма Прима. Ожидаемый - {expected}, Фактический - {actual}")
    public static void ResultStep(int expected, int actual){
        assertEquals(expected, actual);
    }

    @Step("Проверка результата алгоритма Прима и сохранение матрицы. Ожидаемый - {kruskal}, Фактический - {prima}")
    public static void ResultWithSaveStep(int[][] matrix, int kruskal, int prima){
        if(kruskal == prima){
            Utils.SaveCorrectMatrix(matrix, prima);
        }
        else{
            Utils.SaveIncorrectMatrix(matrix, kruskal, prima);
        }
        assertEquals(kruskal, prima);
    }

    @Step("")

    @Attachment
    public static byte[] getBytes(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/resources/GraphPictures", resourceName));
    }
}
