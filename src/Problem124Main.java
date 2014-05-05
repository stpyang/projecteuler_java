import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by stpyang on 12/27/13.
 */
public class Problem124Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N, int M) {
        int[] radical = new int[M + 1];
        Arrays.fill(radical, 1);
        radical[0] = 0;
        for(int i = 2; i <= M; ++i) {
            if (radical[i] == 1) {
                int j = i;
                while (j <= M) {
                    radical[j] *= i;
                    j += i;
                }
            }
        }
        List<Solution> sortedRadicals = new ArrayList<>();
        for(int i = 0; i <=M; ++i) {
            sortedRadicals.add(new Solution(radical[i], i));
        }
        Collections.sort(sortedRadicals);
        return sortedRadicals.get(N).b;
    }

    public static void main(String[] args) {
        System.out.println(solve(10000, 100000));
    }

    static class Solution extends Pair<Integer> implements Comparable<Solution> {

            public Solution(int radical, int n) {
                super(radical, n);
            }

        @Override
        public int compareTo(Solution o) {
            if (a.compareTo(o.a) != 0) {
                return a.compareTo(o.a);
            } else {
                return b.compareTo(o.b);
            }
        }
    }
}
