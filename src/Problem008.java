import gnu.trove.TIntArrayList;
import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/13/13
 * Time: 1:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem008 implements MySolution {

    private final int N;

    public Problem008(int N) {
        this.N = N;
    }

    public static class Product {
        // a class which keeps track of the rolling product by keeping track of the entires which are pushed in and
        // popped out of a buffer.

        private final Deque<Integer> entries = new ArrayDeque<>();
        private long product = 1;
        private int numberOfZeros = 0;

        Product(int[] initialEntries) {
            for (int e : initialEntries) {
                entries.addFirst(e);
                if (e == 0) ++numberOfZeros;
                else product *= e;
            }
        }

        long getNextProduct(int add) {
            entries.addFirst(add);
            if (add == 0) ++numberOfZeros;
            else product *= add;

            int drop = entries.removeLast();
            if (drop == 0) --numberOfZeros;
            else product /= drop;

            if (numberOfZeros > 0) return 0;
            else return product;
        }
    }

    private static final String[] INPUT = new String[] {
            "73167176531330624919225119674426574742355349194934",
            "96983520312774506326239578318016984801869478851843",
            "85861560789112949495459501737958331952853208805511",
            "12540698747158523863050715693290963295227443043557",
            "66896648950445244523161731856403098711121722383113",
            "62229893423380308135336276614282806444486645238749",
            "30358907296290491560440772390713810515859307960866",
            "70172427121883998797908792274921901699720888093776",
            "65727333001053367881220235421809751254540594752243",
            "52584907711670556013604839586446706324415722155397",
            "53697817977846174064955149290862569321978468622482",
            "83972241375657056057490261407972968652414535100474",
            "82166370484403199890008895243450658541227588666881",
            "16427171479924442928230863465674813919123162824586",
            "17866458359124566529476545682848912883142607690042",
            "24219022671055626321111109370544217506941658960408",
            "07198403850962455444362981230987879927244284909188",
            "84580156166097919133875499200524063689912560717606",
            "05886116467109405077541002256983155200055935729725",
            "71636269561882670428252483600823257530420752963450",
    };

    private static int[] getArray(String[] input) {
        TIntArrayList result = new TIntArrayList();
        for (String s : input) {
            for (char c : s.toCharArray()) {
                result.add(Integer.parseInt(Character.toString(c)));
            }
        }
        return result.toNativeArray();
    }

    @Override
    public long solve() {
        long result = 1;

        int[] array = getArray(INPUT);

        Product product = new Product(ArrayUtils.subarray(array, 0, N));

        for (int i = N; i < array.length; ++i)
            result = Math.max(result, product.getNextProduct(array[i]));

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Problem008(5).solve());
    }
}
