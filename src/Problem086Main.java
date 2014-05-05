/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/6/13
 * Time: 11:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem086Main {

    private static int count(int a) {
        int result = 0;
        for (int b = 1; b <= a; ++b) {
            for (int c = 1; c <= b; ++c) {
                if (EulerUtil.isSquare(a * a + (b + c) * (b + c))) {
                    ++result;
                }
            }
        }
        return result;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        int cumulativeSum = 0;
        int i = 1;
        while (cumulativeSum < N) {
            cumulativeSum += count(i);
            ++i;
        }
        return i - 1;
    }

    public static void main(String[] args) {
        System.out.println(solve(1000000));
    }
}
