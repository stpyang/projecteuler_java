import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/20/13
 * Time: 2:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem109Main {

    static class Throw extends Pair<Integer> {
        Throw(Integer baseValue, Integer multiplier) {
            super(baseValue, multiplier);
        }

        public int getScore() {
            return a * b;
        }

        boolean isDouble() {
            return b == 2;
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        long result = 0;
        List<Throw> possibleThrows = new ArrayList<>();
        List<Throw> possibleDoubles = new ArrayList<>();
        for (int baseValue = 1; baseValue <= 20; ++baseValue) {
            for (int multiplier = 1; multiplier < 4; ++multiplier) {
                Throw t = new Throw(baseValue, multiplier);
                possibleThrows.add(t);
                if (t.isDouble()) {
                    possibleDoubles.add(t);
                }
            }
        }
        possibleThrows.add(new Throw(25, 1));
        Throw bullseye = new Throw(25, 2);
        possibleThrows.add(bullseye);
        possibleDoubles.add(bullseye);

        // one throw
        for (Throw d : possibleDoubles) {
            if (d.getScore() < N) {
                ++result;
            }
        }

        // two throws
        for (Throw t1 : possibleThrows) {
            for (Throw d : possibleDoubles) {
                if (t1.getScore() + d.getScore() < N) {
                    ++result;
                }
            }
        }

        // three throws
        for (int i = 0; i < possibleThrows.size(); ++i) {
            Throw t1 = possibleThrows.get(i);
            for (int j = i; j < possibleThrows.size(); ++j) {
                Throw t2 = possibleThrows.get(j);
                for (Throw d : possibleDoubles) {
                    if (t1.getScore() + t2.getScore() + d.getScore() < N) {
                        ++result;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(100));
    }
}
