import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/12/13
 * Time: 3:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem101Main {

    private static long u(int n) {
        if (n == 1) {
            return 1;
        } else {
            return (EulerUtil.longPow(n, 11) + 1) / (n + 1);
        }
    }

    private static BigInteger lagrangeU(int x, int i) {
        BigInteger result = BigInteger.ZERO;

        for (int j = 1; j <= i; ++j) {
            BigInteger temp = BigInteger.valueOf(u(j));

            for (int k = 1; k <= i; ++k) {
                if (k != j) {
                    temp = temp.multiply(BigInteger.valueOf(x - k));
                }
            }
            for (int k = 1; k <= i; ++k) {
                if (k != j) {
                    temp = temp.divide(BigInteger.valueOf(j - k));
                }
            }
            result = result.add(temp);
        }
        return result;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        BigInteger result = BigInteger.ZERO;
        for (int i = 1; i <= N; ++i) {
            result = result.add(lagrangeU(i + 1, i));
        }
        return result.longValue();
    }

    public static void main(String[] args) {
        System.out.println(solve(10));
    }
}
