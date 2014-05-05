/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/17/13
 * Time: 1:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem017 implements MySolution {

    private final int N;

    public Problem017(int N) {
        this.N = N;
    }

    private static String write(int n) {
        if (n == 0) return "";
        if (n < 20) {
            switch (n) {
                case 1 : return "one";
                case 2 : return "two";
                case 3 : return "three";
                case 4 : return "four";
                case 5 : return "five";
                case 6 : return "six";
                case 7 : return "seven";
                case 8 : return "eight";
                case 9 : return "nine";
                case 10 : return "ten";
                case 11 : return "eleven";
                case 12 : return "twelve";
                case 13 : return "thirteen";
                case 14 : return "fourteen";
                case 15 : return "fifteen";
                case 16 : return "sixteen";
                case 17 : return "seventeen";
                case 18 : return "eighteen";
                case 19 : return "nineteen";
            }
        } else if (n < 100) {
            int firstDigit = n / 10;
            switch (firstDigit) {
                case 2:
                    return "twenty" + write(n % 10);
                case 3:
                    return "thirty" + write(n % 10);
                case 4:
                    return "forty" + write(n % 10);
                case 5:
                    return "fifty" + write(n % 10);
                case 6:
                    return "sixty" + write(n % 10);
                case 7:
                    return "seventy" + write(n % 10);
                case 8:
                    return "eighty" + write(n % 10);
                case 9:
                    return "ninety" + write(n % 10);
            }
        } else if (n < 1000 && n % 100 == 0) {
            return write(n / 100) + "hundred";
        } else if (n < 1000) {
            return (write(n / 100) + "hundredand" + write(n % 100));
        } else if (n == 1000) {
            return "onethousand";
        }

        throw new IllegalArgumentException("n out of bounds!");
    }

    @Override
    public long solve() {
        int result = 0;
        for (int i = 1; i <= N; ++i) {
            result += write(i).length();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Problem017(1000).solve());
    }
}
