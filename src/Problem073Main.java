/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 2/7/14
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem073Main {

    private static void recurse(int a, int b, int c, int d, int limit, long[] result) {
        if (b + d > limit) return;
        recurse(a, b, a + c, b + d, limit, result);
        recurse(a + c, b + d, c, d, limit, result);
        ++result[0];
    }

    public static long solve(int a, int b, int c, int d, int limit) {
        long[] result = new long[] { 0 };

        recurse(a, b, c, d, limit, result);

        return result[0];
    }

    public static void main(String[] args) {
        System.out.println(solve(1, 3, 1, 2, 12000));
    }
}
