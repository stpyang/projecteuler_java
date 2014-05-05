/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/10/14
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem134Main {

    static long S(int p, int q) {
        int d = EulerUtil.getNumberOfDigits(p);
        long pow10 = EulerUtil.longPow(10, d);

        // need to solve p + result * (pow10 mod q) == 0 mod q

        long pow10Inv = EulerUtil.longPowModulo(pow10 % q, q - 2, q);
        return ((q - p) * pow10Inv % q) * pow10 + p;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int lowerLimit, int upperLimit) {
        long result = 0;
        while (!EulerUtil.isPrime(++upperLimit)) { }
        PrimeSieve primeSieve = new PrimeSieve(upperLimit);
        int p = lowerLimit;
        int q = primeSieve.getNextPrime(p);
        while (q <= upperLimit) {
            result += S(p, q);
            p = q;
            while (++q < upperLimit && !primeSieve.isPrime(q)) { }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(5, 1000000));
    }
}
