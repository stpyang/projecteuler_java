/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/7/14
 * Time: 11:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem041Main {

    static boolean isPanDigital(long n, int numberOfDigits) {
        if (n < EulerUtil.longPow(10, numberOfDigits - 1) || n >= EulerUtil.longPow(10, numberOfDigits)) return false;
        int mask = 0;
        while (n > 0) {
            if (n % 10 == 0) {
                return false;
            }
            mask |= 1 << (n % 10);
            n /= 10;
        }
        return mask == EulerUtil.longPow(2, numberOfDigits + 1) - 2;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        // no need to test nine-digit numbers since they are divisible by 9 if they are pandigital

        int numberOfDigits = 8;
        long p = EulerUtil.longPow(10, 8) - 1;
        while (numberOfDigits > 0) {
            long lowerLimit = EulerUtil.longPow(10, numberOfDigits - 1);
            while (p > lowerLimit) {
                if (isPanDigital(p, numberOfDigits) && EulerUtil.isPrime(p)) {
                    return p;
                }
                --p;
            }
            --numberOfDigits;
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
