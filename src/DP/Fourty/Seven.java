package DP.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 처음에는 단순하게 투포인터를 사용해서 질의마다 팰린드롬을 판단했는데, 시간이 오래 걸렸다.
 * 그래서 dp 배열을 미리 구해놓고, 질의가 오면 바로 응답할 수 있도록 수정했다.
 * dp[i][j]를 i ~ j 구간이 팰린드롬인지 저장해놓은 테이블이라고 생각해본다.
 * arr[i]과 arr[j]가 같으면서, dp[i + 1][j - 1]이 팰린드롬이어야 dp[i][j] 구간이 팰린드롬이 만족이 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/10942">백준 10942번 - DP : 팰린드롬?</a>
 * @since 2024-03-25
 */

public class Seven {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            dp[i][i] = true; //start와 end가 같은 자리 true 처리
            if (i < n - 1 && arr[i] == arr[i + 1]) { //추가로 바로 다음 자리가 같은 경우 true 처리
                dp[i][i + 1] = true;
            }
        }

        for (int i = n - 2; i >= 0; i--) { //마지막 행은 어차피 start와 end가 같으니 n-2 행부터
            for (int j = i + 2; j < n; j++) { //각 행마다 같은 열 위치와 다음 열 위치는 위에서 처리했으니 i+2부터
                if (arr[i] == arr[j] && dp[i + 1][j - 1]) { //양 끝이 같으면서 start+1 자리와 end-1 자리가 팰린드롬이어야 현재 범위도 팰린드롬이다.
                    dp[i][j] = true;
                }
            }
        }

        int m = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;

            sb.append(dp[s][e] ? 1 : 0).append("\n");
/*
            //오래 걸리는 방식
            boolean flag = true;

            while (s < e) {
                if (arr[s] != arr[e]) {
                    flag = false;
                    break;
                }
                s++;
                e--;
            }

            sb.append(flag ? 1 : 0).append("\n");
*/
        }
        System.out.println(sb);
    }
}
