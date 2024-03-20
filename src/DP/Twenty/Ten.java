package DP.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디이
 * dp[n]을 n자리일 때 n번째 원소를 마지막으로 부분 수열 최댓값이라 생각해본다.
 * dp[현재 자리] = max(dp[현재 자리], 1~현재 자리-1 에서 증가하는 부분 수열이면 현재 원소값 더한 값)
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11055">백준 11055번 - DP : 가장 큰 증가하는 부분 수열</a>
 * @since 2024-03-20
 */
public class Ten {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] A = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            dp[i] = A[i]; //처음엔 자기 자신이 최댓값이 될 수 있다.
        }

        int max = dp[1];
        for (int i = 2; i <= n; i++) { // 2 ~ n
            for (int j = 1; j < i; j++) { // 1 ~ i-1
                if (A[j] < A[i]) { //증가하는 부분 수열이면
                    //i자리의 최댓값을 갱신
                    dp[i] = Math.max(dp[i], dp[j] + A[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);

    }
}
