/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/13/14
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem235Main {

    @SuppressWarnings("WeakerAccess")
    static double s(double r) {
        double result = 0;
        for (int k = 1; k <= 5000; ++k) {
            result += (900 - 3 * k) * Math.pow(r, k - 1);
        }
        return result;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(long sum, int decimalPlaces) {
        double r = 1.0;
        double interval = 0.1;
        while(interval > Math.pow(10, -decimalPlaces)) {
            double y = s(r);
            if (y < sum) {
                r = r - interval / 2;
            } else if (y > sum) {
                r = r + interval / 2;
            }
            interval /= 2;
        }
        return Math.round(r * EulerUtil.longPow(10, decimalPlaces));
    }

    public static void main(String[] args) {
        System.out.println(solve(-600000000000L, 12));
    }
}
