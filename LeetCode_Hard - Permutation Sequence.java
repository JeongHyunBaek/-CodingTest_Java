package permutationsequence;

import java.util.*;

class Solution {
    public void backtrack(int n, StringBuilder sb, boolean[] visited, List<String> result) {
        if (sb.length() == n) {
            result.add(sb.toString());
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            sb.append(i);
            backtrack(n, sb, visited, result);
            visited[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public String getPermutation(int n, int k) {
        List<String> result = new ArrayList<>();
        boolean[] visited = new boolean[n + 1];

        backtrack(n, new StringBuilder(), visited, result);

        return result.get(k - 1);
    }
}