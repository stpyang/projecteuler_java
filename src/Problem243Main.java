import gnu.trove.TLongArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/3/14
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem243Main {

    private static long power(long[] primes, int[] exponents) {
        long result = 1;
        for (int i = 0; i < primes.length; ++i) {
            result *= EulerUtil.longPow(primes[i], exponents[i]);
        }
        return result;
    }

    private static void recursiveTestExponents(long[] primes, long a, long b, int numerator, int denominator, int[] exponents, int level, long[] bestPower) {
        if (level == exponents.length) {
            // calcaulte new power
            long test = power(primes, exponents);
            // update if it's valid and better than what we had before
            if (test * a * denominator < (test * b - 1) * numerator) {
                if (test < bestPower[0]) {
                    bestPower[0] = test;
                }
            }
        } else {
            // break out if there's no hope of finding a solution because the exponents are too small for further recursion
            long test = 1;
            for (int i = 0; i < level; ++i) {
                test *= EulerUtil.longPow(primes[i], exponents[i]);
            }
            for (int i = level; i < exponents.length; ++i) {
                test *= EulerUtil.longPow(primes[i], exponents[level]);
            }
            if (test * a * denominator > (test * b - 1) * numerator) {
                return;
            }

            // recurse;
            for(int e = 0; e <= exponents[level - 1]; ++e) {
                int[] newExponents = exponents.clone();
                newExponents[level] = e;

                if (power(primes, newExponents) < bestPower[0]) {
                    recursiveTestExponents(primes, a, b, numerator, denominator, newExponents, 1 + level, bestPower);
                }
            }
        }
    }

    public static long solve(int numerator, int denominator) {
        int p = 1;

        TLongArrayList primes = new TLongArrayList();

        long a = 1;
        long b = 1;
        while(a * denominator > b * numerator) {
            // get next prime
            while (!EulerUtil.isPrime(++p)) { }

            a *= p - 1;
            b *= p;

            primes.add(p);
        }

        int[] exponents = new int[primes.size()];

        long[] bestPower = new long[1];
        bestPower[0] = Long.MAX_VALUE;

        int i = 1;
        while (bestPower[0] == Long.MAX_VALUE) {
            exponents[0] = i;
            recursiveTestExponents(primes.toNativeArray(), a, b, numerator, denominator, exponents, 1, bestPower);
            ++i;
        }

        return b * bestPower[0];
    }

    public static void main(String[] args) {
        System.out.println(solve(15499, 94744));
    }
}
