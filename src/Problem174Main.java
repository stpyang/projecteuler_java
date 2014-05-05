/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/16/14
 * Time: 3:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem174Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N, int n) {
        long result = 0;
        int[] L = new int[N + 1];
        int squares[] = new int[N / 4 + 2];

        for (int i = 1; i < squares.length; ++i) {
            squares[i] = i * i;
            for (int j = i - 2; j > 0; j -= 2) {
                int k = squares[i] - squares[j];
                if (k < N + 1) {
                    ++L[k];
                } else {
                    break;
                }
            }
        }

        for (int l : L) {
            if (0 < l && l <= n) {
                ++result;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(1000000, 10));
    }
}
