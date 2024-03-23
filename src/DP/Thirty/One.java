package DP.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 1부터 n 순서로 가장 긴 증가하는 부분 수열에
 * n부터 1 순서로 가장 긴 증가하는 부분 수열을 더한 다음에 1을 빼면 답이 된다.
 * 1을 빼는 이유는 교집합이 1개 생기기 때문이다.
 * dp[n]과 dp_r[n]을 각각 n 자리에 가장 증가하는 부분 수열의 길이라 생각해본다.
 * 각각 n번을 통해 증가하는 부분 수열 길이를 구하고,
 * 각 n번 자리의 합에 -1한 값 중 최댓값이 정답이다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11054">백준 11054번 - DP :  가장 긴 바이토닉 부분 수열</a>
 * @since 2024-03-20
 */
public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] A = new int[n + 1];
        int[] dp = new int[n + 1]; //1부터 n 순서(정방향)
        int[] dp_r = new int[n + 1]; //n부터 1 순서(역방향)

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            dp[i] = dp_r[i] = 1; //초기에는 자기 자신 하나로 1로 초기화 할 수 있다.
        }

        //정방향 탐색
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                //값이 더 작다고 무조건 증가시키면 안 된다.
                //+1한 값이 같은 걸로 같은 작은 값을 연속해서 세는 건 아닌지 체크해야 한다.
                //예) 1, 1, 2, 3 일때 2와 3자리에서 1을 연속해서 두 번 더하면 안 된다.
                if (A[i] > A[j] && dp[i] + 1 == dp[j] + 1) {
                    dp[i] = dp[j] + 1; //증가하는 부분 수열 길이 증가
                }
            }
        }

        //역방향 탐색
        for (int i = n; i >= 1; i--) {
            for (int j = n; j >= i; j--) {
                if (A[i] > A[j] && dp_r[i] + 1 == dp_r[j] + 1) {
                    dp_r[i] = dp_r[j] + 1;
                }
            }
        }

        int result = 0;

        for (int i = 1; i <= n; i++) {
            int temp = dp[i] + dp_r[i] - 1; //교집합 1을 빼야 한다.
            if (result < temp) {
                result = temp;
            }
        }

        System.out.println(result);
    }
}
