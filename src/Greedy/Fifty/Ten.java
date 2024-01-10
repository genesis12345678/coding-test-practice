package Greedy.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 아이디어
 * 예상 등수를 오름차순 정렬하고 실제 등수와의 차를 누적합 해준다.
 * 주의! 모든 사람이 1등을 예상하고 최대 500,000명이 주어진다면 int의 범위를 벗어날 수 있다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2012">백준 2012번 : 그리디 알고리즘 - 등수 매기기</a>
 * @since 2024-01-10
 */
public class Ten {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += Math.abs(arr[i - 1] - i);
        }
        System.out.println(sum);
    }
}
