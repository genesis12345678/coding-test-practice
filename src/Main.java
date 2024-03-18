import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[][] dp;
    static int[][] W;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        W = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new long[16][1 << 16];

        System.out.println(solution(0, 1));
    }

    private static long solution(int c, int v) {
        if (v == (1 << N) - 1) {
            if (W[c][0] == 0) {
                return Integer.MAX_VALUE;
            } else {
                return W[c][0];
            }
        }
        if (dp[c][v] != 0) {
            return dp[c][v];
        }

        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if ((v & (1 << i)) == 0 && W[c][i] != 0) {
                minValue = (int) Math.min(minValue, solution(i, (v | (1 << i))) + W[c][i]);
            }
        }
        return dp[c][v] = minValue;
    }
}
