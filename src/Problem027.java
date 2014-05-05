import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/20/13
 * Time: 1:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem027 implements MySolution {

    private final int N;

    public Problem027(int N) {
        this.N = N;
    }

    @Override
    public long solve() {
        PrimeSieve primeSieve = new PrimeSieve(2 * N);

        // first we form a list of candidate pairs (a, b)
        // removing cases where b is not (n == 0)
        // removing cases where 1 + a + b is not (n == 1)
        List<long[]> candidates = new ArrayList<>();
        for (long b = 2; b < N; ++b) {
            if (!primeSieve.isPrime((int)b)) continue;
            for (long a = -N + 1; a < N; ++a) {
                if (primeSieve.isPrime((int)(1 + a + b))) {
                    long[] candidate = new long[]{ a, b };
                    candidates.add(candidate);
                }
            }
        }

        // then for each consecutive value of n we remove candidates which
        // are not prime for b + n * (a + n)
        int n = 2;
        while (candidates.size() > 1) {
            List<long[]> temp = new ArrayList<>(candidates);
            for (long[] candidate : temp) {
                long a = candidate[0];
                long b = candidate[1];
                long c = b + n * (a + n);
                if (0 < c && c <= 2 * N) {
                    if (!primeSieve.isPrime((int)c)) candidates.remove(candidate);
                } else if (!EulerUtil.isPrime(c)) {
                    candidates.remove(candidate);
                }
            }
            ++n;
        }

        // return the survivor
        return candidates.get(0)[0] * candidates.get(0)[1];
    }

    public static void main(String[] args) {
        System.out.println(new Problem027(1000).solve());
    }
}
