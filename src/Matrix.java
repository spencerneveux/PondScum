import java.util.Scanner;
public class Matrix {
    private double[][] matrixA;
    private double[] matrixB;
    private int numEquations;

    public Matrix(double[][] matrixA, double[] matrixB) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        numEquations = matrixB.length;
    }

    //Gaussian
    public void solve() {
        for (int i = 0; i < numEquations; i++) {
            //Find pivot
            double pivot = matrixA[i][i];

            //Normalize the row
            for (int j = 0; j < numEquations; j++) {
                matrixA[i][j] = matrixA[i][j]/pivot;
            }

            //New column value
            matrixB[i] = matrixB[i]/pivot;

            for (int k = 0; k < numEquations; k++) {
                if (k != i)
                {
                    double factor = matrixA[k][i];

                    for (int z = 0; z < numEquations; z++) {
                        matrixA[k][z] = matrixA[k][z] - (factor * matrixA[i][z]);
                    }

                    //New column Value
                    matrixB[k] = matrixB[k] - (factor * matrixB[i]);
                }
            }
        }
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < numEquations; i++) {
            result += "x" + (i+1) + " = " + matrixB[i] + "\n";
        }
        return result;
    }
}
