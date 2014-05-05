import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 2/13/14
 * Time: 1:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class PrimeSieve {

    final private boolean[] primeSieve;
    final private int upperLimit;

    PrimeSieve(int N) {
        // just keep track of odd integers to save space
        this.primeSieve = new boolean[N / 2 + 1];
        this.upperLimit = N;
        Arrays.fill(this.primeSieve, true);
        this.primeSieve[0] = false;

        for (int i = 3; i < Math.sqrt(N); i += 2) {
            if (isPrime(i)) {
                for (int j = 3 * i; (j - 1) / 2 < this.primeSieve.length; j += 2 * i) {
                    this.primeSieve[(j - 1) / 2] = false;
                }
            }
        }
    }

    boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n > upperLimit) throw new IllegalArgumentException("prime sieve too small!");
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        return this.primeSieve[(n - 1) / 2];
    }

    int getNextPrime(int p) {
        while (!isPrime(++p));
        return p;
    }

    int getUpperLimit() {
        return upperLimit;
    }

    public static void main(String[] args) {
        PrimeSieve ps = new PrimeSieve(100);
        for (int i = 0; i < 100; ++i) {
            if (ps.isPrime(i)) {
                System.out.println(i);
            }
        }
    }
}
