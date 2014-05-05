/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/16/14
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem164Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N, int sumLimit) {
        long result = 0;
        // N digit numbers ending in i, j, k
        long[][][] solutions = new long[10][10][10];
        for(int i = 1; i < 10; ++i) {
            for (int j = 0; j <= sumLimit - i; ++j) {
                for (int k = 0; k <= sumLimit - i - j; ++k) {
                    solutions[i][j][k] = 1;
                }
            }
        }

        for (int n = 4; n <= N; ++n) {
            long[][][] newResult = new long[10][10][10];
            for(int i = 0; i < 10; ++i) {
                for(int j = 0; j <= sumLimit - i; ++j) {
                    for (int k = 0; k <= sumLimit - i - j; ++k) {
                        for(int l = 0; l < 10; ++l) {
                            newResult[i][j][k] += solutions[l][i][j];
                        }
                    }
                }
            }
            solutions = newResult;
        }

        for(int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                for(int k = 0; k < 10; ++k) {
                    result += solutions[i][j][k];
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(20, 9));
    }
}
