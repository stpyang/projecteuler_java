import gnu.trove.TIntObjectHashMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 11/29/13
 * Time: 11:15 AM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("UnnecessaryLocalVariable")
public class Problem061Main {

    // todo: redo this horrible solution

    private static final TIntObjectHashMap<List<Integer>> trianglePairs = new TIntObjectHashMap<>();
    private static final TIntObjectHashMap<List<Integer>> squarePairs = new TIntObjectHashMap<>();
    private static final TIntObjectHashMap<List<Integer>> pentagonalPairs = new TIntObjectHashMap<>();
    private static final TIntObjectHashMap<List<Integer>> hexagonalPairs = new TIntObjectHashMap<>();
    private static final TIntObjectHashMap<List<Integer>> heptagonalPairs = new TIntObjectHashMap<>();
    private static final TIntObjectHashMap<List<Integer>> octogonalPairs = new TIntObjectHashMap<>();

    private static void generatePairs() {
        int n;
        int m;

        n = 1;
        while ((m = (int)EulerUtil.getGeometricNumber(n, 3)) < 10000) {
            int firstDigits = m / 100;
            int lastDigits = m % 100;

            if (!trianglePairs.containsKey(firstDigits)) {
                trianglePairs.put(firstDigits, new ArrayList<Integer>());
            }
            trianglePairs.get(firstDigits).add(lastDigits);
            ++n;
        }

        n = 1;
        while ((m = (int)EulerUtil.getGeometricNumber(n, 4)) < 10000) {
            int firstDigits = m / 100;
            int lastDigits = m % 100;

            if (!squarePairs.containsKey(firstDigits)) {
                squarePairs.put(firstDigits, new ArrayList<Integer>());
            }
            squarePairs.get(firstDigits).add(lastDigits);
            ++n;
        }

        n = 1;
        while ((m = (int)EulerUtil.getGeometricNumber(n, 5)) < 10000) {
            int firstDigits = m / 100;
            int lastDigits = m % 100;

            if (!pentagonalPairs.containsKey(firstDigits)) {
                pentagonalPairs.put(firstDigits, new ArrayList<Integer>());
            }
            pentagonalPairs.get(firstDigits).add(lastDigits);
            ++n;
        }

        n = 1;
        while ((m = (int)EulerUtil.getGeometricNumber(n, 6)) < 10000) {
            int firstDigits = m / 100;
            int lastDigits = m % 100;

            if (!hexagonalPairs.containsKey(firstDigits)) {
                hexagonalPairs.put(firstDigits, new ArrayList<Integer>());
            }
            hexagonalPairs.get(firstDigits).add(lastDigits);
            ++n;
        }

        n = 1;
        while ((m = (int)EulerUtil.getGeometricNumber(n, 7)) < 10000) {
            int firstDigits = m / 100;
            int lastDigits = m % 100;

            if (!heptagonalPairs.containsKey(firstDigits)) {
                heptagonalPairs.put(firstDigits, new ArrayList<Integer>());
            }
            heptagonalPairs.get(firstDigits).add(lastDigits);
            ++n;
        }

        n = 1;
        while ((m = (int)EulerUtil.getGeometricNumber(n, 8)) < 10000) {
            int firstDigits = m / 100;
            int lastDigits = m % 100;

            if (!octogonalPairs.containsKey(firstDigits)) {
                octogonalPairs.put(firstDigits, new ArrayList<Integer>());
            }
            octogonalPairs.get(firstDigits).add(lastDigits);
            ++n;
        }

    }

    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        generatePairs();

        List<TIntObjectHashMap<List<Integer>>> pairs = new ArrayList<>();
        pairs.add(trianglePairs);
        pairs.add(squarePairs);
        pairs.add(pentagonalPairs);
        pairs.add(hexagonalPairs);
        pairs.add(heptagonalPairs);
        pairs.add(octogonalPairs);

        for (List<TIntObjectHashMap<List<Integer>>> permutation : EulerUtil.getPermutations(pairs)) {
            TIntObjectHashMap<List<Integer>> pairs0 = permutation.get(0);
            TIntObjectHashMap<List<Integer>> pairs1 = permutation.get(1);
            TIntObjectHashMap<List<Integer>> pairs2 = permutation.get(2);
            TIntObjectHashMap<List<Integer>> pairs3 = permutation.get(3);
            TIntObjectHashMap<List<Integer>> pairs4 = permutation.get(4);
            TIntObjectHashMap<List<Integer>> pairs5 = permutation.get(5);

            for (int key0 : pairs0.keys()) {
                for (int value0 : pairs0.get(key0)) {
                    if (!pairs1.containsKey(value0) || value0 < 10) {
                        continue;
                    }
                    int key1 = value0;
                    for (int value1 : pairs1.get(key1)) {
                        if (!pairs2.containsKey(value1) || value1 < 10) {
                            continue;
                        }
                        int key2 = value1;
                        for (int value2 :  pairs2.get(key2)) {
                            if (!pairs3.containsKey(value2) || value2 < 10) {
                                continue;
                            }
                            int key3 = value2;
                            for (int value3 :  pairs3.get(key3)) {
                                if (!pairs4.containsKey(value3) || value3 < 10) {
                                    continue;
                                }
                                int key4 = value3;
                                for (int value4 :  pairs4.get(key4)) {
                                    if (!pairs5.containsKey(value4) || value4 < 10) {
                                        continue;
                                    }
                                    int key5 = value4;
                                    for (int value5 :  pairs5.get(key5)) {
                                        if (pairs0.containsKey(value5) && value5 >= 10) {
                                            if (value5 == key0) {
                                               return(100 * key0 + value0 + 100 * key1 + value1 + 100 * key2 + value2 + 100 * key3 + value3 + 100 * key4 + value4  + 100 * key5 + value5);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
