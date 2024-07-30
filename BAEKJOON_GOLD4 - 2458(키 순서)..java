package heightorder;

import java.util.*;

public class HeightOrder {
    public static void dfs(int startNode, int nextNode, List<List<Integer>> graph, boolean[] visited, List<List<Integer>> heightList) {
        visited[nextNode] = true;

        for (int i : graph.get(nextNode)) {
            if (!visited[i]) {
                heightList.get(startNode).add(i);
                heightList.get(i).add(startNode);
                dfs(startNode, i, graph, visited, heightList);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        List<List<Integer>> graph = new ArrayList<>();
        List<List<Integer>> heightList = new ArrayList<>();
        int answer = 0;

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            heightList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            graph.get(a).add(b);
        }

        for (int i = 1; i <= n; i++) {
            boolean[] visited = new boolean[n + 1];
            dfs(i, i, graph, visited, heightList);
        }

        for (int i = 1; i <= n; i++) {
            if (heightList.get(i).size() == n - 1) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}
