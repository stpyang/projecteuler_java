import gnu.trove.TLongArrayList;

/**
 * Created by stpyang on 12/29/13.
 */
public class Problem119Main {

    private static final double LOG_2 = Math.log(2);
    private static final double LOG_10 = Math.log(10);

    private static int sumOfDigits(long n) {
        int result = 0;
        while(n > 0) {
            result += n % 10;
            n /= 10;
        }
        return result;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        TLongArrayList result = new TLongArrayList();

        int digits = 2;
        while (result.size() < N) {
            long upperLimit = EulerUtil.longPow(10, digits);
            int exponent = 2;
            while (exponent < digits * LOG_10 / LOG_2) {
                int base = 1 + (int)Math.pow(EulerUtil.longPow(10, digits - 1), 1.0 / exponent);
                while (EulerUtil.longPow(base, exponent) < upperLimit) {
                    long n = EulerUtil.longPow(base, exponent);
                    if (sumOfDigits(n) == base) {
                        result.add(n);
                    }
                    ++base;
                }
                ++exponent;
            }
            ++digits;
        }

        result.sort();
        return result.get(N - 1);
    }

    public static void main(String[] args) {
        System.out.println(solve(30));
    }
}