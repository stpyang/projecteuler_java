/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/13/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem010 implements MySolution {

    private final int N;

    public Problem010(int N) {
        this.N = N;
    }

    @Override
    public long solve() {
        long result = 0;
        PrimeSieve primeSieve = new PrimeSieve(N);
        for (int i = 2; i <= N; ++i) {
            if (primeSieve.isPrime(i)) {
                result += i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Problem010(2000000).solve());
    }
}
