import gnu.trove.TLongObjectHashMap;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.math.BigInteger;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 11/27/13
 * Time: 10:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class EulerUtil {

    private static final TLongObjectHashMap<Boolean> primeMap = new TLongObjectHashMap<>();
    private static final TLongObjectHashMap<int[]> createDigitsMap = new TLongObjectHashMap<>();
    private static final Map<Pair<Integer>, Long> binomialMap = new HashMap<>();
    private static final double SQRT_LONG_MAX_VALUE = Math.sqrt(Long.MAX_VALUE);

    private static final long[] FIRST_PRIMES = new long[] {
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103,
            107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223,
            227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347,
            349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463,
            467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607,
            613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743,
            751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883,
            887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997
    };

    // todo: do I need this code?
    static long binomial(int n, int k) {
        Pair<Integer> key = new Pair<>(n, k);
        if (binomialMap.containsKey(key)) {
            return binomialMap.get(key);
        }
        if (k < 0 || k > n) {
            return 0;
        } else if (k == 0 || k == n) {
            return 1;
        } else {
            long result = binomial(n - 1, k - 1) + binomial(n - 1, k);
            binomialMap.put(key, result);
            return result;
        }
    }

    static long factorial(int n) {
        if (n <= 1) {
            return 1L;
        } else {
            return n * factorial(n - 1);
        }
    }

    static long gcd(long a, long b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }

    static int getNextPrime(int n) {
        ++n;
        while (!isPrime(n)) { n++; }
        return n;
    }

    static int getNumberOfDigits(long n) {
        int result = 0;
        while (n > 0) {
            ++result;
            n /= 10;
        }
        return result;
    }

    // todo: remove this horrible code
    static int[] getDigits(long number) {
        if (createDigitsMap.containsKey(number)) {
            return createDigitsMap.get(number);
        }
        else {
            String s = Long.toString(number);
            char[] charArray = s.toCharArray();
            int[] result = new int[charArray.length];
            for (int i = 0; i < charArray.length; ++i) {
                result[i] = Character.getNumericValue(charArray[i]);
            }
            ArrayUtils.reverse(result);
            return result;
        }
    }

    // todo: remove this horrible code
    static long getLongFromDigits(int[] digits) {
        ArrayUtils.reverse(digits);
        String[] stringArray = new String[digits.length];
        for (int i = 0; i < digits.length; ++i) {
            stringArray[i] = Integer.toString(digits[i]);
        }
        return Long.valueOf(StringUtils.join(stringArray));
    }

    static long getGeometricNumber(long n, int g) {
        if (g > 1 && n > 0) {
            return n * ((g - 2) * n + 4 - g) / 2;
        } else {
            return 0;
        }
    }

    /**
     *  I'm not the biggest fan of calculating totients recursive because for large N it's going to create
     *  a heap size of up to N as we generate 1/2, 1/3, 1/4, etc...

    static private void totientRecursion(int a, int b, int c, int d, int limit, int[] result) {
        if (b + d > limit) return;
        totientRecursion(a, b, a + c, b + d, limit, result);
        totientRecursion(a + c, b + d, c, d, limit, result);
        ++result[b + d];
    }

    static int[] getTotients(final int N) {
        int result[] = new int[N + 1];
        totientRecursion(0, 1, 1, 1, N, result);
        return result;
    }
    */

    static int[] getTotients(final int N) {
        int result[] = new int[N + 1];
        for (int i = 0 ; i < result.length; ++i) {
            result[i] = i;
        }
        for (int i = 2; i < result.length; ++i) {
            if (result[i] == i) {
                int j = i;
                while (j < result.length) {
                    result[j] = (int)((long)result[j] * (long)(i - 1) / (long)i);
                    j += i;
                }
            }
        }
        return result;
    }

    static boolean isGeometricNumber(long i, int g) {
        double discriminant = (g - 4) * (g - 4) + 8 * i * (g - 2);
        long test = Math.round((g - 4 + Math.sqrt(discriminant)) / 2 / (g - 2));
        return i == getGeometricNumber(test, g);
    }

    /**
     * brute force!
     */
    static boolean isPrime(final long n) {
        if (primeMap.containsKey(n)) {
            return primeMap.get(n);
        } else {
            boolean isPrime = true;
            if (n < 2) isPrime = false;

            // try all possible factors i of N
            // if if N has a factor, then it has one less than or equal to sqrt(N),
            // so for efficiency we only need to check i <= sqrt(N) or equivalently i*i <= N
            for (long i = 2; i*i <= n; i++) {

                // if i divides evenly into N, N is not prime, so break out of loop
                if (n % i == 0) {
                    isPrime = false;
                    break;
                }
            }

            primeMap.put(n, isPrime);
            return isPrime;
        }
    }

    /**
     * Miller-Rabin test
     */
    static boolean isPrimeMR(final long n) {
        if (n < 1000) return ArrayUtils.contains(FIRST_PRIMES, n);
        for (long p : FIRST_PRIMES) {
            if (n % p == 0) return false;
        }

        // write n−1 as 2s·d by factoring powers of 2 from n−1
        long d = n - 1;
        int s = 0;
        while ((d & 1) == 0) {
            ++s;
            d = d >> 1;
        }

        int[] witnesses;
        if (n < 1373653) {
            witnesses = new int[] { 2, 3 };
        } else if (n < 9080191) {
            witnesses = new int[] { 31, 73 };
        } else if (n < 4759123141L) {
            witnesses = new int[] { 2, 7, 61 };
        } else if (n < 1122004669633L) {
            witnesses = new int[] {  2, 13, 23, 16628023 };
        } else if (n < 2152302898747L) {
            witnesses = new int[] {  2, 3, 5, 7, 11 };
        } else if (n < 3474749660383L) {
            witnesses = new int[] {  2, 3, 5, 7, 11, 13 };
        } else if (n < 341550071728321L) {
            witnesses = new int[] {  2, 3, 5, 7, 11, 13, 17 };
        } else if (n < 3825123056546413051L) {
            witnesses = new int[] {  2, 3, 5, 7, 11, 13, 17, 19, 23 };
        } else {
            // todo: fix this
            witnesses = new int[] {  2, 3, 5, 7, 11, 13, 17, 19, 23 };
        }

        for (int witness : witnesses) {
            boolean composite = true;

            if (EulerUtil.longPowModulo(witness, d, n) == 1) { composite = false; }
            if (composite & EulerUtil.longPowModulo(witness, d, n) == n - 1) { composite = false; }

            long exponent = d;
            for (int r = 1; composite & r < s; ++r) {
                exponent *= 2;
                if ((EulerUtil.longPowModulo(witness, exponent, n) == n - 1)) { composite = false; }
            }

            if (composite) return false;
        }

        return true;
    }

    static boolean isSquare(final long l) {
        if (l <= 0) {
            return false;
        }
        long s = (long)Math.floor(Math.sqrt(l));
        return (l == s * s);
    }

    private static long longPowSlow(long base, long exponent) {
        long result = 1;
        for (long i = 0; i < exponent; ++i) {
            result *= base;
        }
        return result;
    }

    static long longPow(long base, long exponent) {
        long result = 1;
        long b = base;
        long e = exponent;
        while (e > 0) {
            if ((e & 1) != 0) {
                result *= b;
            }
            if (b > SQRT_LONG_MAX_VALUE) {
                return longPowSlow(base, exponent);
            }
            b = b * b;
            e = e >> 1;
        }

        return result;
    }

    static long longPowModulo(long base, long exponent, long modulus) {
        long result = 1;
        long b = base;
        long e = exponent;

        BigInteger bigModulus = BigInteger.valueOf(modulus);

        while (e > 0) {
            if ((e & 1) != 0) {
                if (b < SQRT_LONG_MAX_VALUE && result < SQRT_LONG_MAX_VALUE) {
                    result *= b;
                    result %= modulus;
                } else {
                    result = (BigInteger.valueOf(result).multiply(BigInteger.valueOf(b))).mod(bigModulus).longValue();
                }
            }

            // we need to square b
            b = Math.min(b, modulus - b);
            if (b < SQRT_LONG_MAX_VALUE) {
                b = b * b;
                b %= modulus;
            } else {
                b = (BigInteger.valueOf(b).multiply(BigInteger.valueOf(b))).mod(bigModulus).longValue();
            }

            e = e >> 1;
        }
        return result;
    }

    static <T> List<List<T>> getPermutations(List<T> array) {
        List<List<T>> result = new ArrayList<>();
        getPermutationsRecursion(new ArrayList<T>(), array, result);
        return result;
    }

    private static <T> void getPermutationsRecursion(List<T> prefix, List<T> list, List<List<T>> result) {
        int n = list.size();
        if (n == 0) {
            result.add(prefix);
        }
        else {
            for (int i = 0; i < n; i++) {
                List<T> newPrefix = new ArrayList<>(prefix);
                List<T> newList = new ArrayList<>(list);
                newPrefix.add(newList.get(i));
                newList.remove(i);
                getPermutationsRecursion(newPrefix, newList, result);
            }
        }
    }

    static int[] getSumsOfDivisorsUpTo(int N) {
        int[] result = new int[1 + N];
        for (int i = 1; i <= N; ++i) {
            int j = 2 * i;
            while (j <= N) {
                result[j] += i;
                j += i;
            }
        }
        return result;
    }

    static int product(int[] A) {
        int result = 1;
        for (int a : A) {
            result *= a;
        }
        return result;
    }

//    static long product(long[] A) {
//        long result = 1;
//        for (long a : A) {
//            result *= a;
//        }
//        return result;
//    }

    static double sum(double[] A) {
        double result = 0;
        for (double a : A) {
            result += a;
        }
        return result;
    }

    static long sum(int[] A) {
        long result = 0;
        for (int a : A) {
            result += (long)a;
        }
        return result;
    }

    static long sum(long[] A) {
        long result = 0;
        for (long a : A) {
            result += a;
        }
        return result;
    }

    static boolean isPalindrome(String s) {
        if (s.isEmpty() || s.length() == 1) return true;
        if (s.charAt(0) != s.charAt(s.length() - 1)) return false;
        return isPalindrome(s.substring(1, s.length() - 1));
    }

    static boolean isPalindrome(long n) {
        return isPalindrome(Long.toString(n));
    }

    static boolean isPalindrome(long n, int base) {
        int numberOfDigits = 1 + (int)Math.floor(Math.log(n) / Math.log(base));
        long m = n;
        for (int i = 0; i < numberOfDigits / 2; ++i) {
            if (n % base != m / longPow(base, numberOfDigits - i - 1)) {
                return false;
            }
            n /= base;
            m = m - (m / longPow(base, numberOfDigits - i - 1)) * longPow(base, numberOfDigits - i - 1);
        }
        return true;
    }

    static double getLinearCombination(int[] coefficients, double[] entries) {
        double result = 0;
        for (int i = 0; i < entries.length; ++i) {
            result += coefficients[i] * entries[i];
        }
        return result;
    }

    static class Triplet<T> {
        final T a;
        final T b;
        final T c;

        Triplet(T[] abc) {
            if (abc.length != 3) {
                throw new ExceptionInInitializerError("T array for abc must have exactly 3 entries!");
            } else {
                this.a = abc[0];
                this.b = abc[1];
                this.c = abc[2];
            }
        }

        Triplet(T a, T b, T c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Triplet triplet = (Triplet) o;

            return !(a != null ? !a.equals(triplet.a) : triplet.a != null) && !(b != null ? !b.equals(triplet.b) : triplet.b != null) && !(c != null ? !c.equals(triplet.c) : triplet.c != null);

        }

        @Override
        public int hashCode() {
            int result = a != null ? a.hashCode() : 0;
            result = 31 * result + (b != null ? b.hashCode() : 0);
            result = 31 * result + (c != null ? c.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Triplet{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    '}';
        }
    }

    static void main(String[] args) {
        System.out.println(Arrays.toString(getDigits(123)));

        List<Integer> test = new ArrayList<>();
        test.add(4);
        test.add(5);
        test.add(6);
        test.add(7);
        test.add(8);
        for (List<Integer> l : getPermutations(test)) {
            System.out.println(l);
        }

        for (int i = 1; i < 100; ++i) {
            System.out.println(i + " " + isSquare(i));
        }
    }

}
