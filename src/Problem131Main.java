/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/7/14
 * Time: 5:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem131Main {

    public static long solve(int N) {
        int result = 0;
        PrimeSieve primeSieve = new PrimeSieve(N);

        int upperLimit = (int)Math.floor(3 + Math.sqrt(9 + 12 * (N - 1))/ 6);

        for (int i = 0; i < upperLimit; ++i) {
            for (int j = i + 1; j < upperLimit; ++j) {
                if (j * j * j - i * i * i < N) {
                    if (primeSieve.isPrime(j * j * j - i * i * i)) {
                        ++result;
                    }
                }
                else {
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(1000000));
    }
}
