package hideandseek2;

import java.util.*;

public class HideAndSeek {
    private static int result = 0;

    public static void bfs(int n, int k, int[] dp) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);

        while (!queue.isEmpty()) {
            int q = queue.poll();

            if (q == k) {
                result++;
                continue;
            }

            int[] dir = {q - 1, q + 1, q * 2};

            for (int i : dir) {
                if (i >= 0 && i <= 100000 && (dp[i] == 0 || dp[i] - 1 == dp[q])) {
                    queue.offer(i);
                    dp[i] = dp[q] + 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] dp = new int[100001];

        bfs(n, k, dp);

        System.out.println(dp[k]);
        System.out.println(result);
    }
}
