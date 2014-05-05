import gnu.trove.TIntArrayList;
import gnu.trove.TIntObjectHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/22/14
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem142Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        TIntObjectHashMap<TIntArrayList> pairMap = new TIntObjectHashMap<>();
        int i = 0;
        while (true) {
            int sumSquare = i * i;
            for (int a = 1; a < sumSquare; ++a) {
                int b = sumSquare - a;
                if (b - a < 0) {
                    break;
                } else if (EulerUtil.isSquare(b - a)) {
                    if (pairMap.containsKey(a)) {
                        for (int c : pairMap.get(a).toNativeArray()) {
                            if (pairMap.containsKey(b) && pairMap.get(b).contains(c)) {
                                return a + b + c;
                            }
                        }
                    }
                    if (!pairMap.containsKey(b)) {
                        pairMap.put(b, new TIntArrayList());
                    }
                    pairMap.get(b).add(a);
                }
            }
            ++i;
        }
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
