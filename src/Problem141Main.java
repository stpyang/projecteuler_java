/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 2/6/14
 * Time: 4:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem141Main {

    /*
    private static final BigInteger TWO = BigInteger.valueOf(2);

    public static BigInteger sqrt(BigInteger n) {
        if (n.signum() >= 0) {
            final int bitLength = n.bitLength();
            BigInteger root = BigInteger.ONE.shiftLeft(bitLength / 2);

            while (!isSqrt(n, root)) {
                root = root.add(n.divide(root)).divide(TWO);
            }
            return root;
        } else {
            throw new ArithmeticException("square root of negative number");
        }
    }

    private static boolean isSqrt(BigInteger n, BigInteger root) {
        final BigInteger lowerBound = root.pow(2);
        final BigInteger upperBound = root.add(BigInteger.ONE).pow(2);
        return lowerBound.compareTo(n) <= 0 && n.compareTo(upperBound) < 0;
    }

    static boolean isSquare(BigInteger n) {
        BigInteger test = sqrt(n);
        test = test.multiply(test);
        return (test.compareTo(n) == 0);
    }
    */

    public static long solve(long upperLimit) {
        long result = 0;

        long n0;
        long n1;
        for (long k = 1; k * (8 * k + 1) < upperLimit; ++k) {
            long a = 1;
            while (true) {
                n0 = k * a * (k * (a + 1) * (a + 1) * (a + 1) + a);
                if (n0 < 0 || n0 > upperLimit) {
                    break;
                }

                long b = a + 1;
                while (true) {
                    if (EulerUtil.gcd(a, b) == 1) {
                        n1 = k * a * (k * b * b * b + a);

                        if (n1 < 0 || n1 > upperLimit) {
                            break;
                        }

                        if (EulerUtil.isSquare(n1)) {
                            result += n1;
                        }
                    }
                    ++b;
                }
                ++a;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(1000000000000L)); // need to scale this up by 1000
    }
}
