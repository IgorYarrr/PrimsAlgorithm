package org.example.tests;

import org.example.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

@DisplayName("Создание случайных графов")
public class RandomGraphTest {
    RandomGraph randomGraph;

    @RepeatedTest(value = 5)
    @DisplayName("Случайная матрица")
    public void FinMinTreeWithRandomMatrixTest(){
        randomGraph = new RandomGraph();
        Steps.CheckMatrixStep(randomGraph.getMatrix());

        Prima prima = new Prima(randomGraph.getMatrix());
        Steps.PrimaStep(prima.tree_value);

        Visualization viz = new Visualization(randomGraph.getMatrix(), prima.min_node);
        Steps.GraphVisualization(viz.Guid);
        Steps.GraphVisualizationSolved(viz.GHuid);

        Kruskal kruskal = new Kruskal(randomGraph.getMatrix());
        Steps.KruskalStep(kruskal.tree_value);

        Steps.ResultStep(kruskal.tree_value, prima.tree_value);
    }
}
