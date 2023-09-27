package org.example;

import io.qameta.allure.Step;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    RandomGraph randomGraph = new RandomGraph();


//    @Test
//    public void ExamplePrimaTest(){
//        int[][] matrix = new int[][]
//                        {{0, 2, 6, 8, 0, 0, 3, 0, 0},
//                        {2, 0, 9, 3, 0, 4, 9, 0, 0},
//                        {6, 9, 0, 7, 0, 0, 0, 0, 0},
//                        {8, 3, 7, 0, 5, 5, 0, 0, 0},
//                        {0, 0, 0, 5, 0, 0, 8, 9, 0},
//                        {0, 4, 0, 5, 0, 0, 0, 6, 4},
//                        {3, 9, 0, 0, 8, 0, 0, 0, 0},
//                        {0, 0, 0, 0, 9, 6, 0, 0, 1},
//                        {0, 0, 0, 0, 0, 4, 0, 1, 0}};
//        Prima prima = new Prima(matrix);
//        assertEquals(prima.tree_value, 28);
//    }

    @Test
    public void ExampleRandomGraphTest(){
        CheckMatrixStep(randomGraph);
//        Prima prima = new Prima(randomGraph.getMatrix());
//        Visualization viz = new Visualization(randomGraph.getMatrix());
    }
    public void CheckMatrixStep(RandomGraph randomGraph){
        assertTrue(randomGraph.CheckCorrectMatrixCreationStep());
    }
}
