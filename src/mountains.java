import java.util.ArrayList;
import java.util.Arrays;

public class mountains {

    ArrayList<Integer> input = new ArrayList<>(Arrays.asList(1, 1, 3, 1, 2, 3, 5, 2, 1, 2, 2, 3, 7, 2, 3, 1, 2, 1, 3, 5, 2, 3, 1,
            2, 5, 7, 3, 1, 3, 4, 2, 5, 3, 2, 4, 4, 1, 2, 1, 3, 3, 2, 1, 2));

    ArrayList<Integer> evens = new ArrayList<>();
    ArrayList<Integer> odds = new ArrayList<>();


    public void start() {
        setArrays();
      //  System.out.println(evens.toString());
        int evenTotal = totalUpArray(evens);
      //  System.out.println(odds.toString());
        int oddTotal = totalUpArray(odds);
      //  System.out.println(evenTotal + oddTotal);

        findMaxHeight();
    }
    
    public void findMaxHeight() {
        int currentDiff = 0;
        int maxHeight = 0;
        int holder = 0;
        for (int i = 0; i < input.size()/2; i++) {
            currentDiff = evens.get(i) - odds.get(i);
            System.out.println("current difference" + currentDiff);
            holder += currentDiff;
            //System.out.println(holder);
            if (currentDiff==0) {
                maxHeight++;
            } else if (currentDiff>0) {
                maxHeight+= currentDiff;
            }
            //System.out.println(maxHeight);
        }
        System.out.println(maxHeight);
    }
    public static boolean isEven(int n) {
        if (n%2==0) {
            return true;
        }
        return false;
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

    public static void main(String args[]) {
        mountains m = new mountains();
        m.start();

    }


}
