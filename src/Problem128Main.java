/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 2/3/14
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem128Main {

    /*
    public static int getNthStartingDigit(int n) {
        if (n < 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return 3 * n * n - 9 * n + 8;
        }
    }
    */

    private static int pd(PrimeSieve[] primeSieve, int... neighborDiffs) {
        int result = 0;
        for (int i : neighborDiffs) {
            if (i >= primeSieve[0].getUpperLimit()) {
                primeSieve[0] = new PrimeSieve(Math.max(2 * primeSieve[0].getUpperLimit(), i));
            }
            if (primeSieve[0].isPrime(i)) {
                ++result;
            }
        }
        return result;
    }

    public static long solve(int value, int N) {
        PrimeSieve[] primeSieve = new PrimeSieve[] { new PrimeSieve(N) };

        int seqeunceNumber = 1;

        // first layer
        int layer = 1;
        if (pd(primeSieve, 4, 3, 2, 1, 6, 5) == value) { ++seqeunceNumber; } // n = 1

        // second hexagonal layer
        ++layer;
        if (pd(primeSieve, 1, 1, 7, 6, 12, 5) == value) { ++seqeunceNumber; } // n = 2
        if (pd(primeSieve, 1, 8, 7, 6, 1, 2) == value) { ++seqeunceNumber; } // n = 3
        if (pd(primeSieve, 9, 8, 7, 1, 3, 1) == value) { ++seqeunceNumber; } // n = 4
        if (pd(primeSieve, 9, 8, 1, 4, 1, 10) == value) { ++seqeunceNumber; } // n = 5
        if (pd(primeSieve, 9, 1, 5, 1, 11, 10) == value) { ++seqeunceNumber; } // n = 6
        if (pd(primeSieve, 1, 6, 5, 12, 11, 10) == value) { ++seqeunceNumber; } // n = 7

        // third hexagonal layer and beyond
        ++layer;
        long n = 8;
        while (seqeunceNumber < N) {
            // smallest diget in a hexagonal layer
//            if (pd(isPrime, 6 * layer - 12, 1, 6 * layer - 5, 6 * layer - 6, 12 * layer - 7 , 6 * layer - 7) == value) { ++seqeunceNumber; }
            if (pd(primeSieve, 6 * layer - 5, 12 * layer - 7 , 6 * layer - 7) == value) { ++seqeunceNumber; }
            if (seqeunceNumber >= N) { return n + seqeunceNumber - N; }
            ++n;

            // side 1 -- working clockwise
//            if (pd(isPrime, 6 * layer - 12, 1, 6 * layer - 5, 6 * layer - 6, 1 , 6 * layer - 11) == value) { seqeunceNumber += (layer - 2); }
            if (pd(primeSieve, 6 * layer - 5, 6 * layer - 11) == value) { seqeunceNumber += (layer - 2); }
            if (seqeunceNumber >= N) { return n + seqeunceNumber - N; }
            n += (layer - 2);

            // upper left hand corner
//            if (pd(isPrime, 1, 6 * layer - 4, 6 * layer - 5, 6 * layer - 6, 1, 6 * layer - 11) == value) { ++seqeunceNumber; }
            if (pd(primeSieve, 6 * layer - 5, 6 * layer - 11) == value) { ++seqeunceNumber; }
            if (seqeunceNumber >= N) { return n + seqeunceNumber - N; }
            ++n;

            // left side
//            if (pd(isPrime, 1, 6 * layer - 4, 6 * layer - 5, 1, 6 * layer - 10, 6 * layer - 11) == value) { seqeunceNumber += (layer - 2); }
            if (pd(primeSieve, 6 * layer - 5, 6 * layer - 11) == value) { seqeunceNumber += (layer - 2); }
            if (seqeunceNumber >= N) { return n + seqeunceNumber - N; }
            n += (layer - 2);

            // bottom left hand corner
//            if (pd(isPrime, 6 * layer - 3, 6 * layer - 4, 6 * layer - 5, 1, 6 * layer - 10, 1) == value) { ++seqeunceNumber; }
            if (pd(primeSieve, 6 * layer - 5) == value) { ++seqeunceNumber; }
            if (seqeunceNumber >= N) { return n + seqeunceNumber - N; }
            ++n;

            // bottom left hand side
//            if (pd(isPrime, 6 * layer - 3, 6 * layer - 4, 1, 6 * layer - 9, 6 * layer - 10, 1) == value) { seqeunceNumber += (layer - 2); }
            if (seqeunceNumber >= N) { return n + seqeunceNumber - N; }
            n += (layer - 2);

            // bottom corner
//            if (pd(isPrime, 6 * layer - 3, 6 * layer - 4, 1, 6 * layer - 9, 1, 6 * layer - 2) == value) { ++seqeunceNumber; }
            if (seqeunceNumber >= N) { return n + seqeunceNumber - N; }
            ++n;

            // bottom right hand side
//            if (pd(isPrime, 6 * layer - 3, 1, 6 * layer - 8, 6 * layer - 9, 1, 6 * layer - 2) == value) { seqeunceNumber += (layer - 2); }
            if (seqeunceNumber >= N) { return n + seqeunceNumber - N; }
            n += (layer - 2);

            // bottom right hand corner
//            if (pd(isPrime, 6 * layer - 3, 1, 6 * layer - 8, 1, 6 * layer - 1, 6 * layer - 2) == value) { ++seqeunceNumber; }
            if (pd(primeSieve, 6 * layer - 1) == value) { ++seqeunceNumber; }
            if (seqeunceNumber >= N) { return n + seqeunceNumber - N; }
            ++n;

            // right side
//            if (pd(isPrime, 1, 6 * layer - 7, 6 * layer - 8, 1, 6 * layer - 1, 6 * layer - 2) == value) { seqeunceNumber += (layer - 2); }
            if (pd(primeSieve, 6 * layer - 7, 6 * layer - 1) == value) { seqeunceNumber += (layer - 2); }
            if (seqeunceNumber >= N) { return n + seqeunceNumber - N; }
            n += (layer - 2);

            // upper right hand corner
//            if (pd(isPrime, 1, 6 * layer - 7, 1, 6 * layer, 6 * layer - 1, 6 * layer - 2) == value) { ++seqeunceNumber; }
            if (pd(primeSieve,6 * layer - 7, 6 * layer - 1) == value) { ++seqeunceNumber; }
            if (seqeunceNumber >= N) { return n + seqeunceNumber - N; }
            ++n;

            // upper right hand side
//            if (pd(isPrime, 6 * layer - 6, 6 * layer - 7, 1, 6 * layer, 6 * layer - 1, 1) == value) { seqeunceNumber += (layer - 3); }
            if (pd(primeSieve,6 * layer - 7, 6 * layer - 1) == value) { seqeunceNumber += (layer - 3); }
            if (seqeunceNumber >= N) { return n + seqeunceNumber - N; }
            n += (layer - 3);

            // largest digit in hexagonal layer
//            if (pd(isPrime, 6 * layer - 6, 12 * layer - 19, 6 * layer - 7, 6 * layer, 6 * layer - 1, 1) == value) { ++seqeunceNumber; }
            if (pd(primeSieve, 12 * layer - 19, 6 * layer - 7, 6 * layer - 1) == value) { ++seqeunceNumber; }
            if (seqeunceNumber >= N) { return n + seqeunceNumber - N; }
            ++n;

            ++layer;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solve(3, 2000));
    }
}
