import java.util.*;

class Node {
    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

public class Main {
    public static void bfs(int x, int y, boolean[][] visited, int[][] tIsland) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            x = node.getX();
            y = node.getY();

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < visited.length && ny >= 0 && ny < visited[0].length && !visited[nx][ny] && tIsland[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    tIsland[nx][ny] = tIsland[x][y] + 1;
                    q.offer(new Node(nx, ny));
                }
            }
        }
    }

    public static int[][] maketIsland(int n, int m, char[][] graph) {
        int[][] tIsland = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 'L') {
                    tIsland[i][j] = 1;
                } else {
                    tIsland[i][j] = 0;
                }
            }
        }
        return tIsland;
    }

    public static int getMaxDistance(int n, int m, int[][] tIsland) {
        int maxDistance = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maxDistance < tIsland[i][j]) {
                    maxDistance = tIsland[i][j];
                }
            }
        }
        return maxDistance;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int maxDistance = Integer.MIN_VALUE;

        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();

        char[][] graph = new char[n][m];

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();

            for (int j = 0; j < m; j++) {
                graph[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                boolean[][] visited = new boolean[n][m];

                int[][] tIsland = maketIsland(n, m, graph);

                if (graph[i][j] == 'L') {
                    bfs(i, j, visited, tIsland);
                }
                int distance = getMaxDistance(n, m, tIsland);

                if (maxDistance < distance) {
                    maxDistance = distance;
                }
            }
        }

        if (maxDistance == 0) {
            System.out.println(maxDistance);
        } else {
            System.out.println(maxDistance - 1);
        }
    }
}