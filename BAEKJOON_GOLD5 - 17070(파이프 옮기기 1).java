import java.util.Scanner;

public class Pipe {
    private static int result = 0;

    public static void dfs(int n, int x, int y, int direction, int[][] graph) {
        if (x == n - 1 && y == n - 1) {
            result += 1;
            return;
        }

        if (x + 1 >= 0 && x + 1 < n && y + 1 >= 0 && y + 1 < n) {
            if (graph[x + 1][y + 1] == 0 && graph[x + 1][y] == 0 && graph[x][y + 1] == 0) {
                dfs(n, x + 1, y + 1, 2, graph);
            }
        }

        if (direction == 0 || direction == 2) {
            if (y + 1 >= 0 && y + 1 < n) {
                if (graph[x][y + 1] == 0) {
                    dfs(n, x, y + 1, 0, graph);
                }
            }
        }

        if (direction == 1 || direction == 2) {
            if (x + 1 >= 0 && x + 1 < n) {
                if (graph[x + 1][y] == 0) {
                    dfs(n, x + 1, y, 1, graph);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
        dfs(n, 0, 1, 0, graph);

        System.out.println(result);
    }
}
