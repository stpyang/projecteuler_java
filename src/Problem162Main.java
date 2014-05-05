import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/23/14
 * Time: 3:04 PM
 * To change this template use File | Settings | File Templates.
 */

// todo: implement divide and modulus for MyBigInteger
public class Problem162Main {

    // use inclusion-exclusion to calculate
    // 15 * 16^(n-1) - 43 * 15^(n-1) + 41 * 14^(n-1) - 13 ^ n

    private static final BigInteger THIRTEEN = BigInteger.valueOf(13);
    private static final BigInteger FOURTEEN = BigInteger.valueOf(14);
    private static final BigInteger FIFTEEN = BigInteger.valueOf(15);
    private static final BigInteger SIXTEEN = BigInteger.valueOf(16);

    private static BigInteger bigPow(BigInteger base, int exponent) {
        BigInteger result = BigInteger.ONE;
        for(int i = 0; i < exponent; ++i) {
            result = result.multiply(base);
        }
        return result;
    }

    private static BigInteger _solve(int n) {
        BigInteger A = FIFTEEN.multiply(bigPow(SIXTEEN, n - 1));
        BigInteger B = BigInteger.valueOf(-43).multiply(bigPow(FIFTEEN, n - 1));
        BigInteger C = BigInteger.valueOf(41).multiply(bigPow(FOURTEEN, n - 1));
        BigInteger D = BigInteger.valueOf(-1).multiply(bigPow(THIRTEEN, n));
        return A.add(B).add(C).add(D);
    }

    @SuppressWarnings("WeakerAccess")
    public static String solve(int N) {
        StringBuilder result = new StringBuilder();
        BigInteger total = BigInteger.ZERO;
        for(int i = 1; i <= N; ++i) {
            total = total.add(_solve(i));
        }

        while (total.signum() > 0) {
            result.append(Long.toHexString(total.mod(SIXTEEN).longValue()).toUpperCase());
            total = total.divide(SIXTEEN);
        }
        result = result.reverse();

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(solve(16));
    }
}
