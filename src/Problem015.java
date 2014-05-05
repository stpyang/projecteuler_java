/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/16/13
 * Time: 5:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem015 implements MySolution {

    private final int N;

    public Problem015(int N) {
        this.N = N;
    }

    @Override
    public long solve() {
        long[] result = new long[N + 1];
        result[0] = 1;

        // this is given by the recursion
        // x_0 = 1, x_{n + 1} = (4 * n + 2) * x_n / (n + 1)
        for(int n = 0; n < N; ++n) {
            result[n + 1] = (long)(4 * n + 2) * result[n] / (n + 1);
        }

        return result[N];
    }

    public static void main(String[] args) {
        System.out.println(new Problem015(20).solve());
    }
}
