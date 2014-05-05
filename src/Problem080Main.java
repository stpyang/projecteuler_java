import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/4/13
 * Time: 9:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem080Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N, int numberOfDecimals) {
        int result = 0;

        for (int i = 1; i <= N; ++i) {
            if (EulerUtil.isSquare(i)) {
                continue;
            }
            int count = 0;

            int[] digits = EulerUtil.getDigits(i);
            int lPlus = (digits.length & 1) == 0 ? digits.length : digits.length + 1;

            int[] digitsPlus = new int[lPlus];
            System.arraycopy(digits, 0, digitsPlus, 0, digits.length);

            BigInteger p = BigInteger.ZERO;
            BigInteger c = BigInteger.ZERO;
            for (int j = digitsPlus.length; j > 0; j -= 2) {
                c = c.multiply(BigInteger.valueOf(100));
                c = c.add(BigInteger.valueOf(10 * digitsPlus[j - 1] + digitsPlus[j - 2]));

                int x = 0;
                while (BigInteger.valueOf(x + 1).multiply(BigInteger.valueOf(20).multiply(p).add(BigInteger.valueOf(x + 1))).compareTo(c) <= 0) {
                    ++x;
                }
//                System.out.print(x);
                result += x;
                ++count;

                c = c.subtract(BigInteger.valueOf(x).multiply(BigInteger.valueOf(20).multiply(p).add(BigInteger.valueOf(x))));
                p = p.multiply(BigInteger.TEN).add(BigInteger.valueOf(x));
            }

//            System.out.print(".");

            while (count < numberOfDecimals) {
                c = c.multiply(BigInteger.valueOf(100));

                int x = 0;
                while (BigInteger.valueOf(x + 1).multiply(BigInteger.valueOf(20).multiply(p).add(BigInteger.valueOf(x + 1))).compareTo(c) <= 0) {
                    ++x;
                }
//                System.out.print(x);
                result += x;
                ++count;

                c = c.subtract(BigInteger.valueOf(x).multiply(BigInteger.valueOf(20).multiply(p).add(BigInteger.valueOf(x))));
                p = p.multiply(BigInteger.TEN).add(BigInteger.valueOf(x));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(100, 100));
    }
}
