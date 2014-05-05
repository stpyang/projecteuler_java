/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/31/13
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem179Main {
    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        long result = 0;

        int[] numberOfDivisors = new int[1 + N];
        for(int i = 1; i < numberOfDivisors.length; ++i) {
            int j = i;
            while(j < numberOfDivisors.length) {
                ++numberOfDivisors[j];
                j += i;
            }
        }

        for(int i = 1; i < numberOfDivisors.length; ++i) {
            if (numberOfDivisors[i] == numberOfDivisors[i - 1]) {
                ++result;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(10000000));
    }
}
