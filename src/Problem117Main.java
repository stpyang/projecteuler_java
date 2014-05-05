/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/22/13
 * Time: 10:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem117Main {
    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        long[] solutions = new long[1 + N];
        solutions[1] = 1;
        solutions[2] = 2;
        solutions[3] = 4;
        solutions[4] = 8;

        for (int i = 5; i <= N; ++i) {
            solutions[i] = solutions[i - 1] + solutions[i - 2] + solutions[i - 3] + solutions[i - 4];
        }

        return solutions[N];
    }

    public static void main(String[] args) {
        System.out.println(solve(50));
    }
}
