import gnu.trove.TIntArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/17/13
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem106Main {

    private static boolean isDisjoint(int i, int j) {
        return ((i & j) == 0);
    }

    private static int minIndex(int i) {
        int result = 0;
        while ((i & 1) == 0) {
            i = i >> 1;
            ++result;
        }
        return result;
    }

    private static int maxIndex(int i) {
        int result = -1;
        while (i != 0) {
            i = i >> 1;
            ++result;
        }
        return result;
    }

    private static int numberOfElements(int i) {
        int result = 0;
        while (i != 0) {
            result += (i & 1);
            i = i >> 1;
        }
        return result;
    }

    private static int[] elements(int i) {
        TIntArrayList result = new TIntArrayList();
        int index = 0;
        while (i != 0) {
            if ((i & 1) == 1) {
                result.add(index);
            }
            i = i >> 1;
            ++index;
        }
        return result.toNativeArray();
    }

    public static long solve(int N) {
        int result = 0;
        int upperLimit = (int)EulerUtil.longPow(2, N);
        for (int i = upperLimit - 1; i > 0; --i) {
            for (int j = i - 1; j > 0; --j) {
                int iElements = numberOfElements(i);
                int jElements = numberOfElements(j);

                if (iElements > 1 && jElements > 1 && iElements == jElements && isDisjoint(i, j) && (maxIndex(i) > minIndex(j))) {
                    int[] elementsI = elements(i);
                    int[] elementsJ = elements(j);
                    boolean mustTest = false;
                    for (int index = 0; !mustTest && index < iElements; ++index) {
                        if (elementsI[index] < elementsJ[index]) {
                            mustTest = true;
                        }
                    }
                    if (mustTest) {
                        ++result;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(12));
    }
}
