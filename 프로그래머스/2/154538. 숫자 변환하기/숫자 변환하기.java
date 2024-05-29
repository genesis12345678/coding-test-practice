import java.util.Arrays;
class Solution {
    
    
    public int solution(int x, int y, int n) {
        int max = y * 2;
        int[] dp = new int[1_000_001];
        Arrays.fill(dp, max);
        dp[0] = 0;
        dp[x] = 0;

        for (int i = x; i <= y; i++) {
            if (i + n <= y) {
                dp[i + n] = Math.min(dp[i] + 1, dp[i + n]);
            }
            if (i * 2 <= y) {
                dp[i * 2] = Math.min(dp[i] + 1, dp[i * 2]);
            }
            if (i * 3 <= y) {
                dp[i * 3] = Math.min(dp[i] + 1, dp[i * 3]);
            }
        }

        return dp[y] == max ? -1 : dp[y];
    }
    
   
}