package wordsearch;

import java.util.*;

class Solution {
    public void dfs(int x, int y, String word, StringBuilder sb, boolean[][] visited, char[][] board, Set<String> set) {
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        visited[x][y] = true;
        sb.append(board[x][y]);

        if (word.length() == sb.length()) {
            set.add(sb.toString());
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length && !visited[nx][ny]) {
                dfs(nx, ny, word, sb, visited, board, set);
                visited[nx][ny] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public boolean exist(char[][] board, String word) {
        boolean answer = false;
        Set<String> set = new HashSet<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boolean[][] visited = new boolean[board.length][board[i].length];

                dfs(i, j, word, new StringBuilder(), visited, board, set);
            }
        }
        System.out.println(set);

        if (set.contains(word)) {
            answer = true;
        }
        return answer;
    }
}