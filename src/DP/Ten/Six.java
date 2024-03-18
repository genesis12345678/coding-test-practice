package DP.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dp[i][j]를 (i, j) 위치에서의 최대 경로라 생각해본다.
 * 도형상에서 대각선 왼쪽, 대각선 오른쪽을 프로그램에서는 각각 위, 대각선 왼쪽 위라고 생각해야 한다.
 * (i, j)위치에서의 최댓값은 (i - 1, j)(위), (i - 1, j - 1)(왼쪽 위)의 값 중 최댓값과 현재 위치의 수를 더하면 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1932">백준 1932번 - DP : 정수 삼각형</a>
 * @since 2024-03-17
 */
public class Six {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] A = new int[n][n];
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = A[0][0]; //처음은 선택의 여지가 없다.


        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    //위쪽하고만 비교할 수 밖에 없는 경우
                    dp[i][j] = Math.max(dp[i][j], A[i][j] + dp[i - 1][j]);
                } else if (j == i) {
                    //왼쪽 위 하고만 비교할 수 밖에 없는 경우
                    dp[i][j] = Math.max(dp[i][j], A[i][j] + dp[i - 1][j - 1]);
                } else {
                    //대각선 왼쪽(아래)으로 오는 경우와 대각선 오른쪽으로 내려오는 경우 중 큰 값을 저장한다.
                    dp[i][j] = Math.max(A[i][j] + dp[i - 1][j],
                                        A[i][j] + dp[i - 1][j - 1]);
                }

            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[n - 1][i]);
        }

        System.out.println(max);
    }
}
