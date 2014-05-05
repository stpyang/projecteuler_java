/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 2/4/14
 * Time: 5:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem136Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        return Problem135Main.solve(N, 1);
    }

        public static void main(String[] args) {
        System.out.println(solve(50000000));
    }
}
