import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Mountains {

    //TODO read from input

    ArrayList<Integer> input2 = new ArrayList<>(Arrays.asList(1, 1, 3, 1, 2, 3, 5, 2, 1, 2, 2, 3, 7, 2, 3, 1, 2, 1, 3, 5, 2, 3, 1,
            2, 5, 7, 3, 1, 3, 4, 2, 5, 3, 2, 4, 4, 1, 2, 1, 3, 3, 2, 1, 2));

    ArrayList<Integer> input = new ArrayList<>(Arrays.asList(10, 7, 12, 2, 4, 7, 2, 4, 1, 2, 6, 6, 3, 2, 1, 4, 7, 2, 7,
            3, 1, 3, 11, 4, 2, 1, 5, 2, 3, 3, 3, 6, 1, 3, 9, 5, 2, 1, 2, 11, 9, 2, 3, 8, 2, 5, 1, 2, 7, 2, 4, 11, 2, 12));

    ArrayList<Integer> evens = new ArrayList<>();
    ArrayList<Integer> odds = new ArrayList<>();

    int maxHeight=0;
    int maxLoc=0;
    int totalLength=0;


    public void start() {
        setArrays();
        findMaxHeight();
        findTotalLength();
        make2DGrid();

    }

    /**
     * updates total number slopes of input array
     */
    public void findTotalLength() {
        for (int i : input) {
            totalLength += i;
        }
    }

    /**
     * calculates peak row index/height
     */
    public void findMaxHeight() {
        int currentDiff = 0;
        int currHeight = 0;
        int tempMaxHeight = 0;
        int maxIndex = 0;
        for (int i = 0; i < input.size()/2; i++) {
            currentDiff = evens.get(i) - odds.get(i);
            tempMaxHeight += evens.get(i);
            currHeight += currentDiff;

            if (tempMaxHeight >= maxHeight) {
                maxHeight = tempMaxHeight;
                maxIndex = i;
            }
            tempMaxHeight = currHeight;
        }
        calculateMaxHeightLocation(evens, odds, maxIndex);

    }

    /**
     * checks if an index is even or not
     * @return
     */
    public static boolean isEven(int n) {
        if (n%2==0) {
            return true;
        }
        return false;
    }

    /**
     * with the even and odd arrays split, calulate the column index where the peak is
     * @return peak column index
     */
    public int calculateMaxHeightLocation(ArrayList<Integer> array1, ArrayList<Integer> array2, int index) {
        int currIndex=0;
        for (int i = 0; i < index; i++) {
            maxLoc += array1.get(i) + array2.get(i);
            currIndex = i;
        }
        maxLoc += array1.get(currIndex+1);
        return maxLoc;
    }

    /**
     * gives you the total of an array if needed.
     * @param array
     * @return the total in an int
     */
    public int totalUpArray(ArrayList<Integer> array) {
        int total = 0;
        for (int i = 0; i < array.size(); i++) {
            total += array.get(i);
        }
        return total;
    }

    /**
     * splits the input into 2 ArrayList for ease of use
     */
    public void setArrays() {
        for (int i = 0; i < input.size(); i++) {
            if (isEven(i)) {
                evens.add(input.get(i));
            }
            else if (!isEven(i)) {
                odds.add(input.get(i));
            }
        }
    }

    /**
     * logic to create a 2D array,
     * populate it with slopes of mountain
     */
    public void make2DGrid() {
        String grid[][] = new String[maxHeight+4][totalLength+1];

        for (int row = 0; row <= maxHeight+3; row++) {

            for(int col = 0; col < totalLength+1; col++) {
                // keep track of current row/col
                grid[row][col] = " ";
            }
        }
        int colIndex=0;
        int rowIndex=0;

        int currentI=0;
        while (currentI<totalLength) {
            if (evens.get(colIndex).equals(1)) {

                grid[rowIndex][currentI] = "/";

                currentI++;
            }

            if (currentI>=totalLength) {
                break;
            }
            if (currentI==maxLoc) {
                currentI++;
            }

            else if (evens.get(colIndex) > 1) {
                for (int i = 0; i < evens.get(colIndex); i++) {
                    grid[rowIndex][currentI] = "/";
                    rowIndex++;
                    currentI++;
                }
                rowIndex--;
            }

            if (currentI>=totalLength) {
                break;
            }
            if (currentI==maxLoc) {
                currentI++;
            }

            if (odds.get(colIndex).equals(1)) {
                grid[rowIndex][currentI] = "\\";
                currentI++;
            }

            if (currentI>=totalLength) {
                break;
            }
            else if (odds.get(colIndex) > 1) {
                for (int i = 0; i < odds.get(colIndex); i++) {
                    grid[rowIndex][currentI] = "\\";
                    rowIndex--;
                    currentI++;
                }
                rowIndex++;
            }
            colIndex++;
        }

        addPeak(grid);
        toString(grid);
    }

    /**
     * Converts the 2d array to output and prints it in reverse
     * @param array
     */
    public void toString(String[][] array) {
        for (int rows = array.length-1; rows >= 0; rows--) {
            for (int cols = 0; cols < array[0].length; cols++) {
                System.out.print(array[rows][cols]);
            }
            System.out.println();
        }
    }

    /**
     * adds the peak characters to the top of the mountain
     * @param grid
     */
    public void addPeak(String[][] grid) {
        grid[maxHeight+2][maxLoc-1] = " o ";
        grid[maxHeight+1][maxLoc-1] = "/|\\";
        grid[maxHeight][maxLoc+1]= ">";
        grid[maxHeight][maxLoc-1] = "<";
    }

    public static void main(String args[]) {
        Mountains m = new Mountains();
        InputLogic ip= new InputLogic();
        m.start();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Ask for user input
            System.out.println("Enter numbers separated by spaces (or type 'exit' to quit):");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            // Split the input string into an array of strings
            String[] numbers = input.split(" ");

            // Create an ArrayList to store the integers
            ArrayList<Integer> list = new ArrayList<>();

            // Convert each string to an integer and add it to the list
            try {
                for (String number : numbers) {
                    list.add(Integer.parseInt(number));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter only numbers separated by spaces.");
            }
        }
        scanner.close();
    }
}

