import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/16/14
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem050Main {

    private static int getUpperLimit(PrimeSieve primeSieve, int N) {
        int result = 0;
        int sum = 0;
        for (int p = 2; p < N && sum < N; ++p) {
            if (primeSieve.isPrime(p)) {
                sum += p;
                ++result;
            }
        }
        return result;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        PrimeSieve primeSieve = new PrimeSieve(N);
        int upperLimit = getUpperLimit(primeSieve, N);

        while (upperLimit > 0) {
            int p = 2;
            Deque<Integer> primes = new ArrayDeque<>();
            int sum = 0;
            while (p < N && primes.size() < upperLimit) {
                if (primeSieve.isPrime(p)) {
                    primes.addFirst(p);
                    sum += p;
                }
                ++p;
            }

            while (sum < N) {
                if (primeSieve.isPrime(sum)) {
                    return sum;
                } else {
                    p = primeSieve.getNextPrime(p);
                    primes.addFirst(p);
                    sum += p;
                    sum -= primes.removeLast();
                }
            }

            --upperLimit;
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solve(1000000));
    }
}
