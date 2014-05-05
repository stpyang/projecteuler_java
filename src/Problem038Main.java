import gnu.trove.TIntArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/6/14
 * Time: 4:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem038Main {

    static long calculatePandigitalConcatenatedProduct(int n) {
        int totalNumberOfDigits = 0;
        int i = 1;
        int umask = 0;

        TIntArrayList result = new TIntArrayList();

        while (totalNumberOfDigits < 9) {
            int j = i * n;
            if (j == 0) {
                return -1;
            }

            totalNumberOfDigits += EulerUtil.getNumberOfDigits(j);
            int pow10 = (int)EulerUtil.longPow(10, EulerUtil.getNumberOfDigits(j) - 1);
            while (j > 0) {
                int k = j / pow10;
                if (k == 0) {
                    return -1;
                }
                result.add(k);
                if (((umask >> k) & 1) == 1) {
                    return -1;
                } else {
                    umask |= (1 << k);
                }
                j = j % pow10;
                pow10 /= 10;
            }
            ++i;
        }
        if (totalNumberOfDigits != 9) {
            return -1;
        }
        return getLong(result.toNativeArray());
    }

    private static long getLong(int[] digits) {
        long result = 0;
        long pow10 = 1;
        for (int i = digits.length - 1; i > -1; --i) {
            result += digits[i] * pow10;
            pow10 *= 10;
        }
        return result;
    }

    private static void recursiveTestDigits(int[] digits, long[] result) {
        int n = (int)getLong(digits);

        long pandigitalConcatenatedProduct = calculatePandigitalConcatenatedProduct(n);

        if (pandigitalConcatenatedProduct > result[0]) {
            result[0] = pandigitalConcatenatedProduct;
        }

        if (digits.length == 5) {
        } else {
            for (int d = 0; d < 10; ++d) {
                int[] newDigits = new int[digits.length + 1];
                System.arraycopy(digits, 0, newDigits, 0, digits.length);
                newDigits[digits.length] = d;

                recursiveTestDigits(newDigits, result);
            }
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        long[] result = new long[1];
        result[0] = 918273645;

        recursiveTestDigits(new int[] { 9 }, result);

        return result[0];
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
