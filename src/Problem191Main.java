/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/6/14
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem191Main {

    public static long solve(int N) {
        long result = 0;
        // Prize strings of length up to N, with up to 3 absences and up to 1 late
        int[][] prizeStrings = new int[3][2];

        prizeStrings[0][0] = 1;
        for(int i = 1; i <= N; ++i) {
            int[][] newPrizeStrings = new int[3][2];
            newPrizeStrings[2][0] = prizeStrings[1][0];
            newPrizeStrings[1][0] = prizeStrings[0][0];
            newPrizeStrings[0][0] = prizeStrings[0][0] + prizeStrings[1][0] + prizeStrings[2][0];
            newPrizeStrings[2][1] = prizeStrings[1][1];
            newPrizeStrings[1][1] = prizeStrings[0][1];
            newPrizeStrings[0][1] = prizeStrings[0][1] + prizeStrings[1][1] + prizeStrings[2][1]
                                + prizeStrings[2][0] + prizeStrings[1][0] + prizeStrings[0][0];
            prizeStrings = newPrizeStrings;
        }

        result += EulerUtil.sum(prizeStrings[0]) + EulerUtil.sum(prizeStrings[1]) + EulerUtil.sum(prizeStrings[2]);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(30));
    }
}
