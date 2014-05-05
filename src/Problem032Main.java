import gnu.trove.TIntHashSet;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/21/13
 * Time: 10:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem032Main {

    static boolean isPandigitalWithProduct(int i, int j) {
        int mask = 0;
        int k = i * j;
        while (i > 0) {
            mask |= 1 << (i % 10);
            i /= 10;
        }
        while (j > 0) {
            mask |= 1 << (j % 10);
            j /= 10;
        }
        while (k > 0) {
            mask |= 1 << (k % 10);
            k /= 10;
        }
        return mask == 1022;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        // pandigital products can only be of the form:
        // (1 digit) x (4 digit) = (4 digit)
        // (2 digit) x (3 digit) = (4 digit)
        TIntHashSet products = new TIntHashSet();

        for (int i = 0; i < 10; ++i) {
            for(int j = 1000; j < 10000; ++j) {
                if (i * j > 9999) {
                    continue;
                }
                if (isPandigitalWithProduct(i, j)) {
                    products.add(i * j);
                }
            }
        }

        for (int i = 10; i < 100; ++i) {
            for (int j = 100; j < 1000; ++j) {
                if (i * j > 9999) {
                    continue;
                }
                if (isPandigitalWithProduct(i, j)) {
                    products.add(i * j);
                }
            }
        }

        return EulerUtil.sum(products.toArray());
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
