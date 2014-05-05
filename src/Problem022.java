import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/18/13
 * Time: 4:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem022 implements MySolution {

    private final String fileName;

    public Problem022(String fileName) {
        this.fileName = fileName;
    }

    static int wordScore(String s) {
        int result = 0;
        for (char c : s.toCharArray()) {
            result += c - 'A' + 1;
        }
        return result;
    }

    @Override
    public long solve() {
        try {
            int result = 0;
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine().replace("\"","");
            String[] words = line.split(",");
            Arrays.sort(words);
            int wordNumber = 1;
            for (String word : words) {
                result += wordScore(word) * wordNumber++;
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Problem022("data/names.txt").solve());
    }
}
