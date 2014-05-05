import gnu.trove.TIntIntHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/22/13
 * Time: 10:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem118Main {

//    private static final TIntObjectHashMap<TIntArrayList> umaskMap = new TIntObjectHashMap<>();
    private static final TIntIntHashMap umaskPrimesMap = new TIntIntHashMap();

    private static int getUMask(int n) {
        int umask = 0;
        while (n > 0) {
            if ((umask & (1 << n % 10)) != 0) {
                return -1;
            }
            umask |= (1 << n % 10);
            n /= 10;
        }
        return umask;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        long result = 0;
        // no need to check 9-digits numbers since they will be divisible by 3
        PrimeSieve primeSieve = new PrimeSieve(100000000);

        int umask;
        for (int i = 2; i < 100000000; ++i) {
            if (!primeSieve.isPrime(i) || (umask = getUMask(i))== -1) {
                continue;
            }
            if (!umaskPrimesMap.containsKey(umask)) {
                umaskPrimesMap.put(umask, 0);
            }
            umaskPrimesMap.put(umask, umaskPrimesMap.get(umask) + 1);
        }

//        for(int key : umaskMap.keys()) {
//            System.out.println(key + " " + umaskMap.get(key));
//        }

        int[] umasks = umaskPrimesMap.keys();

        // there are no more than six prime numbers in a solution
        // loop over i, j, k, l, m, n
        for (int i = 0; i < umasks.length; ++i) {
            for (int j = i + 1; j < umasks.length; ++j) {
                if ((umasks[i] & umasks[j]) != 0) {
                    continue;
                }
                if (umasks[i] + umasks[j] == 1022) {
                    result += umaskPrimesMap.get(umasks[i]) *  umaskPrimesMap.get(umasks[j]);
                }

                for(int k = j + 1; k < umasks.length; ++k) {
                    if ((umasks[i] & umasks[k]) != 0 || (umasks[j] & umasks[k]) != 0) {
                        continue;
                    }
                    if (umasks[i] + umasks[j] + umasks[k] == 1022) {
                        result += umaskPrimesMap.get(umasks[i]) *  umaskPrimesMap.get(umasks[j]) * umaskPrimesMap.get(umasks[k]);
                    }

                    for(int l = k + 1; l < umasks.length; ++l) {
                        if ((umasks[i] & umasks[l]) != 0 || (umasks[j] & umasks[l]) != 0 || (umasks[k] & umasks[l]) != 0) {
                            continue;
                        }
                        if (umasks[i] + umasks[j] + umasks[k] + umasks[l] == 1022) {
                            result += umaskPrimesMap.get(umasks[i]) *  umaskPrimesMap.get(umasks[j]) * umaskPrimesMap.get(umasks[k]) * umaskPrimesMap.get(umasks[l]);
                        }

                        for(int m = l + 1; m < umasks.length; ++m) {
                            if ((umasks[i] & umasks[m]) != 0 || (umasks[j] & umasks[m]) != 0 || (umasks[k] & umasks[m]) != 0 || (umasks[l] & umasks[m]) != 0) {
                                continue;
                            }
                            if (umasks[i] + umasks[j] + umasks[k] + umasks[l] + umasks[m] == 1022) {
                                result += umaskPrimesMap.get(umasks[i]) *  umaskPrimesMap.get(umasks[j]) * umaskPrimesMap.get(umasks[k]) * umaskPrimesMap.get(umasks[l]) * umaskPrimesMap.get(umasks[m]);
                            }

                            for(int n = m + 1; n < umasks.length; ++n) {
                                if ((umasks[i] & umasks[n]) != 0 || (umasks[j] & umasks[n]) != 0 || (umasks[k] & umasks[n]) != 0 || (umasks[l] & umasks[n]) != 0 || (umasks[m] & umasks[n]) != 0) {
                                    continue;
                                }
                                if (umasks[i] + umasks[j] + umasks[k] + umasks[l] + umasks[m] + umasks[n] == 1022) {
                                    result += umaskPrimesMap.get(umasks[i]) *  umaskPrimesMap.get(umasks[j]) * umaskPrimesMap.get(umasks[k]) * umaskPrimesMap.get(umasks[l]) * umaskPrimesMap.get(umasks[m]) * umaskPrimesMap.get(umasks[n]);
                                }
                            }

                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
