/**
 * Created by stpyang on 12/26/13.
 * todo: clean this up
 */
public class Problem123Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve(long N) {
        // 7037th prime number is 71059

        PrimeSieve primeSieve = new PrimeSieve(710590);

        int n = 7037;
        int p = 71059;
        while (true) {
            if (2 * (long)n * (long)p > N && (long)p * (long)p > N) {
                break;
            } else {
                ++n;
                p = primeSieve.getNextPrime(p);
            }
        }

        if ((n & 1) == 0) {
            return n + 1;
        } else {
         return n;
        }
    }

    public static void main(String[] args) {
        System.out.println(solve(1000000000L));
        System.out.println(solve(10000000000L));
    }
}
