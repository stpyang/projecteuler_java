/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/21/13
 * Time: 9:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem030 implements MySolution {

    private final int N;

    public Problem030(int N) {
        this.N = N;
    }

    private static long powerSumOfDigits(int n, int exponent) {
        if (n == 0) return 0;
        else return EulerUtil.longPow(n % 10, exponent) + powerSumOfDigits(n / 10, exponent);
    }

    @Override
    public long solve() {
        long result = 0;

        // find the maximal number of digits
        int maxDigits = 1;
        while (EulerUtil.getNumberOfDigits(maxDigits * EulerUtil.longPow(9, N)) > maxDigits) {
            ++maxDigits;
        }

        for (int i = 2; i < EulerUtil.longPow(10, maxDigits); ++i) {
            if (powerSumOfDigits(i, N) == i) {
                result += i;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Problem030(5).solve());
    }
}
