import java.util.*;

public class Test {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static Queue<int[]> cust = new LinkedList<>();

    public static void bfsSP(int[][] graph, int[][] customers) {
        int[] curTaxi = cust.poll();

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{curTaxi[0], curTaxi[1], 0});
        boolean[][] visited = new boolean[graph.length][graph[0].length];
        visited[curTaxi[0]][curTaxi[1]] = true;

        while (!queue.isEmpty()) {
            int[] q = queue.poll();
            int x = q[0];
            int y = q[1];
            int dist = q[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (1 <= nx && nx < graph.length && 1 <= ny && ny < graph[0].length && !visited[nx][ny] && graph[nx][ny] != 1) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, dist + 1});

                    if (graph[nx][ny] == 2) {
                        if (cust.isEmpty()) {
                            cust.offer(new int[]{nx, ny, dist + 1});
                        } else {
                            int[] curCust = cust.poll();

                            if (curCust[2] == dist + 1) {
                                if (curCust[0] > nx) {
                                    cust.offer(new int[]{nx, ny, dist + 1});
                                } else if (curCust[0] == nx && curCust[1] > ny) {
                                    cust.offer(new int[]{nx, ny, dist + 1});
                                } else {
                                    cust.offer(curCust);
                                }
                            } else {
                                cust.offer(curCust);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void bfsEP(int[][] graph, int[][] customers) {
        int[] curCust = cust.poll();
        int endX = 0, endY = 0;

        for (int i = 0; i < customers.length; i++) {
            if (customers[i][0] == curCust[0] && customers[i][1] == curCust[1]) {
                endX = customers[i][2];
                endY = customers[i][3];
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{curCust[0], curCust[1], 0});
        boolean[][] visited = new boolean[graph.length][graph[0].length];
        visited[curCust[0]][curCust[1]] = true;

        while (!queue.isEmpty()) {
            int[] q = queue.poll();
            int x = q[0];
            int y = q[1];
            int dist = q[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (1 <= nx && nx < graph.length && 1 <= ny && ny < graph[0].length && !visited[nx][ny] && graph[nx][ny] != 1) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, dist + 1});

                    if (nx == endX && ny == endY) {
                        graph[curCust[0]][curCust[1]] = 0;
                        cust.offer(new int[]{nx, ny, dist + 1});
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int fuel = sc.nextInt();
        int[][] graph = new int[n + 1][n + 1];
        int[][] customers = new int[m][4];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        int taxiX = sc.nextInt();
        int taxiY = sc.nextInt();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 4; j++) {
                customers[i][j] = sc.nextInt();
            }
            graph[customers[i][0]][customers[i][1]] = 2;
        }

        cust.offer(new int[]{taxiX, taxiY, 0});

        for (int i = 0; i < m; i++) {
            if (graph[cust.peek()[0]][cust.peek()[1]] == 2) {
                int[] curCust = cust.poll();
                curCust[2] = 0;
                cust.offer(curCust);
            } else {
                bfsSP(graph, customers);
            }

            if (cust.isEmpty()) {
                break;
            }
            fuel -= cust.peek()[2];

            bfsEP(graph, customers);

            if (cust.isEmpty()) {
                break;
            }
            fuel -= cust.peek()[2];

            if (fuel < 0) {
                break;
            } else {
                fuel += (cust.peek()[2] * 2);
            }
        }

        boolean isCust = false;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] == 2) {
                    isCust = true;
                    break;
                }
            }
        }

        if (isCust || fuel < 0) {
            System.out.println(-1);
        } else {
            System.out.println(fuel);
        }
    }
}
