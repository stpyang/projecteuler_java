/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/17/14
 * Time: 9:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem126Main {

    private static long layer(int x, int y, int z, int l) {
        return 2 * (x * y + y * z + x * z) + 4 * (l - 1) * (x + y + z) + 4 * (l - 1) * (l - 2);
    }

    static long C(long n) {
        int result = 0;
        for (int l = 1; layer(1, 1, 1, l) <= n; ++l) {
            for(int x = 1; layer(x, 1, 1, l) <= n; ++x) {
                for (int y = x; layer(x, y, 1, l) <= n; ++y) {
                    int numerator = (int)n - 4 * (l - 1) * (l - 2) - 4 * (l - 1) * (x + y) - 2 * x * y;
                    int denominator = 2 * (x + y) + 4 * (l - 1);

                    if (numerator >= y * denominator) {
                        if (numerator % denominator == 0) {
                            ++result;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return result;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        long result = 0;
        while (C(++result) != N) { }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(C(78));
//        System.out.println(solve(10));
//        System.out.println(solve(1000));
    }
}
