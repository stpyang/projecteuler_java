/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/17/14
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem069Main {

    public static long solve(int N) {
        long result = 1;

        int p = 1;
        while (true) {
            while (!EulerUtil.isPrime(++p)) { }
            if (result * p > N) {
                return result;
            }
            result *= p;
        }
    }

    public static void main(String[] args) {
        System.out.println(solve(1000000));
    }
}
