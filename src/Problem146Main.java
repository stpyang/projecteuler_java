/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 2/12/14
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem146Main {

    private static boolean matchesPattern(long n) {
        long n2 = n * n;

        if (!EulerUtil.isPrimeMR(n2 + 1)) return false;
        if (!EulerUtil.isPrimeMR(n2 + 3)) return false;
        if (!EulerUtil.isPrimeMR(n2 + 7)) return false;
        if (!EulerUtil.isPrimeMR(n2 + 9)) return false;
        if (!EulerUtil.isPrimeMR(n2 + 13)) return false;
        if (!EulerUtil.isPrimeMR(n2 + 27)) return false;

        // do I have to check these?
        if (EulerUtil.isPrimeMR(n2 + 19)) return false;
        //noinspection RedundantIfStatement
        if (EulerUtil.isPrimeMR(n2 + 21)) return false;

        return true;
    }
    
    public static long solve(long N) {
        long result = 0;

        // n must be divisible by 10
        for (long n = 10; n < N; n += 10) {
            long n2 = n * n;
            if (n % 3 == 0) continue;
            if (n % 7 == 0 || n2 % 7 == 1 || n2 % 7 == 4) continue;
            if (n2 % 11 == 4 || n2 % 11 == 9) continue;
            if (n % 13 == 0 || n2 % 13 == 4 || n2 % 13 == 10 || n2 % 13 == 12) continue;
            if (n2 % 17 == 4 || n2 % 17 == 16) continue;
            if (n2 % 23 == 16) continue;
            if (n2 % 29 == 16) continue;
            if (n2 % 31 == 4) continue;
            if (n2 % 37 == 36) continue;
            if (n2 % 39 == 36) continue;
            if (n2 % 43 == 16) continue;

            if (matchesPattern(n)) {
                result += n;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(1000000));
        System.out.println(solve(150000000));
    }
}
