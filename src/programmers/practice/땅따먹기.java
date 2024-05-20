package programmers.practice;

public class 땅따먹기 {
    public static void main(String[] args) {
        int[][] land = {
                {1, 2, 3, 5},
                {5, 6, 7, 8},
                {4, 3, 2, 1}
        };

        System.out.println(solution(land));
    }

    private static int solution(int[][] land) {
        int[][] dp = new int[land.length][4];

        for (int i = 0; i < 4; i++) {
            dp[0][i] = land[0][i];
        }

        for (int i = 1; i < land.length; i++) {
            for (int j = 0; j < 4; j++) {
                int max = 0;
                for (int k = 0; k < 4; k++) {
                    if (j != k) {
                        max = Math.max(max, dp[i - 1][k]);
                    }
                }
                dp[i][j] = max + land[i][j];
            }
        }

        int max = 0;
        for (int i = 0; i < 4; i++) {
            max = Math.max(max, dp[land.length - 1][i]);
        }

        return max;
    }
}
