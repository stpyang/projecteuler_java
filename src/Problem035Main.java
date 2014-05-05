/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/31/13
 * Time: 9:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem035Main {

    static int rotate(int n) {
        return (int)EulerUtil.longPow(10, EulerUtil.getNumberOfDigits(n) - 1) * (n % 10) + n / 10;
    }

    private static boolean isCircularPrime(PrimeSieve primeSieve, int n) {
        if (!primeSieve.isPrime(n)) {
            return false;
        }
        int r = rotate(n);
        while (r != n) {
            if (!primeSieve.isPrime(r)) {
                return false;
            }
            r = rotate(r);
        }
        return true;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        long result = 0;
        PrimeSieve primeSieve = new PrimeSieve(N);
        for(int i = 2; i < N; ++i) {
            if (isCircularPrime(primeSieve, i)) {
                ++result;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(1000000));
    }
}
