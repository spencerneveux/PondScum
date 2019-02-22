
public class Main {
    public static void main(String[] args) {
        PondScum pondScum = new PondScum("src/");
        //Determine configuration of pond
        pondScum.getPondConfiguration();
        //Create grid according to configuration
        String[][] grid = pondScum.createGrid();
        //Get the equations
        pondScum.getEquationValues(grid);
        //Create matrix A from values
        double[][] matrixA = pondScum.createMatrixA();
        //Create matrix B from values
        double[] matrixB = pondScum.createMatrixB();
        //Create Matrix to hold results
        Matrix solution = new Matrix(matrixA, matrixB);
        solution.solve();
        System.out.println(solution.toString());
        pondScum.export(grid, solution.getResults());
    }
}
