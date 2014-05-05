import gnu.trove.TIntLongHashMap;
import gnu.trove.TIntObjectHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/20/13
 * Time: 9:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem114Main {

    private static final TIntObjectHashMap<TIntLongHashMap> fillCountMap = new TIntObjectHashMap<>();
    private static final TIntObjectHashMap<TIntLongHashMap> redMap = new TIntObjectHashMap<>();
    private static final TIntObjectHashMap<TIntLongHashMap> blackMap = new TIntObjectHashMap<>();

    static void initializeMaps(int m) {
        fillCountMap.put(m, new TIntLongHashMap());
        redMap.put(m, new TIntLongHashMap());
        blackMap.put(m, new TIntLongHashMap());
    }

    static long fillCount(int m, int numBlocks) {
        if (fillCountMap.containsKey(numBlocks)) {
            return fillCountMap.get(m).get(numBlocks);
        }
        long result = red(m, numBlocks) + black(m, numBlocks);
        fillCountMap.get(m).put(numBlocks, result);
        return result;
    }

    private static long red(int m, int numBlocks) {
        if (numBlocks < m) {
            return 0;
        } else if (numBlocks == m) {
            return 1;
        } else if (redMap.get(m).containsKey(numBlocks)) {
            return redMap.get(m).get(numBlocks);
        } else if (numBlocks < 0) {
            return 1;
        } else {
            long result = 0;
            for (int i = m; i <= numBlocks; ++i) {
                result += black(m, numBlocks - i);
            }
            redMap.get(m).put(numBlocks, result);
            return result;
        }
    }

    private static long black(int m, int numBlocks) {
        if (numBlocks < m) {
            return 1;
        } else if (blackMap.get(m).containsKey(numBlocks)) {
            return blackMap.get(m).get(numBlocks);
        } else {
            long result = 1;
            for (int i = 1; i <= numBlocks - m; ++i) {
                result += red(m, numBlocks - i);
            }
            blackMap.get(m).put(numBlocks, result);
            return result;
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int m) {
        initializeMaps(3);

        return fillCount(3, m);
    }

    public static void main(String[] args) {
        System.out.println(solve(50));
    }
}
