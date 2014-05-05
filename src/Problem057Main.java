import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: syang
 * Date: 2/14/14
 * Time: 10:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem057Main {

    public static long solve(int N) {
        long result = 0;

        BigInteger num = BigInteger.valueOf(3);
        BigInteger denom = BigInteger.valueOf(2);
        for(int i = 0; i < N; ++i) {
            if(num.toString().length() > denom.toString().length()) ++result;
            BigInteger newNum = num.add(denom).add(denom);
            BigInteger newDenom = num.add(denom);
            num = newNum;
            denom = newDenom;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(1000));
    }
}
