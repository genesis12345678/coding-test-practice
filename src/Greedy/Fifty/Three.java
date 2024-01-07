package Greedy.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 정렬한 뒤 길이보다 작거나 같은 높이를 만나면 길이를 증가한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/16435">백준 16435번 : 그리디 알고리즘 - 스네이크버드</a>
 * @since 2024-01-07
 */
public class Three {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for (int i = 0; i < N; i++) {
            if (arr[i] <= L) {
                L++;
            }
        }
        System.out.println(L);
    }
}
