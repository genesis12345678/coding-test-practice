package Greedy.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 뒤에서부터 지금이 더 비싼 주식이라면 팔고,
 * 더 비싼 주식이 나온다면 판다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11501">백준 11501번 : 그리디 알고리즘 - 주식</a>
 * @since 2024-01-07
 */
public class Ten {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        int[] arr;
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            arr = new int[N];

            for (int j = 0; j < N; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            int max = arr[N - 1];
            long count = 0;
            for (int j = N - 2; j >= 0; j--) {
                if (arr[j] > max) {
                    max = arr[j];
                } else {
                    count += max - arr[j];
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}
