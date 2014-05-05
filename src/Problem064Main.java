import gnu.trove.TIntObjectHashMap;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 11/29/13
 * Time: 12:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem064Main {

    private static final TIntObjectHashMap<List<EulerUtil.Triplet<Long>>> getContinuedFractionExpansionMap = new TIntObjectHashMap<>();

    private static Long[] calculateNextCoefficients(final int N, Long[] abc) {
//        Long a = abc[0];
        Long b = abc[1];
        Long c = abc[2];
        Long[] result = new Long[3];
        result[0] = Math.round(Math.floor(c / (Math.sqrt(N) - b)));
        result[1] = - b + result[0] * N / c - result[0] * b * b / c;
        result[2] = (N - b * b) / c;
        return result;
    }

    static List<EulerUtil.Triplet<Long>> getContinuedFractionExpansion(final int N) {
        if (getContinuedFractionExpansionMap.containsKey(N)) {
            return getContinuedFractionExpansionMap.get(N);
        } else {
            List<EulerUtil.Triplet<Long>> list = new LinkedList<>();
            Long[] abc = new Long[] { 0L, 0L, (long)N };

            while (true) {
                abc = calculateNextCoefficients(N, abc);
                EulerUtil.Triplet<Long> newABC = new EulerUtil.Triplet<>(abc);

                if (list.contains(newABC)) {
                    list.add(newABC);

                    getContinuedFractionExpansionMap.put(N, list);
                    return list;
                } else {
                    list.add(newABC);
                }
            }
        }
    }

    static int getPeriodSquareRoot(final int N) {
        List<EulerUtil.Triplet<Long>> expansion = getContinuedFractionExpansion(N);

        Iterator<EulerUtil.Triplet<Long>> it = expansion.iterator();
        EulerUtil.Triplet lastValue = expansion.get(expansion.size() - 1);
        int i = 0;
        while (it.hasNext()) {
            EulerUtil.Triplet value = it.next();
            if (value.equals(lastValue)) {
                break;
            } else {
                ++i;
            }
        }

        return (expansion.size() - i - 1);
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        int count = 0;
        for (int i = 2; i <= 10000; ++i) {
            if (!EulerUtil.isSquare(i)) {
                int periodSquareRoot = getPeriodSquareRoot(i);
                count += (periodSquareRoot & 1);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
