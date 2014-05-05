/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/23/14
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem197Main {

    private static double f(double x) {
        return Math.floor(Math.pow(2, 30.403243784 - x * x)) * Math.pow(10, -9);
    }

    @SuppressWarnings("WeakerAccess")
    public static double solve() {
        double x = -1.0;
        double y = f(x);
        double oldSum = 0;
        double epsilon = 10e-10;

        while(Math.abs(x + y - oldSum) > epsilon) {
            oldSum = x + y;
            x = f(x);
            y = f(y);
        }

        return x + y;
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
