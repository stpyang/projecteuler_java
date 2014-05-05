import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 11/29/13
 * Time: 12:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem068Main {

    static class MagicNGonGenerator {
        final int N;
        final int[] internalStructure;
        final boolean[] usedEntries;

        MagicNGonGenerator(int N) {
            this.N = N;
            internalStructure = new int[3 * N];
            usedEntries = new boolean[2 * N + 1];
        }

        MagicNGonGenerator(int N, int[] internalStructure, boolean[] usedEntries) {
            this.N = N;
            this.internalStructure = internalStructure.clone();
            this.usedEntries = usedEntries.clone();
        }

        public boolean seed(int[] seed) {
            if (seed.length != 3) {
                throw new IllegalArgumentException("seed must have exactly 3 entries in it");
            }
            return (setEntry(0, seed[0]) && setEntry(1, seed[1]) && setEntry(2, seed[2]));
        }        

        public void generate(List<int[]> result, int place) {
            if (place >= 3 * N) {
                int sum = getSum(0);
                boolean goodNGon = true;
                for (int i = 1; i < N; ++i) {
                    if (sum != getSum(3 * i)) {
                        goodNGon = false;
                        break;
                    }
                    if (internalStructure[0] > internalStructure[3 * i]) {
                        goodNGon = false;
                        break;
                    }
                }
                if (goodNGon) {
                    result.add(internalStructure.clone());
                }
            } else if (internalStructure[place] > 0) {
                generate(result, ++place);
            } else {
                List<Integer> temp = new ArrayList<>();
                for (int i = 1; i < usedEntries.length; ++i) {
                    if (!usedEntries[i]) {
                        temp.add(i);
                    }
                }
                for (int entry : temp) {
                    MagicNGonGenerator fiveGonGenerator = new MagicNGonGenerator(N, internalStructure, usedEntries);
                    if (fiveGonGenerator.setEntry(place, entry)) {
                        fiveGonGenerator.generate(result, place + 1);
                    }
                }
            }
        }

        private int getSum(int place) {
            int i = 3 * (place / 3);
            return internalStructure[i] + internalStructure[i + 1] + internalStructure[i + 2];
        }

        private boolean setEntry(int place, int entry) {
            if (usedEntries[entry]) {
                return false;
            }
            if (internalStructure[place] != 0) {
                return false;
            }
            internalStructure[place] = entry;
            usedEntries[entry] = true;
            if (place % 3 == 0) {
                return true;
            } else if (place % 3 == 1) {
                place = place - 2;
            } else if (place % 3 == 2) {
                place = place + 2;
            }
            if (place == -1) {
                place = 3*N - 1;
            }
            if (place == 3 * N + 1) {
                place = 1;
            }
            if (internalStructure[place] != 0) {
                return false;
            }
            internalStructure[place] = entry;
            return true;
        }

        @Override
        public String toString() {
            return Arrays.toString(internalStructure);
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(final int N) {
        long result = 0;

        for(int i = 2 * N; i > 0; --i) {
            for (int j = 2 * N; j > 0; --j) {
                for (int k = 2 * N; k > 0; --k) {
                    if (i == j || i == k || j == k) {
                        continue;
                    }

                    int[] seed = new int[] { i, j, k };
                    MagicNGonGenerator fiveGon = new MagicNGonGenerator(N);
                    if (!fiveGon.seed(seed)) {
                        continue;
                    }
                    List<int[]> magicFiveGon = new ArrayList<>();
                    fiveGon.generate(magicFiveGon, 3);
                    for (int[] entry : magicFiveGon) {
                        ArrayUtils.reverse(entry);
                        long number = EulerUtil.getLongFromDigits(entry);
                        if (EulerUtil.getNumberOfDigits(number) <= 16) {
                            result = (number > result ? number : result);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(5));
    }
}

