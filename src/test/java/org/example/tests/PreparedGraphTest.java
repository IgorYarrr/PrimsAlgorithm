package org.example.tests;


import org.example.Prima;
import org.example.Steps;
import org.example.Utils;
import org.example.Visualization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


@DisplayName("Проверка на основе заранее подготовленных данных")
public class PreparedGraphTest {

    @ParameterizedTest(name = "Граф {index} с минимальным остовом суммой - {1}")
    @CsvFileSource(resources = "../../../CorrectResult/CorrectResult.csv")
    @DisplayName("Проверка заранее заготовленных матриц")
    public void PreparedMatrixTest(String matrixStr, String resultStr){
        int[][] matrix = Utils.StringToMatrix(matrixStr);
        Steps.CheckMatrixStep(matrix);

        Prima prima = new Prima(matrix);
        Steps.PrimaStep(prima.tree_value);

        Visualization viz = new Visualization(matrix, prima.min_node);
        Steps.GraphVisualization(viz.Guid);
        Steps.GraphVisualizationSolved(viz.GHuid);

        Steps.ResultStep(Integer.parseInt(resultStr), prima.tree_value);
    }
}
