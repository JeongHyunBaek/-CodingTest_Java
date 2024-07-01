import java.util.*;

public class Main {
    public static int dfs(int x, int y, int[][] visited, int[][] graph) {
        if (visited[x][y] != 0) {
            return visited[x][y];
        }

        visited[x][y] = 1;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < visited.length && ny >= 0 && ny < visited[0].length && graph[nx][ny] > graph[x][y]) {
                visited[x][y] = Math.max(visited[x][y], dfs(nx, ny, visited, graph) + 1);
            }
        }
        return visited[x][y];
    }

    public static int getMaxDistance(int n, int[][] visited) {
        int maxDistance = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxDistance = Math.max(maxDistance, visited[i][j]);
            }
        }
        return maxDistance;
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

        int[][] visited = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == 0) {
                    dfs(i, j, visited, graph);
                }
            }
        }
        System.out.println(getMaxDistance(n, visited));
    }
}
