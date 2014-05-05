import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/10/13
 * Time: 2:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem097Main {

    // 28433 * 7830457

    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        BigInteger result = BigInteger.ONE;

        List<Boolean> powersOfTwo = new ArrayList<>();
        int n = 7830457;
//        int n = 20;
        while (n > 0) {
            if ((n & 1) == 1) {
                powersOfTwo.add(true);
            } else {
                powersOfTwo.add(false);
            }
            n /= 2;
        }

        BigInteger p = BigInteger.valueOf(2);
        for (Boolean exponent : powersOfTwo) {
            if (exponent) {
                result = (result.multiply(p)).mod(BigInteger.valueOf(10000000000L));
            }
            p = (p.multiply(p)).mod(BigInteger.valueOf(10000000000L));
        }

        result = BigInteger.valueOf(28433).multiply(result).add(BigInteger.ONE);
        return result.mod(BigInteger.valueOf(10000000000L)).longValue();
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
