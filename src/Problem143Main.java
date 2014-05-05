import gnu.trove.TLongArrayList;
import gnu.trove.TLongObjectHashMap;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 2/7/14
 * Time: 9:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem143Main {

    /*
     * Since the angles around T are always 120 degrees, we just use the law of cosines
     *
     */
    
    private static TLongObjectHashMap<TLongArrayList> myMap = new TLongObjectHashMap<>();

    @SuppressWarnings("WeakerAccess")
    public static long solve(long N) {
        long result = 0;
        Set<Long> sums = new HashSet<>();

        // calculate all of the perfect squares of the form a * a + a * b + b * b with a < b
        for(long a = 1; a < N; ++a) {
            for(long b = a + 1; b < N - a; ++b) {
                long n = a * a + a * b + b * b;
                if (EulerUtil.isSquare(n)) {
                    if (!myMap.containsKey(a)) {
                        myMap.put(a, new TLongArrayList());
                    }
                    myMap.get(a).add(b);
                }
            }
        }

        // look for triplets p < q < r where (p, q), (p, r) and (q, r) are in the map calculated above
        for(long p : myMap.keys()) {
            long[] qvalues = myMap.get(p).toNativeArray();
            for (long q : qvalues) {
                if (!myMap.containsKey(q)) {
                    continue;
                }

                long[] rValues = myMap.get(q).toNativeArray();
                for (long r : rValues) {
                    if (p + q + r > N) {
                        break;
                    }
                    if (myMap.get(p).contains(r)) {
                        sums.add(p + q + r);
                    }
                }
            }
        }

        for (long s : sums) {
            result += s;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(120000));
    }
}
