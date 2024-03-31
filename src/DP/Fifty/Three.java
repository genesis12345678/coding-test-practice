package DP.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dp[i][j]를 N개의 숫자 중 i번째까지 탐색했을 때 j가 될 수 있는 경우의 수라 생각해본다.
 * j는 0~20만 가능하므로 최대 20을 넘지 않는다.
 * 일단 첫번째 숫자에 1로 초기화 해놓고 두번째 숫자부터 탐색한다.
 * 탐색 방법은 전 단계, 즉 i-1에서 0이 아닌 수들에 대해 현재 숫자를 더하거나 빼면서 0~20 이면 경우의 수를 증가시킨다.
 * 최종 정답은 dp[n-1][숫자 배열의 마지막] 이다.
 * n-1인 이유는 마지막 숫자는 결과이기 때문이다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/5557">백준 5557번 - DP : 1학년</a>
 * @since 2024-03-29
 */
public class Three {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[n + 1][21];

        //첫 번째 탐색에 첫 숫자 가능한 경우의 수 1 초기화
        dp[1][a[1]] = 1;

        for (int i = 2; i < n; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i - 1][j] != 0) {
                    if (j + a[i] <= 20) {
                        dp[i][j + a[i]] += dp[i - 1][j];
                    }
                    if (j - a[i] >= 0) {
                        dp[i][j - a[i]] += dp[i - 1][j];
                    }
                }
            }
        }

        System.out.println(dp[n - 1][a[n]]);
    }
}
