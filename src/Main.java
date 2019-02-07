//import Jama.Matrix;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        PondScum pondScum = new PondScum("src/example2.txt");

        //Determine configuration of pond
        pondScum.getPondConfiguration();
        //Create grid according to configuration
        String[][] grid = pondScum.createGrid();
        pondScum.getEquationValues(grid);
        double[][] matrixA = pondScum.createMatrixA();
        double[] matrixB = pondScum.createMatrixB();
        int numEquations = pondScum.getNumEquations();


        Matrix solution = new Matrix(matrixA, matrixB);
        solution.solve();
        System.out.println(solution.toString());

    }
}
