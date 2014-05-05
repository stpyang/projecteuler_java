import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/4/13
 * Time: 2:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem078Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        List<Integer> P = new ArrayList<>();
        P.add(1);

        int i = 1;
        while (true) {
            int next = 0;
            for (int j = 1; j * (3 * j - 1) / 2 <= i; ++j) {
                int p = P.get(i - j * (3 * j - 1) / 2);
                next += ((j & 1) == 0 ? -p : p);
            }
            for (int j = -1; j * (3 * j - 1) / 2 <= i; --j)  {
                int p = P.get(i - j * (3 * j - 1) / 2);
                next += ((j & 1) == 0 ? -p : p);
            }
            P.add(next % N);

            if (next % N ==0) {
                return i;
            }

            ++i;
        }
    }

    public static void main(String[] args) {
        System.out.println(solve(1000000));
    }
}
