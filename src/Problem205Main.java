/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/31/13
 * Time: 8:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem205Main {

    private static int[] roll(int[] person, int[] die) {
        int[] result = new int[person.length];
        for(int i = 0; i < person.length - die.length + 1; ++i) {
            for(int j = 0; j < die.length; ++j) {
                result[i + j] += person[i] * die[j];
            }
        }
        return result;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int p, int c) {
        double result = 0;

        int[] pyramidalPete = new int[37];
        pyramidalPete[0] = 1;
        int[] cubicColin = new int[37];
        cubicColin[0] = 1;
        int[] pyramid = new int[] { 0, 1, 1, 1, 1 };
        int[] cube = new int[] { 0, 1, 1, 1, 1, 1, 1 };

        for(int i = 0; i < p; ++i) {
            pyramidalPete = roll(pyramidalPete, pyramid);
        }
        for(int i = 0; i < c; ++i) {
            cubicColin = roll(cubicColin, cube);
        }

        for(int i = 0 ; i < pyramidalPete.length; ++i) {
            for(int j = 0; j < i; ++j) {
                result += pyramidalPete[i] * cubicColin[j];
            }
        }

        result /= EulerUtil.sum(pyramidalPete);
        result /= EulerUtil.sum(cubicColin);

        return Math.round(result * 10000000);
    }

    public static void main(String[] args) {
        System.out.println(solve(9, 6));
    }
}
