import gnu.trove.TLongIntHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/16/13
 * Time: 5:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem014 implements MySolution {

    private final int N;

    public Problem014(int N) {
        this.N = N;
    }

    private static final TLongIntHashMap chainLengthMap = new TLongIntHashMap();
    static {
        chainLengthMap.put(1, 1);
    }

    static int chainLength(long n) {
        if (chainLengthMap.containsKey(n)) return chainLengthMap.get(n);
        else if (n % 2 == 0) {
            int r = 1 + chainLength(n / 2);
            chainLengthMap.put(n, r);
            return r;
        } else {
            int r = 1 + chainLength(3 * n + 1);
            chainLengthMap.put(n, r);
            return r;

        }
    }

    @Override
    public long solve() {
        int result = 0;
        int maxChainLength = 0;
        int c;
        for (int i = 2; i <= N; ++i)  {
            if ((c = chainLength(i)) > maxChainLength) {
                result = i;
                maxChainLength = c;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Problem014(1000000).solve());
    }
}
