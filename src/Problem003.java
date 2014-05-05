/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/6/13
 * Time: 1:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem003 implements MySolution {

    private final long N;

    public Problem003(long N) {
        this.N = N;
    }

    private static int _solve(long n, int q) {
        if (n == q) return q;
        if (n % q == 0) return _solve(n / q, q);
        if (q == 2) return _solve(n, 3);
        return _solve(n, q + 2);
    }

    // just keep dividing by smaller numbers until we run out
    @Override
    public long solve() {
        return _solve(N, 2);
    }

    public static void main(String[] args) {
        System.out.println(new Problem003(600851475143L).solve());
    }
}
