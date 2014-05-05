import gnu.trove.TIntArrayList;
import gnu.trove.TIntObjectHashMap;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/17/14
 * Time: 2:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem127Main {

    private static final TIntObjectHashMap<TIntArrayList> radicalIntMap = new TIntObjectHashMap<>();

    public static long solve(int N) {
        long result = 0;

        int[] radical = new int[N + 1];
        Arrays.fill(radical, 1);

        for (int i = 1; i < radical.length; ++i) {
            if (radical[i] == 1) {
                for (int j = i; j < radical.length; j += i) {
                    radical[j] *= i;
                }
            }
        }

        for(int i = 0; i < radical.length; ++i) {
            radicalIntMap.put(i, new TIntArrayList());
            if (i > 0) {
                radicalIntMap.get(radical[i]).add(i);
            }
        }

        for (int c = 1; c < N; ++c) {
            for (int radicalA = 1; radicalA * radical[c] < c; ++radicalA) {
                if (radicalA == radical[c]) {
                    continue;
                }
                for (int a : radicalIntMap.get(radicalA).toNativeArray()) {
                    for (int radicalB = 1; radicalA * radicalB * radical[c] < c; ++radicalB) {
                        if (radicalB == radicalA || radicalB == radical[c]) {
                            continue;
                        }
                        for (int b : radicalIntMap.get(radicalB).toNativeArray()) {
                            if (a < b && a + b == c && EulerUtil.gcd(a, c) == 1) {
                                result += c;
                            }
                        }
                    }
                }
            }
        }

        /*
        for(int c = 1; c <= N; ++c) {
            for (int a = 1; a < c / 2; ++a) {
                int b = c - a;
                // gcd(a, c) == gcd (a, b) = gcd(b, c)
                if (radical[a] * radical[b] * radical[c] < c) {
                    if (EulerUtil.gcd(a, c) == 1) {
                        result += c;
                    System.out.println(a + " " + b + " " + c);
                    }
                }
            }
        }
        */


        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(120000));
    }
}
