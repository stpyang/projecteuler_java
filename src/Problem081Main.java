import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/5/13
 * Time: 1:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem081Main {

    static int[][] readMatrixFromFile(String fileName) {
        try {
            List<int[]> lines = new ArrayList<>();
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] entriesString = line.split(",");
                int[] entries = new int[entriesString.length];
                for (int i = 0; i < entries.length; ++i) {
                    entries[i] = Integer.valueOf(entriesString[i]);
                }
                lines.add(entries);
            }

            int[][] result = new int[lines.size()][];
            for (int i = 0; i < lines.size(); ++i) {
                result[i] = lines.get(i);
            }

            return result;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;
    }

    public static long solve(int[][] matrix, int M, int N) {
        long[][] minimalPaths = new long[M][N];
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (i == 0 && j == 0) {
                    minimalPaths[i][j] = matrix[i][j];
                } else if (i == 0) {
                    minimalPaths[i][j] = matrix[i][j] + minimalPaths[i][j - 1];
                } else if (j == 0) {
                    minimalPaths[i][j] = matrix[i][j] + minimalPaths[i - 1][j];
                } else {
                    minimalPaths[i][j] = matrix[i][j] + Math.min(minimalPaths[i - 1][j], minimalPaths[i][j - 1]);
                }
            }
        }
        return minimalPaths[M - 1][N - 1];
    }


    public static void main(String[] args) {
        int[][] matrix = readMatrixFromFile("data/matrix.txt");
        System.out.println(solve(matrix, 80, 80));
    }
}

