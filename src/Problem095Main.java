import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/10/13
 * Time: 4:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem095Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        int result = 0;
        int[] sumOfDivisors = EulerUtil.getSumsOfDivisorsUpTo(N);

        int maxChain = 0;
        for (int i = 2; i <= N; ++i) {
            Set<Integer> inChain = new HashSet<>();
            int j = i;
            int chainLength = 0;
            while (i <= j && j <= N && !inChain.contains(j)) {
                inChain.add(j);
                j = sumOfDivisors[j];
                ++chainLength;
            }
            if (j == i) {
                if (chainLength > maxChain) {
                    maxChain = chainLength;
                    result = i;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(1000000));
    }
}
