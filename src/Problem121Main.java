/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/13/13
 * Time: 2:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem121Main {

    private static double p(int b, int t) {
        if (b < 0 || b > t) {
            return 0;
        } else if (t == 1) {
            return 0.5;
        } else {
            return p(b - 1, t - 1) / (t + 1) + t * p(b, t - 1) / (t + 1);
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        double probabilityOfWinning = 0;
        for (int i = N / 2 + 1; i <= N; ++i) {
            probabilityOfWinning += p(i, N);
        }
        return (long)Math.floor(1 / probabilityOfWinning);
    }

    public static void main(String[] args) {
        System.out.println(solve(15));
    }
}
