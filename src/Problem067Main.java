import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/17/13
 * Time: 2:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem067Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve(String fileName) {
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            List<String> inputList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                inputList.add(line);
            }
            return new Problem018(inputList.toArray(new String[inputList.size()])).solve();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solve("data/triangle.txt"));
    }
}
