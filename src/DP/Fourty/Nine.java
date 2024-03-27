package DP.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dp[i][j]를 i~j 구간에 파일을 합쳤을 때 최소비용이라고 생각해본다.
 * 1 ~ 15의 최소비용을 알려면 (1~1 + 2~15), (1~2 + 3~15), (1~3 + 4~15), .... , (1~13 + 14~15), (1~14 + 15~15) 중 최소 비용을 알아야 한다.
 * 또한 2~15의 최소 비용을 알려면 (2~2, 3~15) 이런 식으로 가능한 모든 경우에 최소 비용을 구해야 한다.
 * 즉, 파일 길이(1~K) 별로 최소 비용을 구해야 한다.
 * dp[i][j] = min(dp[i][j], dp[i][m] + dp[m+1][j] + sum(i ~ j))
 * sum(i ~ j)는 구간 합 배열로 편리하게 구할 수 있다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11066">백준 11066번 - DP : 파일 합치기</a>
 * @since 2024-03-27
 */
public class Nine {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int k = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arr = new int[k + 1];//구간 합 배열

            for (int i = 1; i <= k; i++) {
                arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
            }

            //dp[i][j] = i ~ j 파일의 최소 비용
            int[][] dp = new int[k + 1][k + 1];

            for (int i = 2; i <= k; i++) { //부분 파일의 길이
                for (int s = 1; s <= k - i + 1; s++) { //시작점
                    int e = s + i - 1; //끝점
                    dp[s][e] = Integer.MAX_VALUE;

                    for (int m = s; m < e; m++) {//중간을 한 칸씩 바꿔가면서 비교 진행
                        dp[s][e] = Math.min(dp[s][e], dp[s][m] + dp[m + 1][e] + arr[e] - arr[s - 1]);
                    }
                }
            }

            System.out.println();
            System.out.println("arr = " + Arrays.toString(arr));
            for (int[] ints : dp) {
                System.out.println("ints = " + Arrays.toString(ints));
            }

            System.out.println(dp[1][k]);
        }
    }
}
