package progavoid;

import java.util.*;

public class ProgAvoid {
    private static int size;

    public static void dfs(int x, int y, int n, int m, int[][] graph) {
        if (x > 0 && x <= n && y > 0 && y <= m && graph[x][y] == 1) {
            graph[x][y] = 0;
            size++;

            dfs(x - 1, y, n, m, graph);
            dfs(x + 1, y, n, m, graph);
            dfs(x, y - 1, n, m, graph);
            dfs(x, y + 1, n, m, graph);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        int[][] graph = new int[n + 1][m + 1];

        for (int i = 0; i < k; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();

            graph[r][c] = 1;
        }

        int maxSize = Integer.MIN_VALUE;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                size = 0;

                dfs(i, j, n, m, graph);

                maxSize = Math.max(maxSize, size);
            }
        }
        System.out.println(maxSize);
    }
}
