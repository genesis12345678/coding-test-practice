package DP.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 가장 긴 증가하는 부분 수열을 n에서 1 방향으로 반대로 하면 된다.
 * dp[n]을 n 자리의 가장 긴 감소하는 부분 수열이라고 생각해본다.
 * 현재 수열 값보다 다음 탐색하는 수열의 값보다 커야 하며,
 * 연속하는지도 체크해야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11722">백준 11722번 - DP : 가장 긴 감소하는 부분 수열</a>
 * @since 2024-03-21
 */
public class Three {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] A = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1; //초기에는 자기 자신 하나로 1로 초기화할 수 있다.
        }

        int max = dp[1];

        for (int i = n; i >= 1; i--) { //n ~ 1
            for (int j = n; j > i; j--) { //n ~ i
                if (A[i] > A[j] && dp[i] + 1 == dp[j] + 1) {
                    //값이 크다고 무조건 증가하는 수열이라고 보면 안 된다.
                    //같은 값을 또 세는 경우일 수 있으니 +1한 값이 같은지 확인해야 한다.
                    dp[i] = dp[j] + 1;
                }
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
