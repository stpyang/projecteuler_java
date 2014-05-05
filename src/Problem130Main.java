import gnu.trove.TIntArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/22/14
 * Time: 6:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem130Main
{
    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        TIntArrayList result = new TIntArrayList();
        int i = 2;
        while (result.size() < N) {
            if (i % 2 != 0 && i % 5 != 0 && !EulerUtil.isPrime(i)) {
                if ((i - 1)  % Problem129Main.A(i) == 0) {
                    result.add(i);
                }
            }
            ++i;
        }
        return EulerUtil.sum(result.toNativeArray());
    }

    public static void main(String[] args) {
        System.out.println(solve(25));
    }
}
