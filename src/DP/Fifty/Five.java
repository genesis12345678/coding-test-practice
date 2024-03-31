package DP.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * dp[i][j]를 i장을 j번까지 탐색했을 때 최솟값이라 생각해본다.
 * dp[i][j] = min(이전 최솟값, 현재 j번 가격만큼 돈 주고 샀을 때)
 */

/**
 * <a href = "https://www.acmicpc.net/problem/16194">백준 16194번 - DP : 카드 구매하기 2</a>
 * @since 2024-03-29
 */
public class Five {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[1001][1001];
        int[] P = new int[1001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) { //사려는 카드 장수
            dp[i][0] = Integer.MAX_VALUE; //1번째 값 비교를 위해 큰 값 초기화
            for (int j = 1; j <= n; j++) { //카드 묶음 개수
                if (j > i) { //카드 묶음이 더 많으면 오바되니 이전 최솟값 그대로
                    dp[i][j] = dp[i][j - 1];
                } else {
                    //이전 최솟값과 새로운 카드 묶음을 살 때 값을 비교해 최솟값을 갱신한다.
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - j][j] + P[j]);
                }
            }
        }

        System.out.println(dp[n][n]);

    }
}
