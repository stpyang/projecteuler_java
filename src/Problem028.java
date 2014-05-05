/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/20/13
 * Time: 1:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem028 implements MySolution {

    private final int N;

    public Problem028(int N) {
        this.N = N;
    }

    @Override
    public long solve() {
        // the four corners on the i-th level have coordinates
        // (4 * i * i + 4 * i + 1);
        // (4 * i * i + 2 * i + 1);
        // (4 * i * i + 1);
        // (4 * i * i - 2 * i + 1);
        // respectively
        long result = 1;
        for (int i = 1; i < (N + 1) / 2; ++i) {
            result += (16 * i + 4) * i + 4;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Problem028(1001).solve());
    }
}
