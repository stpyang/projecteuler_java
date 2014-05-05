import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/17/14
 * Time: 10:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem071Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve(int numerator, int denominator, int limit) {
        // generate a faret sequence of order denominator
        LinkedList<Pair<Integer>> faretSequence = new LinkedList<>();
        faretSequence.add(new Pair<>(0, 1));
        faretSequence.add(new Pair<>(1, 1));

        for (int order = 2; order <= denominator; ++order) {
            int i = 0;
            int j = 1;
            while (j < faretSequence.size()) {
                if (faretSequence.get(i).b + faretSequence.get(j).b <= denominator) {
                    Pair<Integer> newPair = new Pair<>(faretSequence.get(i).a + faretSequence.get(j).a, faretSequence.get(i).b + faretSequence.get(j).b);
                    faretSequence.add(j, newPair);
                    ++j;
                }
                i = j;
                ++j;
            }
        }

        // a/b is the fraction directly to the left of numerator/denominator
        int a = 0;
        int b = 0;
        for (Pair<Integer> p : faretSequence) {
            if (p.a == numerator && p.b == denominator) {
                break;
            } else {
                a = p.a;
                b = p.b;
            }
        }

        // we calculate mediant over and over again until the denominator exceeds limit

        int k = (limit - b) / denominator;

        return a + k * numerator;
    }

    public static void main(String[] args) {
        System.out.println(solve(3, 7, 1000000));
    }
}
