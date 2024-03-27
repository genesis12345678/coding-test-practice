package DP.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dp[i][j]를 (i, j)위치에서 가능한 최댓값이라 생각해본다.
 * (i, j)위치에서 얻는 최댓값은 3가지 경우에 현재 (i, j)위치의 사탕 개수를 더했을 때 최댓값이다.
 * 1. 왼쪽에서 온 경우
 * 2. 위쪽에서 온 경우
 * 3. 왼쪽 대각선 위에서 온 경우
 * 조건 중에 오른쪽, 아래쪽, 오른쪽 대각선 아래로만 이동 가능 하다고 했다.
 * 최종 점화식
 * dp[i][j] = max(왼쪽+현재값, 위쪽+현재값, 왼쪽 대각선 위+현재값)
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11048">백준 11048번 - DP : 이동하기</a>
 * @since 2024-03-23
 */
public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] miro = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                miro[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(dp[i - 1][j] + miro[i][j],
                            Math.max(dp[i][j - 1] + miro[i][j],
                                     dp[i - 1][j - 1] + miro[i][j]));
            }
        }

        System.out.println(dp[n][m]);
    }
}
