import gnu.trove.TIntArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/13/14
 * Time: 4:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem132Main {

    private static boolean dividesRepunit(int p, long numberOfOnes) {
        if (p == 3) {
            return numberOfOnes % 3 == 0;
        } else {
            return EulerUtil.longPowModulo(10, numberOfOnes, p) == 1;
        }
    }

    public static long solve(long numberOfOnes, int numberOfPrimes) {
        TIntArrayList primes = new TIntArrayList();

        int p = 1;
        while (primes.size() < numberOfPrimes) {
            while (!EulerUtil.isPrime(++p)) { }
            if (dividesRepunit(p, numberOfOnes)) {
                primes.add(p);
            }
        }

        return EulerUtil.sum(primes.toNativeArray());
    }

    public static void main(String[] args) {
        System.out.println(solve(10, 4));
        System.out.println(solve(1000000000, 40));
    }
}
