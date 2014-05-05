import gnu.trove.TIntArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/22/14
 * Time: 9:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem051Main {

    private static int nextPermutation(int v) {
        int t = (v | ( v - 1)) + 1;
        return t | ((((t & -t) / (v & -v)) >> 1) - 1);
    }

    private static int createNumberFromDigits(int[] digits) {
        int result = 0;
        int pow10 = 1;
        for(int d : digits) {
            result += pow10 * d;
            pow10 *= 10;
        }
        return result;
    }

    private static void recurse(int[] digits, int numberOfPrimes, int template, PrimeSieve primeSieve, int level, TIntArrayList solutions) {
        if (level == digits.length) {
            int numberOfNonPrimes;
            int[] newDigits = digits.clone();
            numberOfNonPrimes = 0;
            TIntArrayList primes = new TIntArrayList();
            int c;
            for (int d = 0; d < 10; ++d) {
                for (int i = 0; i < newDigits.length; ++i) {
                    if ((template & (1 << i)) != 0) {
                        newDigits[i] = d;
                    }
                }

                // create number
                if (primeSieve.isPrime(c = createNumberFromDigits(newDigits)) && newDigits[newDigits.length - 1] != 0) {
                    primes.add(c);
                } else {
                    ++numberOfNonPrimes;
                }

                if (numberOfNonPrimes > 10 - numberOfPrimes) {
                    break;
                }
            }
            if (numberOfNonPrimes <=  10 - numberOfPrimes) {
                solutions.add(primes.toNativeArray());
            }
        } else {
            while (level < digits.length && (template & (1 << level)) != 0) { ++level; }
            if (level < digits.length) {
                int[] newDigits = digits.clone();
                for (int i = 0; i < 10; ++i) {
                    newDigits[level] = i;
                    recurse(newDigits, numberOfPrimes, template, primeSieve, level + 1, solutions);
                }
            } else {
                recurse(digits, numberOfPrimes, template, primeSieve, level, solutions);
            }
        }
    }

    public static long solve(int numberOfDigitsToReplace, int numberOfPrimes) {
        // we have to replace 3*n digits otherwise we could not have eight prime numbers in a family

        TIntArrayList solutions = new TIntArrayList();
        int numberOfDigits = numberOfDigitsToReplace + 1;
        while (solutions.isEmpty()) {
            PrimeSieve primeSieve = new PrimeSieve((int)EulerUtil.longPow(10, numberOfDigits));
            int template = (int)EulerUtil.longPow(2, numberOfDigitsToReplace) - 1;

            for (int i = 0; i < EulerUtil.binomial(numberOfDigits, numberOfDigitsToReplace); ++i) {
                int[] digits = new int[numberOfDigits];
                recurse(digits, numberOfPrimes, template, primeSieve, 0, solutions);
                template= nextPermutation(template);
            }

            ++numberOfDigits;
        }

        solutions.sort();
        return solutions.get(0);
    }

    public static void main(String[] args) {
        System.out.println(solve(3, 8));
    }
}
