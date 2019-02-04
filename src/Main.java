import Jama.Matrix;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        PondScum pondScum = new PondScum("src/example.txt");

        //Determine configuration of pond
        pondScum.getPondConfiguration();
        //Create grid according to configuration
        String[][] grid = pondScum.createGrid();
        pondScum.getEquationValues(grid);
        double[][] matrixA = pondScum.createMatrixA();
        double[] matrixB = pondScum.createMatrixB();

        Matrix A = new Matrix(matrixA);
        Matrix B = new Matrix(matrixB, 2);
        Matrix x = A.solve(B);
        System.out.println("The solution is: " + Arrays.deepToString(x.getArray()));

//        //How to solve system of linear equations using matrix
//        double[][] lArray = {{4, -1} , {-1, 4}};
//        double[] rArray = {602, 1303};
//        Matrix A = new Matrix(lArray);
//        Matrix B = new Matrix(rArray, 2);
//        Matrix x = A.solve(B);
//        System.out.println(Arrays.deepToString(x.getArray()));
    }
}
