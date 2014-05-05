import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/6/13
 * Time: 4:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem088Main {

    private static int product(int[] digits, int l) {
        int result = 1;
        for (int i = 0; i < l; ++i) {
            result *= digits[i];
        }
        return result;
    }

    private static int product(int[] digits) {
        return product(digits, digits.length);
    }

    private static int sum(int[] digits, int l) {
        int result = 0;
        for (int i = 0; i < l; ++i) {
            result += digits[i];
        }
        return result;
    }

    private static int sum(int[] digits) {
        return sum(digits, digits.length);
    }

    private static void _getFactors(int minFactor, int maxProduct, int[] factors, int level, List<int[]> result) {
        if (level >= factors.length) {
            result.add(factors);
        } else if (product(factors, level) < maxProduct){
            int i = (level == 0 ? minFactor : factors[level - 1]);
            int p = 0;
            while (p <= maxProduct) {
                int[] temp = factors.clone();
                temp[level] = i;
                p = product(temp, level + 1);
                if (p <= maxProduct) {
                    _getFactors(minFactor, maxProduct, temp, level + 1, result);
                }
                ++i;
            }
        }
    }

    private static List<int[]> getFactors(int minFactor, int maxProduct, int numberOfFactors) {
        int[] seed = new int[numberOfFactors];
        List<int[]> result = new ArrayList<>();
        _getFactors(minFactor, maxProduct, seed, 0, result);
        return result;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int minValue, int N) {
        int result = 0;

        int[] minimumProduct = new int[1 + N];
        for (int i = 0; i < N; ++i) {
            minimumProduct[i] = Integer.MAX_VALUE;
        }
        for (int i = minValue; i <= 1 + Math.log(2 * N) / Math.log(minValue); ++i) {
            for (int[] factors : getFactors(minValue, 2 * N, i)) {
                int k = product(factors) - sum(factors) + i ;
                if (k <= N && product(factors) < minimumProduct[k]) {
                    minimumProduct[k] = product(factors);
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int m : minimumProduct) {
            if (m != Integer.MAX_VALUE) {
                set.add(m);
            }
        }

        for (int s : set) {
            result += s;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(2, 12000));
    }
}
