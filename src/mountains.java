import java.util.ArrayList;
import java.util.Arrays;

public class mountains {

    ArrayList<Integer> input = new ArrayList<>(Arrays.asList(1, 1, 3, 1, 2, 3, 5, 2, 1, 2, 2, 3, 7, 2, 3, 1, 2, 1, 3, 5, 2, 3, 1,
            2, 5, 7, 3, 1, 3, 4, 2, 5, 3, 2, 4, 4, 1, 2, 1, 3, 3, 2, 1, 2));

    ArrayList<Integer> input2 = new ArrayList<>(Arrays.asList(10, 7, 12, 2, 4, 7, 2, 4, 1, 2, 6, 6, 3, 2, 1, 4, 7, 2, 7,
            3, 1, 3, 11, 4, 2, 1, 5, 2, 3, 3, 3, 6, 1, 3, 9, 5, 2, 1, 2, 11, 9, 2, 3, 8, 2, 5, 1, 2, 7, 2, 4, 11, 2, 12));

    ArrayList<Integer> evens = new ArrayList<>();
    ArrayList<Integer> odds = new ArrayList<>();
    int maxHeight=0;


    public void start() {
        setArrays();
        System.out.println(evens.toString());
        int evenTotal = totalUpArray(evens);
        System.out.println(odds.toString());
        int oddTotal = totalUpArray(odds);

        findMaxHeight();
        makeGrid();
       // buildMountains bm = new buildMountains(this);
       // bm.buildPeaks();
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
            if (tempMaxHeight>=maxHeight) {
                maxHeight=tempMaxHeight;
                maxIndex = i;
            }
            tempMaxHeight = currHeight;

        }
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
        int loc = 0;
        int currIndex=0;
        for (int i = 0; i < index; i++) {
            loc += array1.get(i) + array2.get(i);
           // System.out.println(loc);
            currIndex = i;
        }
        loc += array1.get(currIndex+1);
        return loc;
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
        char grid[][] = new char[maxHeight][input.size()];
        for (int row = maxHeight-1; row >= 0; row--) {
            for(int col = 0; col < input.size(); col++) {
                System.out.println(grid[row][col]);
            }
            System.out.println();
        }
    }



    public static void main(String args[]) {
        mountains m = new mountains();
        m.start();

    }
}

