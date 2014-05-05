/**
* Created with IntelliJ IDEA.
* User: stpyang
* Date: 12/6/13
* Time: 11:21 AM
* To change this template use File | Settings | File Templates.
*/
class Pair<T> {
    final T a;
    final T b;

//        Pair(T[] ab) {
//            if (ab.length != 2) {
//                throw new ExceptionInInitializerError("T array for abc must have exactly 3 entries!");
//            } else {
//                this.a = ab[0];
//                this.b = ab[1];
//            }
//        }

    Pair(T a, T b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        return !(a != null ? !a.equals(pair.a) : pair.a != null) && !(b != null ? !b.equals(pair.b) : pair.b != null);

    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
