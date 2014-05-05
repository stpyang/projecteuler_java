/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/10/13
 * Time: 4:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class Soduku {

    final int[][] cells;

    public Soduku(String[] rows) {
        if (rows.length != 9) {
            throw new IllegalArgumentException("Soduku must have 9 rows");
        }
        cells = new int[9][9];
        for (int i = 0; i < 9; ++i) {
            if (rows[i].length() != 9) {
                throw new IllegalArgumentException("Soduku must have 9 columns");
            }
            for (int j = 0; j < 9; ++j) {
                cells[i][j] = Integer.parseInt(rows[i].substring(j, j + 1));
            }
        }
    }

    boolean solve() {
        return solve(0, 0);
    }

    private boolean solve(int i, int j) {
        if (i == 9) {
            i = 0;
            if (++j == 9)
                return true;
        }
        if (cells[i][j] != 0)  // skip filled cells
            return solve(i + 1,j);

        for (int val = 1; val <= 9; ++val) {
            if (legal(i, j, val)) {
                cells[i][j] = val;
                if (solve(i + 1, j))
                    return true;
            }
        }
        cells[i][j] = 0; // reset on backtrack
        return false;
    }

    boolean legal(int i, int j, int val) {
        for (int k = 0; k < 9; ++k)  // row
            if (val == cells[k][j])
                return false;

        for (int k = 0; k < 9; ++k) // col
            if (val == cells[i][k])
                return false;

        int boxRowOffset = (i / 3) * 3;
        int boxColOffset = (j / 3) * 3;
        for (int k = 0; k < 3; ++k) // box
            for (int m = 0; m < 3; ++m)
                if (val == cells[boxRowOffset + k][boxColOffset + m])
                    return false;

        return true; // no violations, so it's legal
    }

    void writeMatrix() {
        for (int i = 0; i < 9; ++i) {
            if (i % 3 == 0)
                System.out.println(" -----------------------");
            for (int j = 0; j < 9; ++j) {
                if (j % 3 == 0) System.out.print("| ");
                System.out.print(cells[i][j] == 0
                        ? " "
                        : Integer.toString(cells[i][j]));

                System.out.print(' ');
            }
            System.out.println("|");
        }
        System.out.println(" -----------------------");
    }

    public static void main(String[] args) {
        String[] rows = new String[] {
                "003020600",
                "900305001",
                "001806400",
                "008102900",
                "700000008",
                "006708200",
                "002609500",
                "800203009",
                "005010300",
        };
        Soduku s = new Soduku(rows);
        s.writeMatrix();
        if (s.solve())    // solves in place
            s.writeMatrix();
        else
            System.out.println("NONE");
    }
}
