/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/10/14
 * Time: 2:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem045Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        long i = 287;
        while (true) {
            long iTriangularNumber = EulerUtil.getGeometricNumber(i, 3);
            if (EulerUtil.isGeometricNumber(iTriangularNumber, 5) && EulerUtil.isGeometricNumber(iTriangularNumber, 6)) {
                return iTriangularNumber;
            } else {
                ++i;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
