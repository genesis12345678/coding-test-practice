package bruteForce.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 시작 0에서 부터 투 포인트를 사용해 경우의 수를 구한다.
 * s와 e의 합이 m을 넘어가면 s값 만큼 빼주고 s를 이동시킨다.
 * s와 e의 합이 아직 m에 못 미치면 e값 만큼 더해주고 e를 이동시킨다.
 * s와 e의 합이 m이면 경우의 수를 증가시킨다.
 * e가 n의 범위를 넘어갈 때까지 반복하면서 구한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2003">백준 2003번 - 브루트포스 : 수들의 합 2</a>
 * @since 2024-04-24
 */
public class Four {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int start = 0;
        int end = 0;
        int count = 0;

        while (true) {
            if (sum >= m) {
                sum -= arr[start++];

            } else if (end == n) {
                break;

            } else {
                sum += arr[end++];
            }

            if (sum == m) {
                count++;
            }
        }

        System.out.print(count);
    }
}
