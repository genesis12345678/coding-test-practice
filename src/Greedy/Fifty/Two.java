package Greedy.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 중간에 위치하는 것이 유리하다.
 * 정렬한 다음 N이 홀수면 정확히 중간이 있기 때문에 중간값을 출력하면 되고
 * N이 짝수면 가운데 두 값중에 작은 값을 출력한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/18310">백준 18310번 : 그리디 알고리즘 - 안테나</a>
 * @since 2024-01-07
 */
public class Two {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        if (N % 2 == 1) {
            System.out.println(arr[N / 2]);
        } else {
            System.out.println(arr[(N / 2) - 1]);
        }
    }
}
