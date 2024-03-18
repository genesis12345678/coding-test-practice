package DP.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * dp[n]을 n번째 계단에서의 최댓값이라고 생각해본다.
 * dp[n] = max(dp[n - 2], dp[n - 3] + arr[n - 1])
 * 연속된 세 개의 계단을 밟을 수 없다. 때문에 단순히 n - 1번과 n - 2번을 비교하면 안 된다.
 */
public class Four {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n + 1];
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = arr[1];
        if (n >= 2) {
            dp[2] = dp[1] + arr[2];
        }

/*
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2]) + arr[i]; //잘못된 코드, 연속된 세 개의 계단을 밟을 수 없음
        }
*/

        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + arr[i - 1]) + arr[i];
            //dp[i - 2] = 두 칸을 뛰어서 i번에 도착했을 때
            //dp[i - 3] + arr[i - 1] = 한 칸을 뛰어서 i번에 도착했을 때
        }

        System.out.println(dp[n]);
    }
}
