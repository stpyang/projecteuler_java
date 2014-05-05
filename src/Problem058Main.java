/**
 * Created with IntelliJ IDEA.
 * User: syang
 * Date: 2/19/14
 * Time: 2:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem058Main {

    public static long solve() {
        int entry = 1;
        int sideLength = 3;
        int numPrimes = 0;

        while (true) {
            int diagonalLength = 2 * sideLength - 1;
            for (int j = 0; j < 3; ++j) {
                entry += sideLength - 1;
                if (EulerUtil.isPrimeMR(entry)) {
                    ++numPrimes;
                }
            }

            entry += sideLength - 1;

            if (10 * numPrimes < diagonalLength) {
                return sideLength;
            }

            sideLength += 2;
        }
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
