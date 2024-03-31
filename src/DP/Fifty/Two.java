package DP.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * RGB 거리 문제에 1번과 N번이 달라야 한다는 조건이 추가된 문제다.
 * 1번, 2번, 3번 집에서 각각 시작할 때마다의 결과를 구해 최솟값을 구할 수 있다.
 * 1번 집에서 시작하면 2, 3번 집은 큰 값으로 해놓는 식으로 하면서,
 * 마지막 N번째에서 최솟값을 구할 때는 1번 집과 다른 경우에만 최솟값을 구해야 한다.
 * dp[i][j] = i번 집까지 탐색했을 때 색깔 j에 최솟값
 */

/**
 * <a href = "https://www.acmicpc.net/problem/17404">백준 17404번 - DP : RBG거리 2</a>
 * @since 2024-03-28
 */
public class Two {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] cost = new int[N + 1][3]; //색깔은 3가지 경우 밖에 없으니 3으로 지정
        int[][] dp = new int[N + 1][3];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int min = Integer.MAX_VALUE;

        for (int i = 0; i <= 2; i++) { //첫 번째 집의 색깔

            for (int j = 0; j <= 2; j++) {
                if (i == j) { //3개의 색깔별로 타깃을 정하여 타깃 외에는 큰 값으로 정한다.
                    dp[1][j] = cost[1][j];
                } else {
                    dp[1][j] = Integer.MAX_VALUE - 1000; //오버플로우를 감안하여 최대 비용 1000만큼 빼준다.
                }
            }

            //2번 집부터 조건에 맞게 최소비용 탐색
            for (int k = 2; k <= N; k++) {
                dp[k][0] = Math.min(dp[k - 1][1], dp[k - 1][2]) + cost[k][0];
                dp[k][1] = Math.min(dp[k - 1][0], dp[k - 1][2]) + cost[k][1];
                dp[k][2] = Math.min(dp[k - 1][0], dp[k - 1][1]) + cost[k][2];
            }

            //첫 번째 집의 색깔과 겹치지 않게 최솟값 갱신
            for (int j = 0; j <= 2; j++) {
                if (i != j) {
                    min = Math.min(min, dp[N][j]);
                }
            }
        }

        System.out.println(min);
    }
}
