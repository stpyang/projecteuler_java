/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/13/13
 * Time: 1:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem007 implements MySolution {

    private final int N;

    public Problem007(int N) {
        this.N = N;
    }

    @Override
    public long solve() {
        // by the prime number theorem, we can estimate an upper bound for the N-th prime number is
        int estimate = 10;
        while (estimate / Math.log(estimate) < 2 * N) {
            estimate *= 2;
        }

        PrimeSieve primeSieve = new PrimeSieve(estimate);
        int count = 1;
        int p = 2;
        while (count++ < N) {
            p = primeSieve.getNextPrime(p);
        }
        return p;
    }

    public static void main(String[] args) {
        System.out.println(new Problem007(10001).solve());
    }
}
