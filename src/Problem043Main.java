/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/7/14
 * Time: 2:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem043Main {

    private static int getNumber(int[] digits, int i) {
        return 100 * digits[i - 2] + 10 * digits[i - 1] + digits[i];
    }

    static boolean hasProperty(int[] digits) {
        return digits.length == 10 && getNumber(digits, 9) % 17 == 0 ||
                digits.length == 9 && getNumber(digits, 8) % 13 == 0 ||
                digits.length == 8 && getNumber(digits, 7) % 11 == 0 ||
                digits.length == 7 && getNumber(digits, 6) % 7 == 0 ||
                digits.length == 6 && getNumber(digits, 5) % 5 == 0 ||
                digits.length == 5 && getNumber(digits, 4) % 3 == 0 ||
                digits.length == 4 && getNumber(digits, 3) % 2 == 0;
    }

    private static void recurse(int[] digits, int umask, long[] result) {
        if (digits.length == 10 && hasProperty(digits)) {
            long r = 0;
            long pow10 = 1;
            for(int i = 9; i > -1; --i) {
                r += digits[i] * pow10;
                pow10 *= 10;
            }
            result[0] += r;
        } else if (digits.length < 4 || hasProperty(digits)) {
            for(int i = 0; i < 10; ++i) {
                if ((umask & (1 << i)) == 0) {
                    int[] newDigits = new int[digits.length + 1];
                    System.arraycopy(digits, 0, newDigits, 0, digits.length);
                    newDigits[digits.length] = i;
                    recurse(newDigits, umask | (1 << i), result);
                }
            }
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        long[] result = new long[1];
        recurse(new int[0], 0, result);
        return result[0];
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
