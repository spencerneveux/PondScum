import Jama.Matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PondScum {
    private static int numRows = 0;
    private static int numColumns = 0;

    public static void main(String[] args) {
        //Determine configuration of pond
        getPondConfiguration();
        //Create grid according to configuration
        createGrid();

        //How to solve system of linear equations using matrix
        double[][] lArray = {{4, -1} , {-1, 4}};
        double[] rArray = {602, 1303};
        Matrix A = new Matrix(lArray);
        Matrix B = new Matrix(rArray, 2);
        Matrix x = A.solve(B);
        System.out.println(Arrays.deepToString(x.getArray()));
    }

    /**
     * Method to determine number of rows/columns
     */
    private static void getPondConfiguration() {
        try(Scanner input = new Scanner(new File("src/example.txt"))) {
            while (input.hasNextLine()) {
                //If another line then add to num Rows
                numRows++;
                //Seperate values to determine num columns
                String line = input.nextLine();
                String[] stringValues = line.split(",");
                numColumns = stringValues.length;
            }
            System.out.println("Number of Rows = " + numRows + " Number of Columns = " + numColumns);
        }catch (FileNotFoundException e) {
            e.getMessage();
        }
    }

    /**
     * Method to create grid layout to represent pond
     */
    private static double[][] createGrid() {
        //Create grid to be returned
        double[][] grid = new double[numRows][numColumns];
        //Scan through text file
        try(Scanner input = new Scanner(new File("src/example.txt"))){
            int rowCount = 0;
            while(input.hasNextLine()) {
                //Read each line and separate values
                String line = input.nextLine();
                String[] stringValues = line.split(",");

                //Find variable height ponds
                for (int i = 0; i < stringValues.length; i++) {
                    if(stringValues[i].charAt(0) == '!')
                        stringValues[i] = "1";
                }

                //Add values to grid in double form
                for (int i = 0; i < stringValues.length; i++) {
                    grid[rowCount][i] = Double.parseDouble(stringValues[i]);
                }
                rowCount++;
            }
            //Print out grid
            System.out.println(Arrays.deepToString(grid));
        }catch (FileNotFoundException ex) {
            ex.getMessage();
        }
        return grid;
    }

}
