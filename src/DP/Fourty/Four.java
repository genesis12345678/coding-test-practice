package DP.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dp[i][j]를 (i, j) 위치에서 얻는 최대 또는 최솟값이라 생각해본다.(숫자는 세 개씩 적혀있으니 j는 0, 1, 2일 것이다.)
 * 첫 번째 열, j=0인 경우 = dp[i][0] = max(전 행의 0열, 전 행의 1열) + 현재 값
 * 두 번째 열, j=1인 경우 = dp[i][1] = max(전 행의 0열, 전 행의 1열, 전 행의 2열) + 현재 값
 * 세 번째 열, j=2인 경우 = dp[i][2] = max(전 행의 1열, 전 행의 2열) + 현재 값
 * 현재 위치 (i, j)에 올 수 있는 경우를 고려해 최댓값을 찾으면 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2096">백준 2096번 - DP : 내려가기</a>
 * @since 2024-03-25
 */
public class Four {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] maxDP = new long[n][3];
        long[][] minDP = new long[n][3];

        for (int i = 0; i < 3; i++) { //0행 초기화
            maxDP[0][i] = minDP[0][i] = map[0][i];
        }

        for (int i = 1; i < n; i++) { //1행부터
            for (int j = 0; j < 3; j++) {
                if (j == 0) { //0열은 위 행에 0열과 1열 밖에 비교 안됨
                    maxDP[i][j] = Math.max(maxDP[i - 1][j], maxDP[i - 1][j + 1]) + map[i][j];
                    minDP[i][j] = Math.min(minDP[i - 1][j], minDP[i - 1][j + 1]) + map[i][j];
                } else if (j == 1) { //1열은 위 행에 0열, 1열, 2열과 비교
                    maxDP[i][j] = Math.max(Math.max(maxDP[i - 1][j - 1], maxDP[i - 1][j + 1]), maxDP[i - 1][j]) + map[i][j];
                    minDP[i][j] = Math.min(Math.min(minDP[i - 1][j - 1], minDP[i - 1][j + 1]), minDP[i - 1][j]) + map[i][j];
                } else { //2열은 위 행에 1열과 2열 밖에 비교 안됨
                    maxDP[i][j] = Math.max(maxDP[i - 1][j], maxDP[i - 1][j - 1]) + map[i][j];
                    minDP[i][j] = Math.min(minDP[i - 1][j], minDP[i - 1][j - 1]) + map[i][j];
                }
            }
        }

        long max = maxDP[n - 1][0];
        long min = minDP[n - 1][0];

        for (int i = 1; i < 3; i++) {
            max = Math.max(max, maxDP[n - 1][i]);
            min = Math.min(min, minDP[n - 1][i]);
        }

        System.out.print(max + " " + min);
    }
}
