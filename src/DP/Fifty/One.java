package DP.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dp[i][j]를 n개의 앱 중 i번째까지 탐색했을 때, 총 비활성화 비용 중 j 비용으로 얻을 수 있는 최대 메모리 크기라 생각해본다.
 * dp[i][j] = max(dp[i][j], 이전 탐색에 현재 메모리 크기만큼 더했을 때)
 * dp[i][j] = max(dp[i][j], dp[i-1][j-현재 비활성화 비용] + 현재 메모리 크기)
 * 최종적으로는 dp[n]번째를 돌면서 값이 m보다 크거나 같아지는 열 번호가 정답이다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/7579">백준 7579번 - DP : 앱</a>
 * @since 2024-03-28
 */
public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //앱 개수
        int m = Integer.parseInt(st.nextToken()); //확보 필요한 메모리 용량

        int[] app = new int[n + 1]; //각 앱마다 필요한 메모리
        int[] c = new int[n + 1]; //각 앱의 비활성화 비용

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            app[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            c[i] = Integer.parseInt(st.nextToken());
            sum += c[i];
        }

        //dp[i][j] = i번째 앱까지 탐색했을 때 총 비활성화 비용 중 j 비용으로 얻을 수 있는 최대 메모리
        int[][] dp = new int[n + 1][sum + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j >= c[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - c[i]] + app[i]);
                }
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            }
        }

        for (int[] ints : dp) {
            System.out.println("ints = " + Arrays.toString(ints));
        }

        for (int i = 0; i <= sum; i++) {
            if (dp[n][i] >= m) {
                System.out.println(i);
                break;
            }
        }

    }
}
