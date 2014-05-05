import gnu.trove.TDoubleArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/6/14
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem204Main {

    private static void recursiveTestExponents(double[] logPrimes, double logLimit, int[] exponents, int level, int[] count) {
        if (level == exponents.length) {
            if (EulerUtil.getLinearCombination(exponents, logPrimes) <= logLimit) {
                ++count[0];
            }
        } else {
            double upperLimit = logLimit - EulerUtil.getLinearCombination(exponents, logPrimes);
            // recurse;
            for(int e = 0; e * logPrimes[level] <= upperLimit; ++e) {
                int[] newExponents = exponents.clone();
                newExponents[level] = e;

                recursiveTestExponents(logPrimes, logLimit, newExponents, 1 + level, count);
            }
        }
    }

   // todo: make this work!
   private static long recursiveCountExponents(double[] logPrimes, double logLimit, int[] exponents, int level) {
        if (level == exponents.length - 1) {
            return (long)Math.floor(logLimit - EulerUtil.getLinearCombination(exponents, logPrimes) / logPrimes[logPrimes.length - 1]);
        } else {
            long result = 0;
            double upperLimit = logLimit - EulerUtil.getLinearCombination(exponents, logPrimes);
            // recurse;
            for(int e = 0; e * logPrimes[level] <= upperLimit; ++e) {
                int[] newExponents = exponents.clone();
                newExponents[level] = e;

                result += recursiveCountExponents(logPrimes, logLimit, newExponents, 1 + level);
            }
            return result;
        }
    }

    public static long solve(int type, long limit) {
        TDoubleArrayList logPrimes = new TDoubleArrayList();
        int p = 1;
        while (true) {
            while (!EulerUtil.isPrime(++p)) { }
            if (p <= type) {
                logPrimes.add(Math.log(p));
            } else {
                break;
            }
        }

        int[] count = new int[1];

        int[] exponents = new int[logPrimes.size()];
        for (int i = 0; i * logPrimes.get(0) < Math.log(limit); ++i) {
            exponents[0] = i;
            recursiveTestExponents(logPrimes.toNativeArray(), Math.log(limit), exponents, 1, count);
//            System.out.println(i + " " + count[0]);
        }

        long test = 0;
        for (int i = 0; i * logPrimes.get(0) < Math.log(limit); ++i) {
            exponents[0] = i;
            test += recursiveCountExponents(logPrimes.toNativeArray(), Math.log(limit), exponents, 1);
//            System.out.println(i + " " + test);
        }

        return count[0];
    }

    public static void main(String[] args) {
        System.out.println(solve(100, 1000000000L));
    }
}
