/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/21/13
 * Time: 10:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem031Main {

    private static int dotProduct(int[] currency, int[] digits) {
        int sum = 0;
        for (int i = 0; i < digits.length; ++i) {
            sum += currency[i] * digits[i];
        }
        return sum;
    }

    private static void recursivelySolve(int N, int[] currency, int[] coefficients, int level, int[] result) {
        if (level == coefficients.length) {
            if (dotProduct(currency, coefficients) == N) {
                ++result[0];
            }
        } else {
            if (dotProduct(currency, coefficients) > N) {
                return;
            }
            for (int i = 0; i <= N / currency[level]; ++i) {
                int[] newDigits = coefficients.clone();
                newDigits[level] = i;
                recursivelySolve(N, currency, newDigits, level + 1, result);
            }
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        int[] currency = new int[] { 1, 2, 5, 10, 20, 50, 100, 200 };
        int[] coefficients = new int[currency.length];

        int[] result = new int[1];
        recursivelySolve(N, currency, coefficients, 0, result);

        return result[0];
    }

    public static void main(String[] args) {
        System.out.println(solve(200));
    }
}
