import gnu.trove.TIntIntHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/6/14
 * Time: 3:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem039Main {

    private static void recurse(FibonacciBox parent, int limit, TIntIntHashMap solutions) {
        long sumOfSides = parent.getSumOfSides();
        if (sumOfSides <= limit) {
            int i = (int)sumOfSides;
            while (i <= limit) {
                solutions.put(i, solutions.get(i) + 1);
                i += sumOfSides;
            }
            for (FibonacciBox child : parent.getChildren()) {
                recurse(child, limit, solutions);
            }
        }
    }

    public static long solve(int limit) {
        long result = 0;
        int maxSolutions = 0;

        TIntIntHashMap solutions = new TIntIntHashMap();
        recurse(FibonacciBox.SEED, limit, solutions);

        for (int key : solutions.keys()) {
            if (solutions.get(key) > maxSolutions) {
                maxSolutions = solutions.get(key);
                result = key;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(1000));
    }
}
