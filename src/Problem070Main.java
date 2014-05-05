/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/2/13
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem070Main {

    static boolean isPermutationOf(long i, long j) {
        int[] iCount = new int[10];
        int[] jCount = new int[10];
        while (i > 0) {
            iCount[(int)(i % 10)]++;
            i /= 10;
        }
        while (j > 0) {
            jCount[(int)(j % 10)]++;
            j /= 10;
        }
        for (int k = 0; k < 10; ++k) {
            if (iCount[k] != jCount[k]) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        long result = 0;
        double minRatio = Double.MAX_VALUE;
        PrimeSieve primeSieve = new PrimeSieve(N);

        for (int p = (int)Math.floor(Math.sqrt(N)); p > 1; --p) {
            if (!primeSieve.isPrime(p)) {
                continue;
            }
            for (int q = N / p; q > 1; --q) {
                if (!primeSieve.isPrime(q)) {
                    continue;
                }

                int n = p * q;
                long t = (p - 1) * (q - 1);

                if (!isPermutationOf(n, t)) {
                    continue;
                }

                double ratio = (double) n / (double) t;

                if (ratio < minRatio) {
                    result = n;
                    minRatio = ratio;
                } else {
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(10000000));
    }
}

