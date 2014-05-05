/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/13/13
 * Time: 3:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem104Main {

    private static final double SQRT_5 = Math.sqrt(5);
    private static final double PHI = (1.0 + Math.sqrt(5)) / 2.0;

    private static boolean isPanDigital(long n) {
        if (n < 100000000 || n >= 1000000000L) return false;
        int mask = 0;
        while (n > 0) {
            mask |= 1 << (n % 10);
            n /= 10;
        }
        return mask == 1022;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        int result = 3;
        long f2 = 1;
        long f1 = 1;
        long f;
        while (true) {
            f = (f1 + f2) % 1000000000;

            if (isPanDigital(f)) {
                double exponent = result * Math.log10(PHI) - Math.log10(SQRT_5);
                double d = Math.floor(exponent);
                exponent = exponent - d + 8;
                long firstDigits = (long)Math.floor(Math.pow(10, exponent));

                if (isPanDigital(firstDigits)) {
                    return result;
                }
            }
            f2 = f1;
            f1 = f;
            ++result;
        }
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
