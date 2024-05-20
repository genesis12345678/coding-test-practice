package sort.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2230">백준 2230번 - 정렬 : 수 고르기</a>
 * @since 2024-05-16
 */
public class Two {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int left = 0;
        int right = 1;
        int min = Integer.MAX_VALUE;

        while (right < n && left < n) {
            int diff = arr[right] - arr[left];

            if (diff >= m) {
                min = Math.min(min, diff);
                left++;
            } else {
                right++;
            }
        }

        System.out.print(min);
    }
}
