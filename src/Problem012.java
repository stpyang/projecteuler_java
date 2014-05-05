import gnu.trove.TIntIntHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/13/13
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem012 implements MySolution {

    private final int N;

    public Problem012(int N) {
        this.N = N;
    }

    private static final TIntIntHashMap numberOfDivisorsMap = new TIntIntHashMap();

    // memozied brute force computation of the number of divisors
    private static int numberOfDivisors(int n) {
        if (numberOfDivisorsMap.containsKey(n)) return numberOfDivisorsMap.get(n);
        else {
            int result = 0;
            for (int i = 1; i < n; ++i) {
                if (n % i == 0) ++result;
            }
            numberOfDivisorsMap.put(n, result / 2);
            return result;
        }
    }

    @Override
    public long solve() {
        int n = 2;
        while (true) {
            int b = numberOfDivisors(n + 1);
            // test n knowing that (n + 1) is odd and n is even
            if (b * numberOfDivisors(n / 2) > N) return n * (n + 1) / 2;
            ++n;

            // test n + 1 knowing that n is odd and (n + 1) is even
            if (b * numberOfDivisors((n + 1) / 2) > N)  return n * (n + 1) / 2;
            ++n;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Problem012(500).solve());
    }
}
