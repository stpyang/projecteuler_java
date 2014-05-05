import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/15/13
 * Time: 4:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem105Main {

    static boolean isSpecial(int[] A) {
        boolean isSpecial = true;

        Arrays.sort(A);

        Set<Integer> allSums = new HashSet<>();
        int allSumsSize = 0;

        int headSum = A[0];
        int tailSum = 0;
        int k;
        for (k = 0; k < A.length - 1; ++k) {
            allSums.addAll(Problem103Main.getSubsetSums(A, k));
            allSumsSize += EulerUtil.binomial(A.length, k);

            if ((allSums.size()) != allSumsSize) {
                isSpecial = false;
                break;
            }

            headSum += A[k + 1];
            tailSum += A[A.length - 1 - k];
            if (headSum <= tailSum) {
                isSpecial = false;
                break;
            }
        }
        return isSpecial;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(String filename) {
        try {
        long result = 0;
            File file = new File(filename);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] input = line.split(",");
                int n = input.length;
                int[] A = new int[n];
                for (int i = 0; i < n; ++i) {
                    A[i] = Integer.parseInt(input[i]);
                }

                if (isSpecial(A)) {
                    result += EulerUtil.sum(A);
                }
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solve("data/sets.txt"));
    }
}
