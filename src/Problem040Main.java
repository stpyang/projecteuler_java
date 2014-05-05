import gnu.trove.TIntArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/6/14
 * Time: 5:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem040Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {

        TIntArrayList digits = new TIntArrayList();

        for(int i = 1; digits.size() <= EulerUtil.longPow(10, N - 1); ++i) {
            int j = i;
            int numberOfDigits = EulerUtil.getNumberOfDigits(i);
            int pow10 = (int)EulerUtil.longPow(10, numberOfDigits - 1);
            for (int k = 0; k < numberOfDigits; ++k) {
                int l = j / pow10;
                digits.add(l);
                j = j % pow10;
                pow10 /= 10;
            }
        }

        int result = 1;
        int pow10 = 1;
        for(int i = 0; i < N; ++i) {
            result *= digits.get(pow10 - 1);
            pow10 *= 10;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(7));
    }
}
