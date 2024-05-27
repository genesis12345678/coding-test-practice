package sort.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/13164">백준 13164번 - 정렬 : 행복 유치원</a>
 * @since 2024-05-20
 */
public class Three {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] diff = new int[n - 1]; //키 차이 배열
        for (int i = 0; i < n - 1; i++) {
            diff[i] = Math.abs(arr[i] - arr[i + 1]);
        }

        Arrays.sort(diff);

        int sum = 0;
        for (int i = 0; i < n - k; i++) {
            sum += diff[i];
        }

        System.out.println(sum);
    }
}
