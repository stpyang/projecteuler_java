import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/18/13
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem023 implements MySolution {

    private static final int UPPER_LIMIT = 28123;

    @Override
    public long solve() {
        int[] sumOfDivisors = EulerUtil.getSumsOfDivisorsUpTo(UPPER_LIMIT);
        List<Integer> abundantNumbers = new ArrayList<>();
        for (int i = 0; i <= UPPER_LIMIT; ++i) {
            if (sumOfDivisors[i] > i) {
                abundantNumbers.add(i);
            }
        }

        int[] sumOfTwoAmicableNumbers = new int[1 + UPPER_LIMIT];
        for (int i = 0; i < abundantNumbers.size(); ++i) {
            for (int j = 0; j <= i; ++j) {
                int a = abundantNumbers.get(i);
                int b = abundantNumbers.get(j);
                if (a + b > UPPER_LIMIT) {
                    break;
                }
                sumOfTwoAmicableNumbers[a + b] = a + b;
            }
        }

        return UPPER_LIMIT * (UPPER_LIMIT + 1) / 2 - EulerUtil.sum(sumOfTwoAmicableNumbers);
    }

    public static void main(String[] args) {
        System.out.println(new Problem023().solve());
    }
}
