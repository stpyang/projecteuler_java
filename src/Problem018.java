/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/17/13
 * Time: 1:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem018 implements MySolution {

    private final String[] triangle;

    public Problem018(String[] triangle) {
        this.triangle = triangle;
    }

    public static final String[] sample = new String[] {
            "3",
            "7 4",
            "2 4 6",
            "8 5 9 3",
    };

    public static final String[] input = new String[] {
            "75",
            "95 64",
            "17 47 82",
            "18 35 87 10",
            "20 04 82 47 65",
            "19 01 23 75 03 34",
            "88 02 77 73 07 63 67",
            "99 65 04 28 06 16 70 92",
            "41 41 26 56 83 40 80 70 33",
            "41 48 72 33 47 32 37 16 94 29",
            "53 71 44 65 25 43 91 52 97 51 14",
            "70 11 33 28 77 73 17 78 39 68 17 57",
            "91 71 52 38 17 14 91 43 58 50 27 29 48",
            "63 66 04 68 89 53 67 30 73 16 69 87 40 31",
            "04 62 98 27 23 09 70 98 73 93 38 53 60 04 23",
    };

    @Override
    public long solve() {
        int matrix[][] = new int[triangle.length][];
        for (int i = 0; i < triangle.length; ++i) {
            String[] line = triangle[i].split(" ");
            int[] row = new int[line.length];
            for (int j = 0; j < line.length; ++j) {
                row[j] = Integer.parseInt(line[j]);
            }
            matrix[i] = row;
        }

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                if (i == 0 && j == 0) {
                    matrix[i][j] += 0;
                } else if (j == 0) {
                    matrix[i][j] += matrix[i - 1][j];
                } else if (j == i) {
                    matrix[i][j] += matrix[i - 1][j - 1];
                } else {
                    matrix[i][j] += Math.max(matrix[i - 1][j - 1], matrix[i - 1][j]);
                }
            }
        }

        int result = 0;
        for (int p : matrix[matrix.length - 1]) {
            result = Math.max(p, result);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Problem018(input).solve());
    }
}
