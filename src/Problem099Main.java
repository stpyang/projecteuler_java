import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/20/13
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem099Main {
    @SuppressWarnings("WeakerAccess")
    public static long solve(String fileName) {
        int result = 0;
        double maxLog = 0;
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                String[] baseExp = line.split(",");
                double d = Math.log(Double.parseDouble(baseExp[0])) * Integer.parseInt(baseExp[1]);
                if (maxLog < d) {
                    maxLog = d;
                    result = lineNumber;
                }
                ++lineNumber;
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solve("data/base_exp.txt"));
    }
}
