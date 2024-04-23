package programmers.practice;

public class Fibo {


    public static void main(String[] args) {
        int n = 100000;

        System.out.println(solution(n));
    }

    private static int solution(int n) {

        int[] dp = new int[100_001];

        dp[1] = dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % 1234567;
        }

        return dp[n];
    }
}
