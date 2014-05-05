import java.math.BigInteger;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 11/29/13
 * Time: 12:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem066Main {

    private static BigInteger getK(BigInteger numerator, BigInteger denominator, int N) {
        BigInteger firstTerm = numerator.multiply(numerator);
        BigInteger secondTerm = BigInteger.valueOf(N).multiply(denominator).multiply(denominator);
        return firstTerm.subtract(secondTerm);
    }

    private static BigInteger[] getFundamentalSolution(int N) {
        List<EulerUtil.Triplet<Long>> expansion = Problem064Main.getContinuedFractionExpansion(N);
        int period = Problem064Main.getPeriodSquareRoot(N);
        BigInteger oldOldNumerator = BigInteger.valueOf(expansion.get(0).a);
        BigInteger oldOldDenominator = BigInteger.ONE;
        if (getK(oldOldNumerator, oldOldDenominator, N).equals(BigInteger.ONE)) {
            return new BigInteger[] { oldOldNumerator, oldOldDenominator };
        }
        BigInteger oldNumerator = BigInteger.valueOf(expansion.get(0).a * expansion.get(1).a + 1);
        BigInteger oldDenominator = BigInteger.valueOf(expansion.get(1).a);
        if (getK(oldNumerator, oldDenominator, N).equals(BigInteger.ONE)) {
            return new BigInteger[] { oldNumerator, oldDenominator };
        }
        for (int i = 2; i < 10 * expansion.size(); ++i) {
            int j = i;
            while(j >= expansion.size()) {
                j -= period;
            }
            EulerUtil.Triplet<Long> triplet = expansion.get(j);

            BigInteger numerator = BigInteger.valueOf(triplet.a).multiply(oldNumerator).add(oldOldNumerator);
            BigInteger denominator = BigInteger.valueOf(triplet.a).multiply(oldDenominator).add(oldOldDenominator);
            if (getK(numerator, denominator, N).equals(BigInteger.ONE)) {
                return new BigInteger[] { numerator, denominator };
            }

            oldOldNumerator = oldNumerator;
            oldOldDenominator = oldDenominator;
            oldNumerator = numerator;
            oldDenominator = denominator;
        }
        return null;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        BigInteger maxX = BigInteger.ZERO;
        int maxD = 0;
        for (int D = 2; D <= 1000; ++D) {
            if (!EulerUtil.isSquare(D)) {
                BigInteger[] fundamentalSolution = getFundamentalSolution(D);
                if (fundamentalSolution[0].compareTo(maxX) > 0) {
                    maxX = fundamentalSolution[0];
                    maxD = D;
                }
            }
        }
        return maxD;
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}

