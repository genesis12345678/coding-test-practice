package Greedy.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * 배열로 저장해서 가장 마지막 숫자를 제일 높은 점수라고 본다.
 * 배열 마지막부터 시작해서 이전 점수와 비교해서 이전 점수가 더 크다면
 * 최소한 현재 점수보다 작아질 때까지 계산한다.
 * 꼭 점수가 1점씩 차이나야 하는 것은 아니다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2847">백준 2847번 : 그리디 알고리즘 - 게임을 만든 동준이</a>
 * @since 2024-01-04
 */
public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        for (int i = N-1; i > 0 ; i--) {
            while (arr[i] <= arr[i - 1]) {
             count++;
             arr[i-1]--;
            }

        }
        System.out.println(count);
    }
}
