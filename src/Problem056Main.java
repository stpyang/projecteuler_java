import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: syang
 * Date: 2/13/14
 * Time: 5:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem056Main {

    static int digitSum(BigInteger bigN) {
        int result = 0;
        while (bigN.signum() > 0) {
            result += bigN.mod(BigInteger.TEN).longValue();
            bigN = bigN.divide(BigInteger.TEN);
        }
        return result;
    }

    public static long solve(int N) {
        long result = 0;

        for (int a = N - 1; a > -1; --a) {
            if (a % 10 == 0) continue;
            for (int b = N - 1; b > -1; --b) {
                // break if there's no hope of bettering the result we already have
                if (9 * b * Math.log10(a) < result) break;

                BigInteger bigA = BigInteger.valueOf(a);
                BigInteger bigC = bigA.pow(b);
                result = Math.max(result, digitSum(bigC));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(100));
    }
}
