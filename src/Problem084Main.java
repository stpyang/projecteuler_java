import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/5/13
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem084Main {

    private static final int GO = 0;
    private static final int CC1 = 2;
    private static final int R1 = 5;
    private static final int CH1 = 7;
    private static final int JAIL = 10;
    private static final int C1 = 11;
    private static final int U1 = 12;
    private static final int R2 = 15;
    private static final int CC2 = 17;
    private static final int CH2 = 22;
    private static final int E3 = 24;
    private static final int R3 = 25;
    private static final int U2 = 28;
    private static final int G2J = 30;
    private static final int CC3 = 33;
//    static final int R4 = 35;
    private static final int CH3 = 36;
    private static final int H2 = 39;

    private static double[][] iterate(double[][] squareMatrix, int N) {
        double[][] newMatrix = new double[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                for (int k = 0; k < N; ++k) {
                    newMatrix[i][j] += squareMatrix[i][k] * squareMatrix[k][j];
                }
            }
        }
        return newMatrix;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        double[][] rolls = new double[40][40];
        for (int i = 0; i < 40; ++i) {
            rolls[i][(i + 2) % 40] = (double) 1 / (double) 16;
            rolls[i][(i + 3) % 40] = (double) 2 / (double) 16;
            rolls[i][(i + 4) % 40] = (double) 3 / (double) 16;
            rolls[i][(i + 5) % 40] = (double) 4 / (double) 16;
            rolls[i][(i + 6) % 40] = (double) 3 / (double) 16;
            rolls[i][(i + 7) % 40] = (double) 2 / (double) 16;
            rolls[i][(i + 8) % 40] = (double) 1 / (double) 16;
        }

        // go to jail
        for (int i = 0; i < 40; ++i) {
            rolls[i][JAIL] += rolls[i][G2J];
            rolls[i][G2J] = 0;
        }

        // chance

        for (int i = 0; i < 40; ++i) {
            double ch1 = rolls[i][CH1];
            rolls[i][GO] += ch1 / 16;
            rolls[i][JAIL] += ch1 / 16;
            rolls[i][C1] += ch1 / 16;
            rolls[i][E3] += ch1 / 16;
            rolls[i][H2] += ch1 / 16;
            rolls[i][R1] += ch1 / 16;
            rolls[i][R2] += 2 * ch1 / 16;
            rolls[i][U1] += ch1 / 16;
            rolls[i][CH1 - 3] += ch1 / 16;
            rolls[i][CH1] = 3 * ch1 / 8;

            double ch2 = rolls[i][CH2];
            rolls[i][GO] += ch2 / 16;
            rolls[i][JAIL] += ch2 / 16;
            rolls[i][C1] += ch2 / 16;
            rolls[i][E3] += ch2 / 16;
            rolls[i][H2] += ch2 / 16;
            rolls[i][R1] += ch2 / 16;
            rolls[i][R3] += 2 * ch2 / 16;
            rolls[i][U2] += ch2 / 16;
            rolls[i][CH2 - 3] += ch2 / 16;
            rolls[i][CH2] = 3 * ch2 / 8;

            double ch3 = rolls[i][CH3];
            rolls[i][GO] += ch3 / 16;
            rolls[i][JAIL] += ch3 / 16;
            rolls[i][C1] += ch3 / 16;
            rolls[i][E3] += ch3 / 16;
            rolls[i][H2] += ch3 / 16;
            rolls[i][R1] += ch3 / 16;
            rolls[i][R1] += 2 * ch3 / 16;
            rolls[i][U1] += ch3 / 16;
            rolls[i][CH3 - 3] += ch3 / 16;
            rolls[i][CH3] = 3 * ch3 / 8;
        }

        // community chest
        for (int i = 0; i < 40; ++i) {
            double cc1 = rolls[i][CC1];
            rolls[i][GO] += cc1 / 16;
            rolls[i][JAIL] += cc1 / 16;
            rolls[i][CC1] = 7 * cc1 / 8;

            double cc2 = rolls[i][CC2];
            rolls[i][GO] += cc2 / 16;
            rolls[i][JAIL] += cc2 / 16;
            rolls[i][CC2] = 7 * cc2 / 8;

            double cc3 = rolls[i][CC3];
            rolls[i][GO] += cc3 / 16;
            rolls[i][JAIL] += cc3 / 16;
            rolls[i][CC3] = 7 * cc3 / 8;
        }

        for (int i = 0; i < 10; ++i) {
            rolls = iterate(rolls, 40);
        }

        SortedMap<Double, Integer> relativeLikelihoods = new TreeMap<>();

        for (int j = 0; j < 40; ++j) {
            double r = 0;
            for (int i = 0; i < 40; ++i) {
                r += rolls[i][j];
            }
            relativeLikelihoods.put(r, j);
        }

        int result = 0;
        for (int i = 0; i < 3; ++i) {
            result *= 100;
            result += relativeLikelihoods.get(relativeLikelihoods.lastKey());
            relativeLikelihoods.remove(relativeLikelihoods.lastKey());
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
