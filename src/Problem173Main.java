/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/3/14
 * Time: 11:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem173Main {

    public static long solve(int N) {
        long result = 0;

        long[] squares = new long[N / 4 + 2];

        for (long i = 0; i < squares.length; ++i) {
            squares[(int)i] = i * i;
        }

        for (int i = 1; i < squares.length; ++i) {
            long j = (long)Math.floor(Math.sqrt(squares[i] + N));
            result += (j - i) / 2;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(1000000));
    }
}
