/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/13/13
 * Time: 1:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem005 implements MySolution {

    private final int N;

    public Problem005(int N) {
        this.N = N;
    }

    private static long lcm(long a, long b) {
        return a / EulerUtil.gcd(a, b) * b;
    }

    @Override
    public long solve() {
        long result = 1;
        for (long i = 1; i <= N; ++i) {
            result = lcm(result, i);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Problem005(10).solve());
    }
}
