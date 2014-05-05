/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/8/13
 * Time: 5:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem091Main {

    static class Coordinates extends Pair<Double> {
        public Coordinates(double a, double b) {
            super(a, b);
            if (a < 0.0 || b < 0.0) {
                throw new IllegalArgumentException("No negative coordinates!");
            }
        }
    }

    private static Coordinates midpoint(Coordinates p1, Coordinates p2) {
        return new Coordinates((p1.a + p2.a) / 2, (p1.b + p2.b) / 2);
    }

    private static double distance(Coordinates p1, Coordinates p2) {
        double z2 = (p1.a - p2.a) * (p1.a - p2.a) + (p1.b - p2.b) * (p1.b - p2.b);
        return Math.sqrt(z2);
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        int result = 0;
        Coordinates p0 = new Coordinates(0.0, 0.0);
        for (int i = 0; i <= N; ++i) {
            for (int j = 0; j <= N; ++j) {
                if (i == 0 && j == 0) {
                    continue;
                }
                Coordinates p1 = new Coordinates(i, j);
                for (int k = i; k <= N; ++k) {
                    for (int l = 0; l <= N; ++l) {
                        if (k == i && l <= j) {
                            continue;
                        }
                        Coordinates p2 = new Coordinates(k, l);
                        double d0 = distance(p1, p2);
                        double d1 = distance(p0, p2);
                        double d2 = distance(p0, p1);
                        Coordinates m0 = midpoint(p1, p2);
                        Coordinates m1 = midpoint(p0, p2);
                        Coordinates m2 = midpoint(p0, p1);

                        if (Math.abs(2 * distance(p0, m0) - d0) < 10e-8 ||
                                Math.abs(2 * distance(p1, m1) - d1) < 10e-8 ||
                                Math.abs(2 * distance(p2, m2) - d2) < 10e-8
                                ) {
                            ++result;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(50));
    }
}