import gnu.trove.TDoubleArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/16/13
 * Time: 12:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem108Main {

    private static double[] getLogPrimes(int N) {
        TDoubleArrayList primes = new TDoubleArrayList();
        int p = 1;
        while (primes.size() < N) {
            p = EulerUtil.getNextPrime(p);
            primes.add(Math.log(p));
        }
        return primes.toNativeArray();
    }

    private static int numberOfSolutions(int[] exponents) {
        int result = 1;
        for (int e : exponents) {
            result *= (2 * e + 1);
        }
        return (result - 1) / 2;
    }

    private static void recursiveTestExponents(double[] logPrimes, int N, int[] exponents, int level, int[][] best) {
        if (level == exponents.length) {
            if (numberOfSolutions(exponents) > N) {
                if (best[0] == null || EulerUtil.getLinearCombination(exponents, logPrimes) < EulerUtil.getLinearCombination(best[0], logPrimes)) {
                    best[0] = exponents;
                }
            }
        } else {
            // break out if there's no hope of finding a solution because the exponents are too small for further recursion
            long test = 1;
            for (int i = 0; i < level; ++i) {
                test *= (2 * exponents[i] + 1);
            }
            for (int i = level; i < exponents.length; ++i) {
                test *= (2 * exponents[level - 1] + 1);
            }
            if (test < 2 * N) {
                return;
            }

            // recurse;
            for(int e = 0; e <= exponents[level - 1]; ++e) {
                int[] newExponents = exponents.clone();
                newExponents[level] = e;

                if (best[0] == null || EulerUtil.getLinearCombination(newExponents, logPrimes) < EulerUtil.getLinearCombination(best[0], logPrimes)) {
                    recursiveTestExponents(logPrimes, N, newExponents, 1 + level, best);
                }
            }
        }
    }

    public static long solve(int N) {
        long result = 1;
        double[] logPrimes = getLogPrimes((int)Math.ceil(Math.log(2 * N) / Math.log(3)));

        int[][] best = new int[1][];
        for (int e = 1; e < Math.log(2 * N) / Math.log(2); ++e) {
            int[] newExponents = new int[logPrimes.length];
            newExponents[0] = e;
            recursiveTestExponents(logPrimes, N, newExponents, 1, best);
        }

        for(int i = 0; i < logPrimes.length; ++i) {
            long p = Math.round(Math.exp(logPrimes[i]));
            result *= EulerUtil.longPow(p, best[0][i]);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(1000));
    }
}
