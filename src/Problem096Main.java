import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/10/13
 * Time: 4:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem096Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve(String fileName) {
        int result = 0;

        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            int r = 0;
            String[] rows = new String[9];
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Grid")) {
                    continue;
                }
                rows[r++] = line;
                if (r == 9) {
                    Soduku s = new Soduku(rows);
                    s.solve();
                    result += 100 * s.cells[0][0] + 10 * s.cells[0][1] + s.cells[0][2];
                    r = 0;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve("data/sudoku.txt"));
    }
}
