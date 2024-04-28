package programmers.practice;

public class LongJump {
    public static void main(String[] args) {
        int n = 2000;

        System.out.println(solution(n));
    }

    private static long solution(int n) {
        long[] dp = new long[2001];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1234567;
        }

        return dp[n];
    }
}
