import gnu.trove.TIntArrayList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/18/13
 * Time: 4:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem024 implements MySolution {

    private final int N;
    private final int k;

    public Problem024(int N, int k) {
        this.N = N;
        this.k = k;
    }

    private String getDigits(int n, TIntArrayList digits) {
        if (digits.isEmpty()) return "";
        else {
            int f = (int)EulerUtil.factorial(digits.size() - 1);
            int i = n / f;
            return Integer.toString(digits.remove(i)) + getDigits(n - i * f, digits);
        }
    }

    @Override
    public long solve() {
        TIntArrayList digits = new TIntArrayList();
        for(int i = 0; i < k; ++i) digits.add(i);

        return Long.parseLong(getDigits(N - 1, digits));
    }

    public static void main(String[] args) {
        System.out.println(new Problem024(1000000, 10).solve());
    }
}
