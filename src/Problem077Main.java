import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/4/13
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem077Main {

    // todo: speed this up!
    private static int[] multiply(int[] a, int[] b) {
        int l = Math.min(a.length, b.length);
        int[] result = new int[l];
        for (int i = 0; i < l; ++i) {
            int c = 0;
            for (int j = 0; j <= i; ++j) {
                c += a[j] * b[i-j];
            }
            result[i] = c;
        }
        return result;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        // generate P = (1 - x^2)*(1 - x^3)*(1 - x^5)*...
        int[] P = new int[1 + N];
        P[0] = 1;

        PrimeSieve primeSieve = new PrimeSieve(N);
        for (int i = 2; i < N; ++i) {
            if (primeSieve.isPrime(i)) {
                int[] temp = new int[1 + N];
                temp[0] = 1;
                temp[i] = -1;
                P = multiply(P, temp);
            }
        }

        List<Integer> Q = new ArrayList<>();
        Q.add(1);
        for (int i = 1; i < N; ++i) {
            int convolution = 0;
            for(int j = 0; j < i; ++j) {
                convolution -= Q.get(j) * P[i-j];
            }
            Q.add(convolution);
            if (convolution > N) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solve(5000));
    }
}
