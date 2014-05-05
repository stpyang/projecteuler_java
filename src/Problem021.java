/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/18/13
 * Time: 4:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem021 implements MySolution {

    private final int N;

    public Problem021(int N) {
        this.N = N;
    }

    @Override
    public long solve() {
        int result = 0;
        int[] sumOfDivisors = EulerUtil.getSumsOfDivisorsUpTo(N);
        for (int i = 1; i <= N; ++i) {
            // test for amicibility
            if (sumOfDivisors[i] <= N && i != sumOfDivisors[i] && sumOfDivisors[sumOfDivisors[i]] == i) {
                result += i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Problem021(10000).solve());
    }
}
