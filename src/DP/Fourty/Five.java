package DP.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 일반적인 LIS 문제(Longest Increasing Subsequence, 가장 긴 증가하는 부분 수열)에 부분 수열을 출력해야 한다.
 * 여기서 핵심은 LIS가 여러가지인 경우 아무거나 출력할 수 있어야 한다.
 * 일단 LIS의 길이를 구하면서, LIS의 마지막 인덱스, 즉 여러 개가 될 수 있는 LIS의 A 수열의 마지막 인덱스를 구한다.
 * 이후 마지막 인덱스부터 1로(거꾸로) 탐색하면서 증가하는 부분 수열인 경우 스택에 저장한다.
 * 이제 스택에서 하나씩 꺼내서 출력하면 정상적인 순서로 출력될 것이다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/14002">백준 14002번 - DP : 가장 긴 증가하는 부분 수열 4</a>
 * @since 2024-03-25
 */
public class Five {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] A = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        int max = 1; //LIS 값
        int maxIdx = 1; //LIS의 마지막 인덱스
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (A[i] > A[j] && dp[i] + 1 == dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
            //max <= dp[i], 크거나 같게로 하면 마지막 순서에 있는 LIS가 출력될 것이다.
            if (max < dp[i]) {
                max = dp[i];
                maxIdx = i;
            }
        }

        Stack<Integer> result = new Stack<>();
        result.push(A[maxIdx]);

        int index = max - 1;
        for (int i = maxIdx - 1; i >= 1; i--) {
            if (dp[i] == index) {
                index--;
                result.push(A[i]);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!result.isEmpty()) {
            sb.append(result.pop()).append(" ");
        }
        System.out.println(max);
        System.out.println(sb);
    }
}
