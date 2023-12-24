package Level17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Five {
    static int[][] dp = new int[30][30];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            sb.append(combination(M, N)).append("\n");
        }
        System.out.println(sb);
    }

    public static int combination(int n, int r) {
        if (dp[n][r] > 0) {
            return dp[n][r];
        } else if (n == r || r == 0) {
            return dp[n][r] = 1;
        } else return dp[n][r] = combination(n - 1, r - 1) + combination(n - 1, r);
    }

}
