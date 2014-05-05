import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/13/13
 * Time: 4:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem009 implements MySolution {

    private final int N;

    public Problem009(int N) {
        this.N = N;
    }

    @Override
    public long solve() {
        List<FibonacciBox> fibonacciBoxes = new ArrayList<>();
        fibonacciBoxes.add(FibonacciBox.SEED);

        while (true) {
            List<FibonacciBox> temp = new ArrayList<>();
            for (FibonacciBox f : fibonacciBoxes) {
                if (N % f.getSumOfSides() == 0) {
                    // once we find the unique solution we know we can quit
                    long k = N / f.getSumOfSides();
                    long[] sides = f.getPythagoreanTriple();
                    return k * sides[0] * k * sides[1] * k * sides[2];
                }
                temp.addAll(Arrays.asList(f.getChildren()));
            }
            fibonacciBoxes = temp;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Problem009(1000).solve());
    }
}
