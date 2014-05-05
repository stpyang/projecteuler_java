/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/22/13
 * Time: 10:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem034Main {

    private static final int[] factorial = new int[] { 1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880 };

    static long digitFactorial(int n) {
        long result = 0;
        while (n > 0) {
            result += factorial[n % 10];
            n /= 10;
        }
        return result;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        long result = 0;
        int i = 3;
        while (i < EulerUtil.getNumberOfDigits(i) * factorial[9]) {
            if (i == digitFactorial(i)) {
                result += i;
            }
            ++i;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
