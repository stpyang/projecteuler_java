/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/20/13
 * Time: 10:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem026 implements MySolution {

    private final int N;

    public Problem026(int N) {
        this.N = N;
    }

    private static long getSequenceLength(int divisor, int dividend, int position, int[] remainders) {
        if (dividend == 0) return 0;
        if (remainders[dividend] != 0) return position - remainders[dividend];
        remainders[dividend] = position;
        return getSequenceLength(divisor, (10 * dividend) % divisor, position + 1, remainders);
    }

    static long getSequenceLength(int n) {
        if (n == 0) return 0;
        if (n % 2 == 0) return getSequenceLength(n / 2);
        if (n % 5 == 0) return getSequenceLength(n / 5);
        return getSequenceLength(n, 1, 1, new int[n + 1]);
    }

    @Override
    public long solve() {
        int result = 0;
        long maxSequence = 0;
        for (int i = 3; i < N; ++i) {
            long s = getSequenceLength(i);
            if (maxSequence < s) {
                maxSequence = s;
                result = i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Problem026(1000).solve());
    }
}
