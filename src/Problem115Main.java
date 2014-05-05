/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/21/13
 * Time: 12:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem115Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve(int m, int numberOfSolutions) {
        Problem114Main.initializeMaps(m);

        int i = 3;
        while (Problem114Main.fillCount(m, i) < numberOfSolutions) {
            ++i;
        }
        return i;
    }

    public static void main(String[] args) {
        System.out.println(solve(50, 1000000));
    }
}
