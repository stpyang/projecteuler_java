import gnu.trove.TIntArrayList;
import gnu.trove.TIntObjectHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/16/14
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem049Main {

    private static int getDigits(int n) {
        int result = 0;
        while (n > 0) {
            result += EulerUtil.longPow(10, n % 10);
            n /= 10;
        }
        return result;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        PrimeSieve primeSieve = new PrimeSieve(10000);
        TIntObjectHashMap<TIntArrayList> digitsPrimeMap = new TIntObjectHashMap<>();

        for (int p = 2; p < 10000; ++p) {
            if (primeSieve.isPrime(p)) {
                int digits = getDigits(p);
                if (!digitsPrimeMap.containsKey(digits)) {
                    digitsPrimeMap.put(digits, new TIntArrayList());
                }
                digitsPrimeMap.get(digits).add(p);
            }
        }

        int size;
        for (Object p : digitsPrimeMap.getValues()) {
            TIntArrayList primeList = (TIntArrayList)p;
            primeList.sort();
            if ((size = primeList.size()) > 2) {
                for (int i = 0; i < size; ++i) {
                    for (int j = i + 1; j < size; ++j) {
                        int k = 2 * primeList.get(j) - primeList.get(i);
                        if (k < 10000 && k != 8147) {
                            if (primeList.contains(2 * primeList.get(j) - primeList.get(i))) {
                                return 100000000L*primeList.get(i) + 10000L*primeList.get(j) + k;
                            }
                        } else {
                            break;
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
