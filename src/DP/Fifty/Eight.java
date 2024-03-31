package DP.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * 증가하는 부분 수열(LIS)을 구해 전체 길이 n에서 빼면 된다.
 * 예시에서도 LIS에 포함된 수를 제외한 수들을 모두 옮겼다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2631">백준 2631번 - DP : 줄세우기</a>
 * @since 2024-03-31
 */
public class Eight {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            dp[i] = 1;
        }

        int max = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (arr[i] > arr[j] && dp[i] + 1 == dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(n - max);
    }
}
