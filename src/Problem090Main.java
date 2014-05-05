import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/6/13
 * Time: 5:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem090Main {

    private static void calculateDice(List<boolean[]> result, boolean[] die, int numberOfSides, int level) {
        if (numberOfSides == 6) {
            result.add(die);
        } else if (level < die.length) {
            for (int i = level; i < die.length; ++i) {
                if (!die[i]) {
                    boolean[] temp = die.clone();
                    temp[i] = true;
                    calculateDice(result, temp, 1 + numberOfSides, i + 1);
                }
            }
        }
    }

//    static String printDie(boolean[] die) {
//        Set<Integer> faces = new HashSet<>();
//        for (int i = 0; i < die.length; ++i) {
//            if (die[i]) {
//                faces.add(i);
//            }
//        }
//        return faces.toString();
//    }

    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        double result = 0;
        boolean[] die1 = new boolean[10];
        boolean[] die2 = new boolean[10];

        List<boolean[]> dice1 = new ArrayList<>();
        calculateDice(dice1, die1, 0, 0);
        List<boolean[]> dice2 = new ArrayList<>();
        calculateDice(dice2, die2, 0, 0);

        for (boolean[] d1 : dice1) {
            for (boolean[] d2 : dice2)  {

//                d1 = new boolean[] { true, false, false, false, false, true, true, true, true, true};
//                d2 = new boolean[] { false, true, true, true, true, false, true, true, false, false};

                if ((!d1[0]||!d2[1])&&(!d1[1]||!d2[0])) {
                    continue;
                }
                if ((!d1[0]||!d2[4])&&(!d1[4]||!d2[0])) {
                    continue;
                }
                if ((!d1[0]||!(d2[9]||d2[6]))&&(!(d1[9]||d1[6])||!d2[0])) {
                    continue;
                }
                if ((!d1[1]||!(d2[6]||d2[9]))&&(!(d1[6]||d1[9])||!d2[1])) {
                    continue;
                }
                if ((!d1[2]||!d2[5])&&(!d1[5]||!d2[2])) {
                    continue;
                }
                if ((!d1[3]||!(d2[6]||d2[9]))&&(!(d1[6]||d1[9])||!d2[3])) {
                    continue;
                }
                if ((!d1[4]||!(d2[6]||d2[9]))&&(!(d1[6]||d1[9])||!d2[4])) {
                    continue;
                }
                if ((!d1[8]||!d2[1])&&(!d1[1]||!d2[8])) {
                    continue;
                }

                if (Arrays.equals(d1, d2)) {
                    result += 1.0;
                } else {
                    result += 0.5;
                }
            }
        }
        return Math.round(result);
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
