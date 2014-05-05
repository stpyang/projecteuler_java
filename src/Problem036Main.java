/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/31/13
 * Time: 10:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem036Main {
    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        long result = 0;
        for(int i = 1; i <= N; ++i) {
            if (EulerUtil.isPalindrome(i, 10) && EulerUtil.isPalindrome(i, 2)) {
                result += i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(1000000));
    }
}
