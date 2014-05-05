import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/2/13
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem074Main {

    private static long[] sumFactorialDigits;

    private static long getSumFactorialDigits(int n) {
        long result = 0;
        while (n > 0) {
            result += EulerUtil.factorial(n % 10);
            n /= 10;
        }
        return result;
    }

    private static int calculateNumberOfNonrepeatingTerms(long i) {
        SortedSet<Long> usedTerms = new TreeSet<>();
        usedTerms.add(i);
        int result = 1;
        long j = i;
        while (!usedTerms.contains(sumFactorialDigits[(int)j])) {
            usedTerms.add(sumFactorialDigits[(int)j]);
            j = sumFactorialDigits[(int)j];
            ++result;
        }
        return result;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N, int period) {
        int result = 0;

        // calculate sumFactorialDigits
        int numberOfDigits = EulerUtil.getNumberOfDigits(N);

        sumFactorialDigits = new long[1 + (int)EulerUtil.factorial(9) * numberOfDigits];
        for (int i = 0; i < sumFactorialDigits.length; ++i) {
            sumFactorialDigits[i] = getSumFactorialDigits(i);
        }

        for (long i = 0; i < N; ++i) {
            if (calculateNumberOfNonrepeatingTerms(i) == period) {
                ++result;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(1000000, 60));
    }
}

