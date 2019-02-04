import Jama.Matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PondScum {
    private int numRows;
    private int numColumns;

    public PondScum() {
        numRows = 0;
        numColumns = 0;
    }

    /**
     * Method to determine number of rows/columns
     */
    public void getPondConfiguration() {
        try(Scanner input = new Scanner(new File("src/example2.txt"))) {
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
        try(Scanner input = new Scanner(new File("src/example2.txt"))){
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

    public void getEquations(String[][] grid) {
        ArrayList<String> equationValues = new ArrayList<>();
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
        //Iterate through equationValues
        //
        System.out.println(equationValues);
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
