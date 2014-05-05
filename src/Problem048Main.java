/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/14/14
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem048Main {

    public static long solve(int N, long modulus) {
        long result = 0;
        for (long i = 1; i <= N; ++i) {
            result += EulerUtil.longPowModulo(i, i, modulus);
            result %= modulus;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(1000, 10000000000L));
    }
}
