/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/4/13
 * Time: 4:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem001 implements MySolution {

    private final int N;
    private final int a;
    private final int b;

    public Problem001(int n, int a, int b) {
        N = n;
        this.a = a;
        this.b = b;
    }

    private static long getTriangularNumber(int n) {
        return EulerUtil.getGeometricNumber(n, 3);
    }

    @Override
    public long solve() {
        // basic use of the inclusion/exclusion principle
        return a * getTriangularNumber((N - 1)/ a) + b * getTriangularNumber((N - 1)/ b) - a * b * getTriangularNumber((N - 1)/ a / b);
    }

    public static void main(String[] args) {
        System.out.println(new Problem001(1000, 3, 5).solve());
    }
}
