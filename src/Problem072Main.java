/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/2/13
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem072Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        int[] totients = EulerUtil.getTotients(N);
        return EulerUtil.sum(totients) - 1;
    }

    public static void main(String[] args) {
        System.out.println(solve(1000000));
    }
}

