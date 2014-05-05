import gnu.trove.TLongArrayList;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/20/13
 * Time: 10:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem111Main {

    private static void testRepeatedDigits(int[] digits, int d, int numberOfNonRepeatingDigits, int level, TLongArrayList S) {
        if (numberOfNonRepeatingDigits == 0) {
            if (digits[0] != 0) {
                for (int i = 0; i < digits.length; ++i) {
                    if (digits[i] == -1) {
                        digits[i] = d;
                    }
                }
                long test = 0;
                long pow10 = 1;
                for (int i = 0; i < digits.length; ++i) {
                    test += digits[digits.length - i - 1] * pow10;
                    pow10 *= 10;
                }
                if (digits[0] != 0 && EulerUtil.isPrime(test)) {
                    S.add(test);
                }
            }
        } else {
            for (int i = 0; i < 10; ++i) {
                if (i != d) {
                    for (int l = level; l < digits.length; ++l) {
                        int[] newDigits = digits.clone();
                        newDigits[l] = i;
                        testRepeatedDigits(newDigits, d, numberOfNonRepeatingDigits - 1, l + 1, S);
                    }
                }
            }
        }
    }


    @SuppressWarnings("WeakerAccess")
    public static long solve(int n) {
        long result = 0;
        for (int d = 0; d < 10; ++d) {
            TLongArrayList S = new TLongArrayList();
            int numberOfNonRepeatingDigits = 0;

            while (S.size() == 0 && numberOfNonRepeatingDigits <= n) {
                int[] seed = new int[n];
                Arrays.fill(seed, -1);
                testRepeatedDigits(seed, d, numberOfNonRepeatingDigits, 0, S);
                ++numberOfNonRepeatingDigits;
            }

            result += EulerUtil.sum(S.toNativeArray());
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(10));
    }
}
