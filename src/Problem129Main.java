/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/16/14
 * Time: 8:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem129Main {

    public static int A(long n) {
        if (n % 2 == 0 || n % 5 == 0) {
            return 1;
        } else {
            int result = 1;
            int repunit = 1;
            while (repunit != 0) {
                ++result;
                repunit *= 10;
                ++repunit;
                repunit %= n;
            }
            return result;
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        long n = N;
        if ((n & 1) == 0) { ++n; }
        while (true) {
            if (n % 5 != 0) {
                if (A(n) > N) {
                    return n;
                }
            }
            n += 2;
        }
    }

    public static void main(String[] args) {
        System.out.println(solve(1000000));
    }
}
