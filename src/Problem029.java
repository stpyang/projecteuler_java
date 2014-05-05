import gnu.trove.TIntArrayList;
import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/20/13
 * Time: 4:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem029 implements MySolution {

    private final int N;
    private static final ArrayComparator arrayComparator = new ArrayComparator();

    public Problem029(int N) {
        this.N = N;
    }

    private static int[] getExponents(TIntArrayList primes, int n) {
        int[] result = new int[primes.size()];
        int i = 0;
        for (int p : primes.toNativeArray()) {
            while (n % p == 0) {
                n /= p;
                ++result[i];
            }
            ++i;
        }
        return result;
    }

    private static int[] multiply(int[] array, int n) {
        int[] result = array.clone();
        for (int i = 0; i < array.length; ++i) result[i] *= n;
        return result;
    }

    private static int[] tail(int[] array) {
        return ArrayUtils.subarray(array, 1, array.length);
    }

    private static class ArrayComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            if (o1.length == 0 && o2.length == 0) return 0;
            if (o1.length != o2.length) return Integer.compare(o1.length, o2.length);
            if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
            return compare(tail(o1), tail(o2));
        }
    }

    @Override
    public long solve() {
        int result = 0;
        PrimeSieve primeSieve = new PrimeSieve(N);
        TIntArrayList primes = new TIntArrayList();
        for (int p = 2; p <= N; ++p) {
            if (primeSieve.isPrime(p)) primes.add(p);
        }
        List<int[]> exponents = new ArrayList<>();

        for (int a = 2; a <= N; ++a) {
            int[] baseExponents = getExponents(primes, a);
            for (int b = 2; b <= N; ++b) {
                exponents.add(multiply(baseExponents, b));
            }
        }

        Collections.sort(exponents, arrayComparator);

        for (int i = 1; i < exponents.size(); ++i) {
            if (arrayComparator.compare(exponents.get(i), exponents.get(i - 1)) != 0)
            ++result;
        }

        return 1 + result;
    }

    public static void main(String[] args) {
        System.out.println(new Problem029(100).solve());
    }
}
