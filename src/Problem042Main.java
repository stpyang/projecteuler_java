import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/7/14
 * Time: 11:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem042Main {

    static int score(String word) {
        int result = 0;
        word = word.toUpperCase();
        for (char c : word.toCharArray()) {
            result += c - 'A' + 1;
        }
        return result;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(String filename) {
        int result = 0;
        try {
            File file = new File(filename);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                line = line.replace("\"","");
                String[] words = line.split(",");
                for (String word : words) {
                    if (EulerUtil.isGeometricNumber(score(word), 3)) {
                        ++result;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve("data/words.txt"));
    }
}
