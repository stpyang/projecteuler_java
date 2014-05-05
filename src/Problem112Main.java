/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/20/13
 * Time: 4:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem112Main {

    private static boolean notBouncy(long n) {
        if (n < 100) {
            return false;
        }
        boolean isIncreasing = true;
        boolean isDecreasing = true;
        long lastDigit = n % 10;
        while (n > 0) {
            if (lastDigit < n % 10) {
                isIncreasing = false;
            } else if (lastDigit > n % 10) {
                isDecreasing = false;
            }
            if (!isDecreasing && !isIncreasing) {
                return false;
            }
            lastDigit = n % 10;
            n /= 10;
        }
        return true;
    }

    // todo: use estimate for bouncy numbers to speed this up
    @SuppressWarnings("WeakerAccess")
    public static long solve(double proportion) {
        if (proportion <= 0 || proportion >= 1) {
            throw new IllegalArgumentException("Proportion must be between 0 and 1");
        }
        int nonBouncy = 99;
        long i = 100;
        while (true) {
            if (notBouncy(i)) {
                ++nonBouncy;
            }

            if (i == Math.round(nonBouncy / (1 - proportion))) {
                return i;
            }
            ++i;
        }
    }

    public static void main(String[] args) {
        System.out.println(solve(0.99));
    }
}
