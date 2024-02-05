import java.util.ArrayList;

public class buildMountains {

    private ArrayList<Integer> input;
    private ArrayList<Integer> evens;
    private ArrayList<Integer> odds;

    public buildMountains(mountains mountains) {

        this.input = mountains.getInputArrays();
        this.evens = mountains.getEvens();
        this.odds = mountains.getOdds();
    }

    public void buildPeaks() {
        StringBuilder sb = new StringBuilder();
        System.out.println(evens.size());
        for (int i = 0; i < input.size()/2; i++) {
            for (int j = 0; j < evens.get(i); j++) {
                sb.append("/");
            }
            for (int k = 0; k < odds.get(i); k++) {
                sb.append("\\");
            }
        }

        System.out.println(sb);
    }
}
