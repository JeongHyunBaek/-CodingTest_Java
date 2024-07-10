package gerrymandering;

import java.util.*;

class Combination {
    private int n;
    private int r;
    private int[] now; // 현재 조합
    private ArrayList<ArrayList<Integer>> result; // 모든 조합

    public ArrayList<ArrayList<Integer>> getResult() {
        return result;
    }

    public Combination(int n, int r) {
        this.n = n;
        this.r = r;
        this.now = new int[r];
        this.result = new ArrayList<ArrayList<Integer>>();
    }

    public void combination(int[] arr, int depth, int index, int target) {
        if (depth == r) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < now.length; i++) {
                temp.add(arr[now[i]]);
            }
            result.add(temp);
            return;
        }
        if (target == n) return;
        now[index] = target;
        combination(arr, depth + 1, index + 1, target + 1);
        combination(arr, depth, index, target + 1);
    }
}

public class Gerrymandering {
    private static int minValue = Integer.MAX_VALUE;

    public static boolean dfs(int v, List<Integer> g, boolean[] visited, List<Integer>[] graph) {
        visited[v] = true;

        for (int i : graph[v]) {
            if (g.contains(i) && !visited[i]) {
                dfs(i, g, visited, graph);
            }
        }

        for (int gg : g) {
            if (!visited[gg]) {
                return false;
            }
        }
        return true;
    }

    public static int sumPopulation(List<Integer> g, int[] population) {
        int sum = 0;

        for (int gg : g) {
            sum += population[gg - 1];
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] population = new int[n];

        for (int i = 0; i < n; i++) {
            population[i] = sc.nextInt();
        }

        List<Integer>[] graph = new ArrayList[n + 1];

        for (int i = 1; i < n + 1; i++) {
            int k = sc.nextInt();

            graph[i] = new ArrayList<>();

            for (int j = 0; j < k; j++) {
                graph[i].add(sc.nextInt());
            }
        }

        int[] v = new int[n];

        for (int i = 1; i < n + 1; i++) {
            v[i - 1] = i;
        }

        for (int i = 1; i < n / 2 + 1; i++) {
            Combination comb = new Combination(v.length, i);
            comb.combination(v, 0, 0, 0);

            for (List<Integer> g1 : comb.getResult()) {
                List<Integer> g2 = new ArrayList<>();

                for (int j : v) {
                    if (!g1.contains(j)) {
                        g2.add(j);
                    }
                }

                boolean[] visited1 = new boolean[n + 1];
                boolean[] visited2 = new boolean[n + 1];

                if (dfs(g1.get(0), g1, visited1, graph) && dfs(g2.get(0), g2, visited2, graph)) {
                    int sumG1 = sumPopulation(g1, population);
                    int sumG2 = sumPopulation(g2, population);

                    minValue = Math.min(minValue, Math.abs(sumG1 - sumG2));
                }
            }
        }

        if (minValue == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minValue);
        }
    }
}
