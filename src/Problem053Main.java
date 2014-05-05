/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/16/14
 * Time: 9:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem053Main {
    @SuppressWarnings("WeakerAccess")
    public static long solve(int N, int threshold) {
        long result = 0;
        long[][] nCk = new long[N + 1][];
        nCk[0] = new long[] { 1 };
        for(int n = 1; n <= N; ++n) {
            nCk[n] = new long[1 + n];
            nCk[n][0] = 1;
            for(int k = 1; k < n; ++k) {
                nCk[n][k] = nCk[n - 1][k] + nCk[n - 1][k - 1];
                if (nCk[n][k] > threshold) {
                    nCk[n][k] = threshold;
                    ++result;
                }
            }
            nCk[n][n] = 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(100, 1000000));
    }
}
