package Greedy.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 간격이 최대합 좁은 구간끼리 집중국을 놓으면 된다.
 * 센서들을 오름차순 정렬한 뒤, 각 센서들의 거리를 계산한 뒤 거리들을 내림차순 정렬한다.
 * N - 1번 점프(스킵)가 가능하니까 앞에서부터 N - 1은 건너뛰어 합을 구한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2212">백준 2212번 : 그리디 알고리즘 - 센서</a>
 * @since 2024-01-05
 */
public class Six {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        // 기지국 개수가 더 많다면 1센서 1기지국이 가능하다.
        if (K >= N) {
            System.out.println(0);
            return;
        }

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        Integer[] diff = new Integer[N - 1];
        for (int i = 0; i < N - 1; i++) {
            diff[i] = arr[i + 1] - arr[i];
        }

        Arrays.sort(diff, Collections.reverseOrder());

        int sum = 0;
        for (int i = K - 1; i < N - 1; i++) {
            sum += diff[i];
        }
        System.out.println(sum);
    }
}
