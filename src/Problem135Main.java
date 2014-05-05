/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/15/14
 * Time: 3:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem135Main {

    private static double lowerX(int N, long k) {
        double result = 0;
        double discriminant = 4 * k * k + 4 * (3 * k * k - N);
        if (discriminant > 0 && 2 * k < Math.sqrt(discriminant)) {
            result = (2 * k + Math.sqrt(discriminant)) / 2.0;
        }
        return result;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N, int n) {
        long result = 0;
        int[] numberOfSolutions = new int[N];

        // solve 3*k*k + 2 * k - (1 + upperLimit) = 0
//        double discriminant = 2 + 12 * (1 + upperLimit);
//        int upperK = (int)Math.ceil((-2.0 + Math.sqrt(discriminant)) / 6.0);

        int k = 1;
        while (k <= Math.sqrt(N / 3)) {
            int x = 3 * k;
            int f = 0;
            int d = 4 * k - 1;
            while (x > 0) {
                if (0 <= f && f < N) {
                    ++numberOfSolutions[f];
                }
                f += d;
                d -= 2;
                --x;
            }
            ++k;
        }
        while (3 * k - lowerX(N, k) > 0.99999999) {
            int f = 0;
            int d = 4 * k - 1;
            while (f < N) {
                ++numberOfSolutions[f];
                f += d;
                d -= 2;
            }
            ++k;
        }

        for(int s : numberOfSolutions) {
            if (s == n) {
                ++result;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(1000000, 10));
    }
}
