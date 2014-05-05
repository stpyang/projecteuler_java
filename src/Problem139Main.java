/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 2/5/14
 * Time: 1:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem139Main {

    private static void recurse(long N, FibonacciBox f, long[] result) {
        if (EulerUtil.sum(f.getPythagoreanTriple()) < N) {
            if (f.getPythagoreanTriple()[2] % Math.abs(f.getPythagoreanTriple()[0] - f.getPythagoreanTriple()[1]) == 0) {
                result[0] += N / EulerUtil.sum(f.getPythagoreanTriple());
            }
            for (FibonacciBox child : f.getChildren()) {
                recurse(N, child, result);
            }
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(long N) {
        long result[] = new long[] { 0 };
        recurse(N, FibonacciBox.SEED, result);
        return result[0];
    }

    public static void main(String[] args) {
        System.out.println(solve(100000000));
    }
}
