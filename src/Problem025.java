/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/18/13
 * Time: 4:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem025 implements MySolution {

    private final int N;

    public Problem025(int N) {
        this.N = N;
    }

    private static final double SQRT_5 = Math.sqrt(5);
    private static final double PHI = (1 + SQRT_5) / 2;

    // this approximation only works for *large* values of N
    @Override
    public long solve() {
        return Math.round(Math.ceil((N - 1 + Math.log10(SQRT_5)) / Math.log10(PHI)));
    }

    public static void main(String[] args) {
        System.out.println(new Problem025(1000).solve());
    }
}
