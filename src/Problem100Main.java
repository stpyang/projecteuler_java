/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/11/13
 * Time: 4:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem100Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve(long l) {
        // generate solutions to negative pell equation
        long x = 1;
        long y = 1;
        long b = 0;
        long rb = 0;
        while (rb < l) {
            long new_x = 3 * x + 2 * y;
            long new_y = 4 * x + 3 * y;
            x = new_x;
            y = new_y;
            b = (x + 1) / 2;
            rb = (y + 1) / 2;
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println(solve(1000000000000L));
    }
}
