/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 2/4/14
 * Time: 4:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem133Main {

    public static boolean fastTest(int prime) {
        // test if (A(prime) == 2^a * 5^b)
        if (prime == 2 || prime == 5) {
            return false;
        }
        int a = Problem129Main.A(prime);
        while (a % 2 == 0) {
            a /= 2;
        }
        while (a % 5 == 0) {
            a /= 5;
        }
        return a == 1;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        long result = 0;
        PrimeSieve primeSieve = new PrimeSieve(N);

        for (int i = 2; i <= N; ++i) {
            if (primeSieve.isPrime(i) && !fastTest(i)) {
                result += i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(100000));
    }
}
