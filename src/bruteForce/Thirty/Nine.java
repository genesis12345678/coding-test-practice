package bruteForce.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/2851">백준 2851번 - 브루트포스 : 슈퍼 마리오</a>
 * @since 2024-05-07
 */
public class Nine {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[10];

        for (int i = 0; i < 10; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] sum = new int[10];

        sum[0] = arr[0];

        for (int i = 1; i < 10; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        int diff = Integer.MAX_VALUE;
        int score = 0;

        for (int i : sum) {
            if (Math.abs(100 - i) <= diff) {
                diff = Math.abs(100 - i);
                score = i;
            }
        }

        System.out.print(score);
    }
}
