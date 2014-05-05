import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: syang
 * Date: 2/13/14
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem055Main {

    static boolean isPalindrome(BigInteger bigN) {
        return bigN.compareTo(reverse(bigN)) == 0;
    }

    static BigInteger reverse(BigInteger bigN) {
        BigInteger result = BigInteger.ZERO;
        while (bigN.signum() > 0) {
            result = result.multiply(BigInteger.TEN);
            result = result.add(bigN.mod(BigInteger.TEN));
            bigN = bigN.divide(BigInteger.TEN);
        }
        return result;
    }

    static boolean isLychrel(long n) {
        BigInteger bigN = BigInteger.valueOf(n);
        for (int i = 0; i < 50; ++i) {
            bigN = bigN.add(reverse(bigN));
            if (isPalindrome(bigN)) return false;
        }
        return true;
    }

    public static long solve(long N) {
        long result = 0;
        for (int n = 0; n < N; ++n) {
            if (isLychrel(n)) {
                ++result;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(10000));
    }
}
