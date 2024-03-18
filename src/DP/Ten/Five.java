package DP.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dp[n]을 A[n]을 마지막 값으로 하는 가장 긴 증가하는 부분 수열이라고 생각해본다.
 * A[n]이 증가하는 부분 수열의 마지막이 되려면 A[n]이 추가되기 전 증가부분 수열의 마지막 값이 A[n]보다는 작아야 한다.
 * 따라서 A[n]이 추가될 수 있는 증가부분 수열 중 최댓값에 1을 더한 값이 A[n]값이 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11053">백준 11053번 - DP : 가장 긴 증가하는 부분 수열</a>
 * @since 2024-03-17
 */
public class Five {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] A = new int[n + 1];
        int[] dp = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1; //각 숫자는 자기 숫자 하나로 부분 수열 1개를 만들 수 있다.
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j]) {  //처음부터 훑어보면서 현재 숫자가 증가하는 부분 수열에 들어갈 수 있는지 판단한다.
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
