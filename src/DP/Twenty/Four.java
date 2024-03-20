package DP.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * 전형적인 LCS 문제다.
 * 각 문자열의 길이를 축으로 하는 2차원 배열을 생성한다.
 * LCS 점화식
 * 특정 행과 열이 가리키는 문자열이 같다면 대각선 왼쪽 위의 값에 1을 더한 값을 dp[i][j]에 저장한다.
 * 문자열이 다르다면 왼쪽과 위쪽 값 중 더 큰 값을 dp[i][j]에 저장한다.
 * 마지막에 위치한 값이 정답이다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/9251">백준 9251번 - DP : LCS</a>
 * @since 2024-03-18
 */
public class Four {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();

        int lenA = A.length;
        int lenB = B.length;

        int[][] dp = new int[lenA + 1][lenB + 1];

        for (int i = 1; i < lenA + 1; i++) {
            for (int j = 1; j < lenB + 1; j++) {
                if (A[i - 1] == B[j - 1]) { //문자열이 같다면 왼쪽 위 값 + 1 저장
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {                    //문자열이 다르다면 왼쪽과 위쪽 값 중 더 큰 값 저장
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[lenA][lenB]);
    }
}
