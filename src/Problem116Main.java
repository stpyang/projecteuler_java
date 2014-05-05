/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/21/13
 * Time: 12:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem116Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve(int numBlocks) {
        long[] red = new long[numBlocks + 1];
        red[1] = 1;
        red[2] = 2;
        for(int i = 3; i <= numBlocks; ++i) {
            red[i] = red[i - 1] + red[i - 2];
        }

        long[] green = new long[numBlocks + 1];
        green[1] = 1;
        green[2] = 1;
        green[3] = 2;
        for (int i = 4; i <= numBlocks; ++i) {
            green[i] = green[i - 1] + green[i - 3];
        }

        long[] blue = new long[numBlocks + 1];
        blue[1] = 1;
        blue[2] = 1;
        blue[3] = 1;
        blue[4] = 2;
        for (int i = 5; i <= numBlocks; ++i) {
            blue[i] = blue[i - 1] + blue[i - 4];
        }

        return red[numBlocks] + blue[numBlocks] + green[numBlocks] - 3;
    }

    public static void main(String[] args) {
        System.out.println(solve(50));
    }
}
