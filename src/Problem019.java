/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/17/13
 * Time: 3:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem019 implements MySolution {

    private final int firstYear;
    private final int lastYear;

    public Problem019(int firstYear, int lastYear) {
        this.firstYear = firstYear;
        this.lastYear = lastYear;
    }

    // this is copied from wikipedia
    private static final int[] TONDERING = {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};

    static int dayOfWeek(int year, int month, int day) {
        if (month < 3 ){
            --year;
        }
        return (year + year/4 - year/100 + year/400 + TONDERING[month - 1] + day) % 7;
    }

    @Override
    public long solve() {
        int result = 0;

        for (int year = firstYear; year < lastYear; ++year) {
            for (int month = 1; month <= 12; ++month) {
                if (dayOfWeek(year, month, 1) == 0) {
                    ++result;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Problem019(1901, 2001).solve());
    }
}