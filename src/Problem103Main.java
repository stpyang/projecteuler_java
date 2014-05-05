import gnu.trove.TIntArrayList;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/15/13
 * Time: 3:31 AM
 * To change this template use File | Settings | File Templates.
 */                                                       /**/
public class Problem103Main {

    private static void _getSubsetSums(int[] A, int k, Set<Integer> result, TIntArrayList S, int level) {
        if (S.size() >= k) {
            result.add((int)EulerUtil.sum(S.toNativeArray()));
        } else {
            for (int l = level; l < A.length; ++l) {
                TIntArrayList S_clone = new TIntArrayList(S.toNativeArray());
                S_clone.add(A[l]);
                _getSubsetSums(A, k, result, S_clone, l + 1);
            }
        }
    }

    public static Set<Integer> getSubsetSums(int[] A, int k){
        Set<Integer> result = new HashSet<>();
        if (k == 0) {
            result.add(0);
        } else {
            _getSubsetSums(A, k, result, new TIntArrayList(), 0);
        }
        return result;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        long minSum = Long.MAX_VALUE;
        StringBuilder sbr = new StringBuilder();

        for (int sum = 255; sum >= 0; --sum) {
            for (int a1 = 1; a1 < 33; ++a1) {
                for (int a2 = a1 + 1; a2 < 34; ++a2) {
                    for (int a7 = a2 + 5; a7 < a1 + a2; ++a7) {
                        for (int a3 = a2 + 1; a3 < a7; ++a3) {
                            for (int a6 = a3 + 3; a3 < a6 && a6 < a1 + a2 + a3 - a7 && a6 < a7; ++a6) {
                                for (int a4 = a3 + 1; a4 < a6; ++a4) {
                                    int a5 = sum - a1 - a2 - a3 - a4 - a6 - a7;
                                    if (a4 >= a5 || a5 >= a6 || a1 + a2 + a3 + a4 == a5 + a6 + a7) {
                                        continue;
                                    }
                                    int[] A = new int[] { a1, a2, a3, a4, a5, a6, a7 };

                                    if (getSubsetSums(A, 2).size() == 21 && getSubsetSums(A, 3).size() == 35) {
                                        long Asum = EulerUtil.sum(A);
                                        if (Asum < minSum) {
                                            minSum = Asum;
                                            for (int a : A) {
                                                sbr.append(a);
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
        return Long.parseLong(sbr.toString());
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
