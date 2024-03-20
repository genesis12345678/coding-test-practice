package DP.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dp[i][j]를 N개의 물품 중 i번까지 탐색했을 때 j무게의 최대 가치라고 생각해본다.
 * dp[i][j] = max(dp[i-1][j], dp[i-1][j-W[i]] + V[i])
 * 같은 j 무게의 이전 탐색과
 * 현재 W[i]와 같이 구성할 수 있는 무게에 현재 가치를 더한 값 중
 * 최댓값을 dp[i][j]에 저장한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/12865">백준 12865번 - DP : 평범한 배낭</a>
 * @since 2024-03-18
 */
public class Two {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //물품의 수
        int k = Integer.parseInt(st.nextToken()); //버틸 수 있는 무게

        int[] W = new int[n + 1];
        int[] V = new int[n + 1];
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken()); //무게
            int v = Integer.parseInt(st.nextToken()); //가치

            W[i] = w;
            V[i] = v;
        }

        for (int i = 1; i <= n; i++) {//i번 탐색
            for (int j = 1; j <= k; j++) {//j무게

                if (W[i] > j) { //현재 무게를 가방에 담을 수 없을 때
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W[i]] + V[i]);
                }
            }
        }
        System.out.println(dp[n][k]);

        for (int[] ints : dp) {
            System.out.println("ints = " + Arrays.toString(ints));
        }

    }
}
