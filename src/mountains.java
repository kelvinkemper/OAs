import java.util.ArrayList;
import java.util.Arrays;

public class mountains {

    ArrayList<Integer> input2 = new ArrayList<>(Arrays.asList(1, 1, 3, 1, 2, 3, 5, 2, 1, 2, 2, 3, 7, 2, 3, 1, 2, 1, 3, 5, 2, 3, 1,
            2, 5, 7, 3, 1, 3, 4, 2, 5, 3, 2, 4, 4, 1, 2, 1, 3, 3, 2, 1, 2));

    ArrayList<Integer> input = new ArrayList<>(Arrays.asList(10, 7, 12, 2, 4, 7, 2, 4, 1, 2, 6, 6, 3, 2, 1, 4, 7, 2, 7,
            3, 1, 3, 11, 4, 2, 1, 5, 2, 3, 3, 3, 6, 1, 3, 9, 5, 2, 1, 2, 11, 9, 2, 3, 8, 2, 5, 1, 2, 7, 2, 4, 11, 2, 12));

    ArrayList<Integer> evens = new ArrayList<>();
    ArrayList<Integer> odds = new ArrayList<>();

    ArrayList<Integer> expandedEvens = new ArrayList<>();
    ArrayList<Integer> expandedOdds = new ArrayList<>();

    int maxHeight=0;
    int maxLoc=0;
    int totalLength=0;


    public void start() {
        setArrays();
        System.out.println(evens.toString());
        int evenTotal = totalUpArray(evens);
        System.out.println(odds.toString());
        int oddTotal = totalUpArray(odds);

        findMaxHeight();
        findTotalLength();
        makeGrid();
        buildMountains bm = new buildMountains(this);
        bm.buildPeaks();
    }

    public void findTotalLength() {
        for (int i : input) {
            totalLength += i;
        }
        System.out.println("Total length: " + totalLength);
    }
    
    public void findMaxHeight() {
        int currentDiff = 0;
        int currHeight = 0;
        int tempMaxHeight = 0;
        int maxIndex = 0;
        for (int i = 0; i < input.size()/2; i++) {
            currentDiff = evens.get(i) - odds.get(i);
            tempMaxHeight += evens.get(i);
            currHeight += currentDiff;

            //System.out.println(i+". current height: " + currHeight);
            if (tempMaxHeight >= maxHeight) {
                maxHeight = tempMaxHeight;
                maxIndex = i;
            }
            tempMaxHeight = currHeight;
        }
       //     if (currentDiff >0) {
       //         for (int j = 0; j < evens.get(i); j++) {
       //             System.out.println("/ ");
       //         }
       //     } else if (currentDiff < 0) {
       //         for (int j = 0; j < odds.get(i); j++) {
       //             System.out.println("\\ ");
       //
       //         }
       //     } else {
       //         System.out.print("/ \\ ");
       //     }
       //     System.out.println();
       //
       // }
        //System.out.println("max index: " + maxIndex);
        System.out.println("peak: " + maxHeight + " at space " + calculateMaxHeightLocation(evens, odds, maxIndex));
    }
    public static boolean isEven(int n) {
        if (n%2==0) {
            return true;
        }
        return false;
    }

    public int calculateMaxHeightLocation(ArrayList<Integer> array1, ArrayList<Integer> array2, int index) {
        int currIndex=0;
        for (int i = 0; i < index; i++) {
            maxLoc += array1.get(i) + array2.get(i);
           // System.out.println(loc);
            currIndex = i;
        }
        maxLoc += array1.get(currIndex+1);
        return maxLoc;
    }

    public int totalUpArray(ArrayList<Integer> array) {
        int total = 0;
        for (int i = 0; i < array.size(); i++) {
            total += array.get(i);
        }
        return total;
    }

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

    public ArrayList<Integer> getInputArrays() {
        return input;
    }

    public ArrayList<Integer> getEvens() {
        return evens;
    }

    public ArrayList<Integer> getOdds() {
        return odds;
    }


    public void makeGrid() {
        System.out.println("Max height: " + maxHeight);
        String grid[][] = new String[maxHeight+3][totalLength];

      // for (int i = maxHeight-3; i<=maxHeight; i++) {
      //     for (int col = 0; col < totalLength; col++) {
      //         grid[i][col] = "";
      //     }
      // }

        for (int row = 0; row <= maxHeight; row++) {

            for(int col = 0; col < totalLength; col++) {
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


      //  for (int fHalfRow=0; fHalfRow<=maxHeight-3; fHalfRow++) {
      //      for (int fHalfCol =0; fHalfCol<maxLoc; fHalfCol++) {
      //          //if ()
      //          grid[fHalfRow][fHalfCol] = "|x|";
      //      }
      //  }



      // for (int lHalfRow=0; lHalfRow<=maxHeight-3; lHalfRow++) {
      //     for (int lHalfCol =maxLoc; lHalfCol<totalLength; lHalfCol++) {
      //         grid[lHalfRow][lHalfCol] = "|y|";
      //     }
      // }



        //System.out.println(Arrays.deepToString(grid).replace("], ", "]\n"));


        toString(grid);
    }
    public void toString(String[][] array) {
        for (int rows = maxHeight; rows >= 0; rows--) {
            for (int cols = 0; cols < array[0].length; cols++) {
                System.out.print(array[rows][cols]);
            }
            System.out.println();
        }
    }


    /**
     * might have been a good idea
     * @param list
     * @param expandedList
     */
    public void expandLists(ArrayList<Integer> list, ArrayList<Integer> expandedList) {
        for (int i : list) {
            for (int j = 0; j < i; j++) {
                expandedList.add(1);
            }
        }
    }

    public void drawPeak() {
        System.out.println(" o "); // grid[maxHeight+2][maxLoc]
        System.out.println("/|\\"); // grid[maxHeight+1][maxLoc]
        System.out.println("< >"); //grid[maxHeight][maxLoc]
    }



    public static void main(String args[]) {
        mountains m = new mountains();
        m.start();

    }
}

