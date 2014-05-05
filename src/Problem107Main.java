import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/15/13
 * Time: 5:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem107Main {

    static final String[][] sample = new String[][] {
            "-,16,12,21,-,-,-".split(","),
            "16,-,-,17,20,-,-".split(","),
            "12,-,-,28,-,31,-".split(","),
            "21,17,28,-,18,19,23".split(","),
            "-,20,-,18,-,-,11".split(","),
            "-,-,31,19,-,-,27".split(","),
            "-,-,-,23,11,27,-".split(","),
    };

    private static int[][] parseMatrix(String[][] input, int N) {
        int[][] matrix = new int[N][];
        for (int i = 0; i < N; ++i) {
            if (input[i].length != N) {
                throw new IllegalArgumentException("input must be a N x N matrix");
            }
            matrix[i] = new int[N];
            for (int j = 0; j < N; ++j) {
                if (input[i][j].equals("-")) {
                    matrix[i][j] = Integer.MAX_VALUE;
                } else {
                    matrix[i][j] = Integer.parseInt(input[i][j]);
                }
            }
        }
        return matrix;
    }

    private static boolean hasFalse(boolean[] B) {
        for (boolean b : B) {
            if (!b) {
                return true;
            }
        }
        return false;
    }

    private static long prim(int[][] matrix, int N) {

        // initialization
        boolean[] Vnew = new boolean[N];
        long totalEdgeWeights = 0;

        // initial node
        int k = 0;
        while (matrix[0][k] == 0) {
            ++k;
        }
        Vnew[k] = true;

        while (hasFalse(Vnew)) {
            // find edge w minimal weight not in Vnew
            int[] e = new int[2];
            int minimalWeight = Integer.MAX_VALUE;
            for (int i = 0; i < N; ++i) {
                for (int j = i + 1; j < N; ++j) {
                    if ((Vnew[i] ^ Vnew[j]) && matrix[i][j] < minimalWeight) {
                        minimalWeight = matrix[i][j];
                        e[0] = i;
                        e[1] = j;
                    }
                }
            }

            totalEdgeWeights += minimalWeight;
            Vnew[e[0]] = true;
            Vnew[e[1]] = true;
        }

        return totalEdgeWeights;
    }

    private static long solve(int[][] matrix, int N) {
        if (N > matrix.length) {
            throw new IllegalArgumentException("matrix must be n x n");
        }
        int sum = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                if (matrix[i][j] < Integer.MAX_VALUE) {
                    sum += matrix[i][j];
                }
            }
        }
        return sum - prim(matrix, N);
    }

    static long solve(String[][] input, int N) {
        int[][] matrix = parseMatrix(input, N);

        return solve(matrix, N);
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(String fileName, int N) {
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            List<String[]> inputList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                inputList.add(row);
            }

            String[][] input = new String[inputList.size()][];
            for (int i = 0; i < inputList.size(); ++i) {
                input[i] = inputList.get(i);
            }

            int[][] matrix = parseMatrix(input, N);
            return solve(matrix, N);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solve("data/network.txt", 40));
    }
}
