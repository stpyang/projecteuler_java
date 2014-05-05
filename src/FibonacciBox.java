/**
* Created with IntelliJ IDEA.
* User: stpyang
* Date: 12/6/13
* Time: 11:21 AM
* To change this template use File | Settings | File Templates.
*/
class FibonacciBox {

    private final long a;
    private final long b;
    private final long c;
    private final long d;

    private FibonacciBox(long a, long b, long c, long d) {
        if (a + b != c || a + c != d) {
            throw new IllegalArgumentException("not a valid four-tuple for fibonnaci box!");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    FibonacciBox[] getChildren() {
        return new FibonacciBox[] {
                new FibonacciBox(d - b, b, d, 2 * d - b),
                new FibonacciBox(b, d, b + d, 2 * b + d),
                new FibonacciBox(d, b, b + d, b + 2 * d),
        };
    }

    static final FibonacciBox SEED = new FibonacciBox(1, 1, 2, 3);

    long[] getPythagoreanTriple() {
        return new long[] { b * d, 2 * a * c, c * d - a * b };
    }

    long getSumOfSides() {
        return b * d + 2 * a * c + c * d - a * b;
    }

    @Override
    public String toString() {
        return "{" +
                " a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                '}';
    }
}
