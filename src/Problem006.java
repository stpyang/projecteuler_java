/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/13/13
 * Time: 1:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem006 implements MySolution {

    private final long N;

    public Problem006(long N) {
        this.N = N;
    }

    @Override
    public long solve() {
        // start with square of the sum, subtract the sum of the squares
        long sum = EulerUtil.getGeometricNumber(N, 3);
        return sum * sum - N * (N + 1) * (2 * N + 1) / 6;
    }

    public static void main(String[] args) {
        System.out.println(new Problem006(100).solve());
    }
}
