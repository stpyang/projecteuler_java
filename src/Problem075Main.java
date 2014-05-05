/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/2/13
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem075Main {

    private static void calculatePossibleWires(int[] numberOfWires, FibonacciBox parent) {
        if (parent.getSumOfSides() <= numberOfWires.length) {
            long L = parent.getSumOfSides();
            long j = L;
            while (j < numberOfWires.length) {
                ++numberOfWires[(int)j];
                j += L;
            }
            for (FibonacciBox child : parent.getChildren()) {
                calculatePossibleWires(numberOfWires, child);
            }
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N, int desiredNumberOfWires) {
        int result = 0;
        int[] numberOfWires = new int[1 + N];
        calculatePossibleWires(numberOfWires, FibonacciBox.SEED);

        for (int i : numberOfWires) {
            if (i == desiredNumberOfWires) {
                ++result;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(1500000, 1));
    }
}

