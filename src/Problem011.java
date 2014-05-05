import gnu.trove.TIntArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/13/13
 * Time: 4:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem011 implements MySolution {

    private final int N;

    public Problem011(int N) {
        this.N = N;
    }

    // here we use the Product class from Problem008 to keep track of a running buffer of integers, as well as their
    // product to find the maximal horizontal, vertical, and diagonal product we iterate through the array with
    // different step sizes, i.e. stepsize = 1 results in horizontal products, stepSize = n results in vertical
    // products, and stepsize = n +/- 1 results in digonal products one quick hack is to pad the input with an extra,
    // row/column of zeros so that wrap-around products do not appear as false solutions

    private static final String[] input = new String[] {
            "08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08",
            "49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00",
            "81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65",
            "52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91",
            "22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80",
            "24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50",
            "32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70",
            "67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21",
            "24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72",
            "21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95",
            "78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92",
            "16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57",
            "86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58",
            "19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40",
            "04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66",
            "88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69",
            "04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36",
            "20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16",
            "20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54",
            "01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48",
    };

    private static int[] getArray() {
        TIntArrayList result = new TIntArrayList();
        for (String row : input) {
            String[] entries = row.split(" ");
            for (String entry : entries) {
                result.add(Integer.parseInt(entry));
            }
            result.add(0);
        }
        result.add(new int[input.length + 1]);
        return result.toNativeArray();
    }

    private static long getMaxProduct(int[] array, TIntArrayList initialValues, int i, int step, int modulus) {
        long result = EulerUtil.product(initialValues.toNativeArray());
        Problem008.Product product = new Problem008.Product(initialValues.toNativeArray());
        while (i != 0) {
            result = Math.max(result, product.getNextProduct(array[i]));
            i += step;
            i %= modulus;
        }
        return result;
    }

    @Override
    public long solve() {
        int matrixLength = input.length;
        if (matrixLength < N) {
            throw new IllegalArgumentException("N must be smaller than the size of the input!");
        }

        int[] array = getArray();

        // max horizonal product
        int i = 0;
        TIntArrayList initialHorizontalValues = new TIntArrayList();
        while (i < N) {
            initialHorizontalValues.add(array[i]);
            ++i;
        }
        long maxHorizontalProduct = getMaxProduct(array, initialHorizontalValues, i, 1, array.length - 1);

        // max vertical product
        i = 0;
        TIntArrayList initialVerticalValues = new TIntArrayList();
        while (i < N * (matrixLength + 1)) {
            initialVerticalValues.add(array[i]);
            i += (matrixLength + 1);
        }
        long maxVerticalProduct = getMaxProduct(array, initialHorizontalValues, i, matrixLength + 1, array.length - 1);

        // max diagonal product 1
        i = 0;
        TIntArrayList initialDiagonalProduct1Values = new TIntArrayList();
        while (i < N * (matrixLength + 2)) {
            initialDiagonalProduct1Values.add(array[i]);
            i += (matrixLength + 2);
        }
        long maxDiagonalProduct1 = getMaxProduct(array, initialDiagonalProduct1Values, i, matrixLength + 2, array.length);

        // max diagonal product 2
        i = 0;
        TIntArrayList initialDiagonalProduct2Values = new TIntArrayList();
        while (i < N * (matrixLength)) {
            initialDiagonalProduct2Values.add(array[i]);
            i += matrixLength;
        }
        long maxDiagonalProduct2 = getMaxProduct(array, initialDiagonalProduct2Values, i, matrixLength, array.length);

        long result = Math.max(maxHorizontalProduct, maxVerticalProduct);
        result = Math.max(result, maxDiagonalProduct1);
        result = Math.max(result, maxDiagonalProduct2);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Problem011(4).solve());
    }
}
