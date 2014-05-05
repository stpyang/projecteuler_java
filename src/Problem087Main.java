/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/6/13
 * Time: 1:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem087Main {

    public static long solve(int N) {
        int result = 0;

        PrimeSieve primeSieve = new PrimeSieve((int)Math.ceil(Math.sqrt(N)));
        boolean[] isSum2 = new boolean[1 + N];
        boolean[] isSum3 = new boolean[1 + N];
        boolean[] isSum4 = new boolean[1 + N];

        for(int i = 2; i * i < isSum2.length; ++i) {
            if (primeSieve.isPrime(i)) {
                isSum2[i * i] = true;
            }
        }

        for (int i = 2; i < isSum3.length; ++i) {
            if (isSum2[i]) {
                for (int j = 2; i + j * j * j < N; ++j) {
                    if (primeSieve.isPrime(j)) {
                        isSum3[i + j * j * j] = true;
                    }
                }
            }
        }

        for (int i = 2; i < isSum4.length; ++i) {
            if (isSum3[i]) {
                for (int j = 2; i + j * j * j * j < N; ++j) {
                    if (primeSieve.isPrime(j)) {
                        isSum4[i + j * j * j * j] = true;
                    }
                }
            }
        }

        for (int i = 2; i < isSum4.length; ++i) {
            if (isSum4[i]) {
                ++result;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(50000000));
    }
}
