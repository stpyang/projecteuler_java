/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/14/14
 * Time: 10:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem231Main {

    @SuppressWarnings("WeakerAccess")
    public static long solve(int  n, int k) {
        long result = 0;

        k = Math.min(k, n - k);

        long[] sumOfPrimeFactors = new long[n + 1];
        for (long i = 2; i < sumOfPrimeFactors.length; ++i) {
            if (sumOfPrimeFactors[(int)i] == 0) {
                for (long powI = i; powI < sumOfPrimeFactors.length; powI *= i) {
                    for (long j = powI; j < sumOfPrimeFactors.length; j += powI) {
                        sumOfPrimeFactors[(int)j] += i;
                    }
                }
            }
        }

        for (int i = 0; i < k; ++i) {
            result += sumOfPrimeFactors[n - i] - sumOfPrimeFactors[i + 1];
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(10, 3));
        System.out.println(solve(20000000, 15000000));
    }
}
