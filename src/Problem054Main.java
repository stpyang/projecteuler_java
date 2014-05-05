import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 2/6/14
 * Time: 1:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem054Main {

    public static int compareHands(String s1, String s2) {
        return compareHands(s1.split(" "), s2.split(" "));
    }

    private static int compareHands(String[] s1, String[] s2) {
        PokerHand p1 = new PokerHand(s1);
        PokerHand p2 = new PokerHand(s2);

        return p1.compareTo(p2);
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(String fileName) {
        long result = 0;

        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] inputStrings = line.split(" ");
                if (compareHands(Arrays.copyOfRange(inputStrings, 0, 5), Arrays.copyOfRange(inputStrings, 5, 10)) < 0) {
                    ++result;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve("data/poker.txt"));
    }
}
