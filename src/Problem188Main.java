/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/8/14
 * Time: 10:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem188Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve(long base, long exponent, long modulus) {
        int[] totients = EulerUtil.getTotients((int) modulus);

        long e = 1;
        for (int i = 1; i < exponent; ++i) {
            e = EulerUtil.longPowModulo(base, e, totients[(int)modulus]);
        }

        return EulerUtil.longPowModulo(base, e, modulus);
    }

    public static void main(String[] args) {
        System.out.println(solve(1777, 1855, 100000000));
    }
}
