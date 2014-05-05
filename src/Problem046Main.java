/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/10/14
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem046Main {

    private static boolean failsGoldbach(int n) {
        for (int i = 1; 2 * i * i < n; ++i) {
            if (EulerUtil.isPrime(n - 2 * i * i)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        int result = 3;
        while (true) {
            if (!EulerUtil.isPrime(result) && failsGoldbach(result)) {
                return result;
            }
            result += 2;
        }
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
