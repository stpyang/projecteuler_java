/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/17/14
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem052Main {

    private static int getDigits(int n) {
        int result = 0;
        while (n > 0) {
            result += EulerUtil.longPow(10, n % 10);
            n /= 10;
        }
        return result;
    }

    private static boolean satisfies(int N, int n) {
        int d = getDigits(n);
        for (int i = 2; i <= N; ++i) {
            if (d != getDigits(i * n)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        int result = 1;
        while (!satisfies(N, result)) {
            ++result;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(6));
    }
}
