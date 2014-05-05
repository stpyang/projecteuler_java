import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by stpyang on 12/23/13.
 */
public class Problem125Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        long result = 0;
        SortedSet<Long> solutions = new TreeSet<>();

        long[] sumOfSquares = new long[(int)Math.floor(Math.sqrt(N)) + 1];
        long sum = 0;
        for (long i = 0; i < sumOfSquares.length; ++i) {
            sumOfSquares[(int)i] = sum;
            sum += (i + 1) * (i + 1);
        }

        for (int i = 0; i < sumOfSquares.length; ++i) {
            for (int j = i + 2; j < sumOfSquares.length; ++j) {
                long diff =  sumOfSquares[j] - sumOfSquares[i];
                if (diff > N) {
                } else if (EulerUtil.isPalindrome(diff)) {
                    solutions.add(diff);
                }
            }
        }

        for (long s : solutions) {
            result += s;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(100000000));
    }
}
