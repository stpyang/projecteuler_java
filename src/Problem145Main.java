/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/31/13
 * Time: 8:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem145Main {

    //todo: why are there no reversable numbers above 89990001?

    private static long numberOfReversibleNumbers(int numberOfDigits) {
        if ((numberOfDigits & 1) == 0) {
            return 20 * EulerUtil.longPow(30, numberOfDigits / 2 - 1);
        } else if (numberOfDigits % 4 == 1) {
            return 0;
        } else if (numberOfDigits % 4 == 3) {
            return (EulerUtil.longPow(20 * 25, (numberOfDigits + 1) / 4)) / 5;
        }
        return 0;
    }

    static boolean isReversible(long i) {
        if (i % 10 == 0) {
            return false;
        }
        int carry = 0;
        for(int d = 0; d < EulerUtil.getNumberOfDigits(i); ++d) {
            long digit1 = (i / EulerUtil.longPow(10, d)) % 10;
            long digit2 = (i / EulerUtil.longPow(10, EulerUtil.getNumberOfDigits(i) - d - 1)) % 10;

            if (((digit1 + digit2 + carry) & 1) == 0) {
                return false;
            }

            carry = (digit1 + digit2 + carry > 9) ? 1 : 0;
        }
        return true;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int numberOfDigits) {
        long result = 0;
        for(int d = 1; d <= numberOfDigits; ++d) {
            result += numberOfReversibleNumbers(d);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(9));
    }
}
