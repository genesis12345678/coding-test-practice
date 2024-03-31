package DP.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dp[i]를 i번째 날에서 퇴사일(N+1일)까지 벌 수 있는 최대 수익이라 생각해본다.
 * 만약 현재 일수 i에서 걸리는 시간 T[i]를 더했을 때 퇴사일을 넘어간다면 i번째 날 최대수익은 다음 날 최대 수익과 같다.
 * dp[i] = dp[i+1]
 * 퇴사일을 넘어가지 않는다면 비교한다.
 * dp[i] = max(dp[i+1], dp[i+T[i]] + P[i])
 * 오늘 일 안하고 다음 날 최대 수익이랑 일 했을 때 버는 최대 수입
 */

/**
 * <a href = "https://www.acmicpc.net/problem/15486>백준 15486번 - DP : 퇴사 2</a>
 * @since 2024-03-29
 */
public class Four {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 2];
        int[] T = new int[N + 1]; //상담 기간
        int[] P = new int[N + 1]; //금액

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i > 0; i--) {
            if (i + T[i] > N + 1) { //퇴사날을 넘긴다면 별 수 없이 다음 날의 최대 수익과 같다.
                dp[i] = dp[i + 1];
            } else {
                //i번째 날에 최대 수익은 그냥 일 안하고 다음 날 최대 수익을 그대로 가져가든가,
                //일하고 돈 받았을 때가 최대 수익이 될 수 있다.
                dp[i] = Math.max(dp[i + 1], dp[i + T[i]] + P[i]);
            }
        }

        System.out.println(dp[1]);
    }
}
