/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/6/14
 * Time: 3:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem037Main {

    private static boolean isTruncatable(PrimeSieve primeSieve, int n) {
        int i;
        i = n;
        while (i > 0) {
            if (!primeSieve.isPrime(i)) {
                return false;
            }
            i /= 10;
        }

        i = n;
        long pow10 = EulerUtil.longPow(10, EulerUtil.getNumberOfDigits(n) - 1);
        while (i > 0) {
            if (!primeSieve.isPrime(i)) {
                return false;
            }
            i = (int)(i % pow10);
            pow10 /= 10;
        }

        return true;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        long[] truncateablePrimes = new long[11];
        int index = 0;

        int sieveLength = 3797;
        int i = 10;
        while (index < 11) {
            PrimeSieve primeSieve = new PrimeSieve(sieveLength);
            while (i <= sieveLength) {
                if (isTruncatable(primeSieve, i)) {
                    truncateablePrimes[index++] = i;
                }
                ++i;
            }
            sieveLength *= 2;
        }

        return EulerUtil.sum(truncateablePrimes);
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
