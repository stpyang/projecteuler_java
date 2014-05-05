/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/6/13
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem004 implements MySolution {

    private final int N;

    public Problem004(int n) {
        N = n;
    }

    @Override
    public long solve() {
        int minN = (int)Math.round(Math.pow(10, N - 1));
        int maxN = (int)Math.round(Math.pow(10, N));
        int result = 0;

        for(int i = minN; i < maxN; ++i) {
            for(int j = minN; j < maxN; ++j) {
                if (EulerUtil.isPalindrome(i * j)) result = Math.max(result, i * j);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Problem004(3).solve());
    }
}
