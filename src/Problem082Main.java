import org.apache.commons.lang.ArrayUtils;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/5/13
 * Time: 1:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem082Main {

    public static long solve(int[][] matrix, int M, int N) {
        long[][] minimalPaths = new long[M][N];
        for (int j = 0; j < N; ++j) {
            for (int i = 0; i < M; ++i) {
                if (j == 0) {
                    minimalPaths[i][j] = matrix[i][j];
                } else {
                    long newMinimalPath = Long.MAX_VALUE;
                    for (int k = 0; k < M; ++k) {
                        if (k < i) {
                            int[][] tempMatrix = new int[i - k + 1][2];
                            for (int m = k; m <= i; ++m) {
                                tempMatrix[m - k][0] = (int)minimalPaths[m][j - 1];
                                tempMatrix[m - k][1] = matrix[m][j];
                            }
                            long pathSum = Problem081Main.solve(tempMatrix, i-k+1, 2);
                            newMinimalPath = pathSum < newMinimalPath ? pathSum : newMinimalPath;
                        } else if (k == i) {
                            long pathSum = minimalPaths[i][j-1] + matrix[i][j];
                            newMinimalPath = pathSum < newMinimalPath ? pathSum : newMinimalPath;
                        } else if (k > i) {
                            int[][] tempMatrix = new int[k - i + 1][2];
                            for (int m = i; m <= k; ++m) {
                                tempMatrix[m - i][0] = (int)minimalPaths[m][j - 1];
                                tempMatrix[m - i][1] = matrix[m][j];
                            }
                            ArrayUtils.reverse(tempMatrix);
                            long pathSum = Problem081Main.solve(tempMatrix, k - i + 1, 2);
                            newMinimalPath = pathSum < newMinimalPath ? pathSum : newMinimalPath;
                        }
                    }
                    minimalPaths[i][j] = newMinimalPath;
                }
            }
        }

        long result = Long.MAX_VALUE;
        for (int i = 0; i < M; ++i) {
            result = minimalPaths[i][N - 1] < result ? minimalPaths[i][N - 1] : result;
        }
        return result;
    }


    public static void main(String[] args) {
        int[][] matrix = Problem081Main.readMatrixFromFile("data/matrix.txt");
        System.out.println(solve(matrix, 80, 80));
    }
}
