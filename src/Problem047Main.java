/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/14/14
 * Time: 12:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem047Main {

    private static boolean satisfies(int[] numberOfPrimeFactors, int index, int N) {
        for(int i = 0; i < N; ++i) {
            if (numberOfPrimeFactors[index + i] != N) {
                return false;
            }
        }
        return true;
    }

    public static long solve(int N) {
        int searchLimit = 10;
        while (true) {
            int[] numberOfPrimeFactors = new int[searchLimit + 1];
            for (int i = 2; i < numberOfPrimeFactors.length; ++i) {
                if (numberOfPrimeFactors[i] == 0) {
                    for(int  j = i; j < numberOfPrimeFactors.length; j += i) {
                        ++numberOfPrimeFactors[j];
                    }
                }
            }

            for (int i = 2; i < numberOfPrimeFactors.length - N; ++i) {
                if (satisfies(numberOfPrimeFactors, i, N)) {
                    return i;
                }
            }

            searchLimit *= 2;
        }
    }

    public static void main(String[] args) {
        System.out.println(solve(4));
    }
}
