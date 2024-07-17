package fibonaccisquare;

import java.util.*;

public class FibonacciSquare {
    private static int index = 1;

    public static void fiboSquare(long a1, long b1, long a2, long b2, long x, long y, long[] dp) {
        index--;

        if (x >= a2 && x < a2 + dp[index] && y >= a1 && y < b1) {
            fiboSquare(a2, a2 + dp[index], a1, b1, y, x, dp);
        }

        if (x >= b2 - dp[index] && x < b2 && y >= a1 && y < b1) {
            fiboSquare(b2 - dp[index], b2, a1, b1, y, x, dp);
        }
        return;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a, b;
        long x, y;
        long[] dp = new long[45];
        dp[1] = 1;
        dp[2] = 1;

        a = sc.nextLong();
        b = sc.nextLong();
        x = sc.nextLong();
        y = sc.nextLong();

        long maxFibo = Math.max(a, b);

        if (maxFibo > 1) {
            for (int i = 3; i < 45; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];

                if (dp[i] == maxFibo) {
                    index = i - 1;
                    break;
                }
            }
        }

        if (a > b) {
            fiboSquare(0L, b, 0L, a, x, y, dp);
        } else {
            fiboSquare(0L, a, 0L, b, y, x, dp);
        }
        System.out.println(index + 1);
    }
}
