import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/31/13
 * Time: 1:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem187Main {
    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        int result = 0;

        long numberOfPrimes = 0;
        boolean[] isPrime = new boolean[N + 1];
        long upperLimit = Math.round(Math.ceil(Math.sqrt(N)));
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        int i = 2;
        while (i < upperLimit) {
            if (isPrime[i]) {
                ++numberOfPrimes;
                int j = i;
                while (j + i < isPrime.length) {
                    isPrime[j + i] = false;
                    j += i;
                }
            }
            ++i;
        }
        while (i < isPrime.length) {
            if (isPrime[i])  {
                ++numberOfPrimes;
            }
            ++i;
        }

        i = 0;
        int j = N;
        while (i <= j) {
            while (!isPrime[++i]) { }
            while (i * j > N || !isPrime[j]) {
                if (isPrime[j]) {
                    --numberOfPrimes;
                }
                --j;
            }
            if (i <= j) {
                result += numberOfPrimes;
            }
            --numberOfPrimes;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(100000000));
    }
}
