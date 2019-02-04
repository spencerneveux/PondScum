import Jama.Matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class PondScum {
    private File file;
    private int numRows;
    private int numColumns;
    private ArrayList<String> equationValues;
    private ArrayList<Double> sumList;


    public PondScum(String filePath) {
        file = new File(filePath);
        numRows = 0;
        numColumns = 0;
        equationValues = new ArrayList<>();
        sumList = new ArrayList<>();
    }

    /**
     * Method to determine number of rows/columns
     */
    public void getPondConfiguration() {
        try(Scanner input = new Scanner(file)) {
            int numRows = 0;
            while (input.hasNextLine()) {
                //If another line then add to num Rows
                ++numRows;
                setNumRows(numRows);
                //Seperate values to determine num columns
                String line = input.nextLine();
                String[] stringValues = line.split(",");
                setNumColumns(stringValues.length);
            }
            System.out.println("Number of Rows = " + numRows + " Number of Columns = " + numColumns);
        }catch (FileNotFoundException e) {
            e.getMessage();
        }
    }

    /**
     * Method to create grid layout to represent pond
     */
    public String[][] createGrid() {
        //Create grid to be returned
        String[][] grid = new String[numRows][numColumns];
        //Scan through text file
        try(Scanner input = new Scanner(file)){
            int rowCount = 0;
            while(input.hasNextLine()) {
                //Read each line and separate values
                String line = input.nextLine();
                String[] stringValues = line.split(",");

                //Find variable height ponds
                for (int i = 0; i < stringValues.length; i++) {
                    if(stringValues[i].charAt(0) == '!')
                        stringValues[i] = "!";
                }

                //Add values to grid in double form
                for (int i = 0; i < stringValues.length; i++) {
                    grid[rowCount][i] = stringValues[i];
                }
                rowCount++;
            }
            //Print out grid
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    System.out.printf("%15s", grid[i][j]);
                }
                System.out.println();
            }
//            System.out.println(Arrays.deepToString(grid));
        }catch (FileNotFoundException ex) {
            ex.getMessage();
        }
        return grid;
    }

    /**
     * Method to run through grid and find equation values
     * @param grid 2D array representing pond configuration
     */
    public void getEquationValues(String[][] grid) {
        //Run through 2D array
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                //if value in grid = !
                if(grid[i][j].equals("!")) {
                    //North
                    equationValues.add(grid[i-1][j]);
                    //South
                    equationValues.add(grid[i+1][j]);
                    //East
                    equationValues.add(grid[i][j-1]);
                    //West
                    equationValues.add(grid[i][j+1]);
                }
            }
        }
        System.out.println("Number of equations = " + equationValues.size() / 4 + "\n" + equationValues);
    }

    /**
     * Method to create equations from equation values
     */
    public void getSums() {
        int count = 1;
        double sum = 0.0;
        int maxVariables = 4;
        for(String value : equationValues) {
            System.out.println(count);
            if (!value.equals("!")) {
                sum += Double.parseDouble(value);
            }
            if (count / maxVariables == 1) {
                sumList.add(sum);
                sum = 0;
                count = 0;
            }
            count++;
        }
        System.out.println(sumList);
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }
}
