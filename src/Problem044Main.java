/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/10/14
 * Time: 1:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem044Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        long k = 1;
        while (true) {
            long kPentagonalNumber = EulerUtil.getGeometricNumber(k, 5);
            for(int j = 1; j < k; ++ j) {
                long jPentagonalNumber = EulerUtil.getGeometricNumber(j, 5);
                if (EulerUtil.isGeometricNumber(kPentagonalNumber - jPentagonalNumber, 5) && EulerUtil.isGeometricNumber(kPentagonalNumber + jPentagonalNumber, 5)) {
                    return kPentagonalNumber - jPentagonalNumber;
                }
            }
            ++k;
        }
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
