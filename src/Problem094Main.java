import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/10/13
 * Time: 1:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem094Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve(long N) {
        BigInteger result = BigInteger.ZERO;
        for (long a = 2; a <= N / 3; ++a) {
            if ((a & 1) == 0) {
                continue;
            }
            long b;

            b = (a + 1) / 2;
            if (EulerUtil.isSquare(a * a - b * b)) {
                result = result.add((BigInteger.valueOf(3).multiply(BigInteger.valueOf(a)).add(BigInteger.ONE)));
            }

            b = (a - 1) / 2;
            if (EulerUtil.isSquare(a * a - b * b)) {
                result = result.add((BigInteger.valueOf(3).multiply(BigInteger.valueOf(a)).subtract(BigInteger.ONE)));
            }

        }
        return result.longValue();
    }

    public static void main(String[] args) {
        System.out.println(solve(1000000000L));
    }
}
