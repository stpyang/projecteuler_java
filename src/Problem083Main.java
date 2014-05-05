import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/5/13
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem083Main {

    static class Coordinates extends Pair<Integer> {
        public Coordinates() {
            super(0, 0);
        }

        public Coordinates(int a, int b) {
            super(a, b);
            if (a < 0 || b < 0) {
                throw new IllegalArgumentException("No negative coordinates!");
            }
        }
    }

    public static long solve(int[][] matrix, int M, int N) {
        int[][] distance = new int[M][N];
        boolean[][] visited = new boolean[M][N];
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                distance[i][j] = Integer.MAX_VALUE;
                visited[i][j] = false;
            }
        }

        distance[0][0] = matrix[0][0];
        Set<Coordinates> Q = new HashSet<>();
        Q.add(new Coordinates(0, 0));
        
        while (!Q.isEmpty()) {
            Coordinates u = new Coordinates();

            // u is the vertex in Q with smallest distance that has not been viisted
            int minimumDistiance = Integer.MAX_VALUE;
            for (Coordinates c : Q) {
                if (distance[c.a][c.b] < minimumDistiance && !visited[c.a][c.b]) {
                    u = c;
                    minimumDistiance = distance[c.a][c.b];
                }
            }

            Q.remove(u);
            visited[u.a][u.b] = true;

            Set<Coordinates> neighbors = new HashSet<>();
            if (u.a > 0) {
                neighbors.add(new Coordinates(u.a - 1, u.b));
            }
            if (u.a < M - 1) {
                neighbors.add(new Coordinates(u.a + 1, u.b));
            }
            if (u.b > 0) {
                neighbors.add(new Coordinates(u.a, u.b - 1));
            }
            if (u.b < N - 1) {
                neighbors.add(new Coordinates(u.a, u.b + 1));
            }

            for (Coordinates v : neighbors) {
                int alt = distance[u.a][u.b] + matrix[v.a][v.b];
                if (alt < distance[v.a][v.b]) {
                    distance[v.a][v.b] = alt;
                    if (!visited[v.a][v.b]) {
                        Q.add(v);
                    }
                }
            }
        }

        return distance[M - 1][N - 1];
    }

    public static void main(String[] args) {
        int[][] matrix = Problem081Main.readMatrixFromFile("data/matrix.txt");
        System.out.println(solve(matrix, 80, 80));
    }
}
