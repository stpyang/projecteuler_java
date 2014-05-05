/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/20/13
 * Time: 8:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem113Main {

    private static long numberOfIncreasingSequences(int digits) {
        if (digits == 1) {
            return 0;
        } else {
            return EulerUtil.binomial(10 + digits - 1, digits) - EulerUtil.binomial(10 + digits - 2, digits - 1);
        }
    }

    private static long numberOfDecreasingSequences(int digits) {
        if (digits == 1) {
            return 0;
        } else {
            return EulerUtil.binomial(10 + digits - 1, digits) - 1;
        }
    }

    private static long numberOfNonBouncySequences(int digits) {
        if (digits == 1) {
            return 9;
        }
        return numberOfIncreasingSequences(digits) + numberOfDecreasingSequences(digits) - 9;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        long result = 0;
        for (int d = 1; d <= N; ++d) {
            result += numberOfNonBouncySequences(d);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(100));
    }
}
