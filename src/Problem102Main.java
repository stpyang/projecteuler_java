import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/13/13
 * Time: 11:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem102Main {

    public static class Coordinates extends Pair<Integer> {
        Coordinates(Integer a, Integer b) {
            super(a, b);
        }
    }

    public static class Triangle {
        final Coordinates[] vertices;

        Triangle(Coordinates v1, Coordinates v2, Coordinates v3) {
            this.vertices = new Coordinates[] { v1, v2, v3 };
        }

        Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
            this.vertices = new Coordinates[] {
                    new Coordinates(x1, y1),
                    new Coordinates(x2, y2),
                    new Coordinates(x3, y3),
            };
        }

        @Override
        public String toString() {
            return "Triangle{" +
                    "vertices=" + Arrays.toString(vertices) +
                    '}';
        }

        boolean containsOrigin() {
            int x1 = vertices[0].a;
            int x2 = vertices[1].a;
            int x3 = vertices[2].a;
            int y1 = vertices[0].b;
            int y2 = vertices[1].b;
            int y3 = vertices[2].b;

            double m1 = ((double) (y2 - y3)) / ((double) (x2 - x3));
            double m2 = ((double) (y1 - y3)) / ((double) (x1 - x3));
            double m3 = ((double) (y1 - y2)) / ((double) (x1 - x2));

            double b1 = ((double) y2 - m1 * x2);
            double b2 = ((double) y3 - m2 * x3);
            double b3 = ((double) y1 - m3 * x1);

            double xIntercept1 = -b1 / m1;
            double xIntercept2 = -b2 / m2;
            double xIntercept3 = -b3 / m3;

            double lambda1 = (xIntercept1 - x3) / (x2 - x3);
            double lambda2 = (xIntercept2 - x3) / (x1 - x3);
            double lambda3 = (xIntercept3 - x2) / (x1 - x2);

            boolean crossing1 = (xIntercept1 > 0 && 0 < lambda1 && lambda1 < 1);
            boolean crossing2 = (xIntercept2 > 0 && 0 < lambda2 && lambda2 < 1);
            boolean crossing3 = (xIntercept3 > 0 && 0 < lambda3 && lambda3 < 1);

            return (crossing1 ^ crossing2 ^ crossing3);
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(String fileName) {
        try {
            int result = 0;
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] coordinates = line.split(",");
                if (coordinates.length != 6) {
                    throw new IOException("triangle must have three coordinates!");
                }
                Coordinates v1 = new Coordinates(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
                Coordinates v2 = new Coordinates(Integer.parseInt(coordinates[2]), Integer.parseInt(coordinates[3]));
                Coordinates v3 = new Coordinates(Integer.parseInt(coordinates[4]), Integer.parseInt(coordinates[5]));

                Triangle t = new Triangle(v1, v2, v3);

                if (t.containsOrigin()) {
                    ++result;
                }
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solve("data/triangles.txt"));
    }

}
