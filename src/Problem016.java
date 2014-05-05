import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/16/13
 * Time: 5:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem016 implements MySolution {

    private final int N;

    public Problem016(int N) {
        this.N = N;
    }

    @Override
    public long solve() {
        long result = 0;
        BigInteger p = BigInteger.valueOf(2).pow(N);

        String s = p.toString();
        for (char c : s.toCharArray()) {
            result += Integer.parseInt(Character.toString(c));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Problem016(1000).solve());
    }
}
