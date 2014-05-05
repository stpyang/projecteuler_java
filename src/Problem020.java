import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/17/13
 * Time: 3:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem020 implements MySolution {

    private final int N;

    public Problem020(int N) {
        this.N = N;
    }

    @Override
    public long solve() {
        int result = 0;
        BigInteger factorial = BigInteger.ONE;
        for (int i = 2; i <= N; ++i) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        for (char c : factorial.toString().toCharArray()) {
            result += Integer.parseInt(Character.toString(c));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Problem020(100).solve());
    }
}
