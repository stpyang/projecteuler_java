/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/21/13
 * Time: 10:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem033Main {
    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        // examples must be of the form ab/bc or ab/ca

        long numProduct = 1;
        long denomProduct = 1;

        for (int a = 1; a < 10; ++a) {
            for(int b = 0; b < 10; ++b) {
                if (a == b) {
                    continue;
                }
                for (int c = 0; c < 10; ++c) {
                    if (b == c) {
                        continue;
                    }
                    if (10 * a + b < 10 * b + c && c * (10 * a + b) == a * (10 * b + c)) {
                        numProduct *= 10 * a + b;
                        denomProduct *= 10 * b + c;
                    }
                    if (10 * a + b < 10 * c + a && c * (10 * a + b) == b * (10 * c + a)) {
                        numProduct *= 10 * a + b;
                        denomProduct *= 10 * c + a;
                    }
                }
            }
        }

        return denomProduct / EulerUtil.gcd(numProduct, denomProduct);
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
