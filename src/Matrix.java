public class Matrix {
    private Fraction[][] fractionMatrixA;
    private Fraction[] fractionMatrixB;
    private double[][] matrixA;
    private double[] matrixB;
    private int numEquations;

    public Matrix(double[][] matrixA, double[] matrixB) {
//        this.matrixA = matrixA;
//        this.matrixB = matrixB;
        numEquations = matrixB.length;
        fractionMatrixA = new Fraction[numEquations][numEquations];
        fractionMatrixB = new Fraction[numEquations];
        populateMatrixA(matrixA);
        populateMatrixB(matrixB);
    }

    //Gaussian
    public void solve() {
        for (int i = 0; i < numEquations; i++) {
            //Find pivot
//            double pivot = matrixA[i][i]; //initial = 4
            Fraction pivot = fractionMatrixA[i][i];

            //Normalize the row
            for (int j = 0; j < numEquations; j++) {
                fractionMatrixA[i][j] = fractionMatrixA[i][j].divide(pivot);
//                matrixA[i][j] = matrixA[i][j]/pivot;
            }

            //Set column to current value divided by pivot
            fractionMatrixB[i] = fractionMatrixB[i].divide(pivot);
//            matrixB[i] = matrixB[i]/pivot;

            //Find
            for (int k = 0; k < numEquations; k++) {
                if (k != i)
                {
//                    double factor = matrixA[k][i];
                    Fraction factor = fractionMatrixA[k][i];
                    for (int z = 0; z < numEquations; z++) {
                        fractionMatrixA[k][z] = fractionMatrixA[k][z].subtract(factor.multiply(fractionMatrixA[i][z]));
//                        matrixA[k][z] = matrixA[k][z] - (factor * matrixA[i][z]);
                    }

                    //New column Value
                    fractionMatrixB[k] = fractionMatrixB[k].subtract(factor.multiply(fractionMatrixB[i]));
//                    matrixB[k] = matrixB[k] - (factor * matrixB[i]);
                }
            }
        }
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < fractionMatrixB.length; i++) {
            result += "x" + (i+1) + " = " + fractionMatrixB[i].toString() + "\n";
        }
        return result;
    }

    public void populateMatrixA(double[][] matrixA) {
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                fractionMatrixA[i][j] = new Fraction((int)matrixA[i][j]);
            }
        }
    }

    public void populateMatrixB(double[] matrixB) {
        for (int i = 0; i < matrixB.length; i++) {
            fractionMatrixB[i] = new Fraction((int)matrixB[i]);
        }
    }
}
