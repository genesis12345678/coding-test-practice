package DP.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dp[i][j]를 j번째에서 가능한 i 볼륨 이라고 생각해본다.
 * 즉, i는 M의 최댓값인 0 ~ 1,000이고, j는 N의 최댓값인 1~50 이다.
 * i를 늘려가면서 볼륨을 체크한다. 볼륨이 0~M 이라면, 지금 단계에 가능한 볼륨임을 체크한다.
 * 다음 단계에서는 이전 단계를 살펴보면서 가능한 볼륨이면 현재 볼륨만큼 더하고 빼서 0~M이면 지금 단계에 가능한 볼륨임을 체크한다.
 * 이 과정을 반복해서 마지막에서 가장 큰 값을 출력하면 된다.
 * 가장 큰 값은 M부터 0까지 거꾸로 탐색하면서 가능하면 출력해서 프로그램 종료한다.
 * 다 찾았는데도 없으면 불가능한 것이므로 -1을 출력한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1495">백준 1495번 - DP : 기타리스트</a>
 * @since 2024-03-31
 */
public class Ten {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //n개의 곡
        int s = Integer.parseInt(st.nextToken()); //시작 볼륨
        int m = Integer.parseInt(st.nextToken()); //최대 볼륨

        boolean[][] dp = new boolean[1001][51]; //0~1000의 볼륨을 1~50 번째에서 가능한치 체크

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n + 1]; //각 연주마다 볼륨

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //처음 연주 볼륨 처리
        if (s - arr[1] >= 0) {
            dp[s - arr[1]][1] = true;
        }

        //처음 연주 볼륨 처리
        if (s + arr[1] <= m) {
            dp[s + arr[1]][1] = true;
        }

        //처음꺼 처리했으니 다음부터
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (dp[j][i - 1]) { //이전 단계에 0~m 까지 가능했던 볼륨 체크
                    if (j + arr[i] <= m) { //가능했던 볼륨에 현재 볼륨을 더했을 때 가능한 범위면 체크
                        dp[j + arr[i]][i] = true;
                    }
                    if (j - arr[i] >= 0) { //가능했던 볼륨에 현재 볼륨을 뺐을 때 가능한 범위면 체크
                        dp[j - arr[i]][i] = true;
                    }
                }
            }
        }

        //최대(M)에서 줄여가면서 가능하면 출력하고 종료, 끝까지 찾아도 없으면 -1 출력
        for (int i = m; i >= 0; i--) {
            if (dp[i][n]) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }
}
