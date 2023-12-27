package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 1. 배열 뒤에서부터(큰 값부터) K를 구성할 수 있는지 판단한다.
 * 2. 이미 입력부터 오름차순으로 입력되므로 정렬은 필요 없다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11047">백준 11047번 : 그리디 알고리즘 - 동전 0</a>
 * @since 2023-12-27
 */

public class Three {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        // 뒤에서부터(큰 값부터) 앞으로 순회한다.
        for (int i = N - 1; i >= 0; i--) {
            // 0원이 되면 더 이상 계산할 필요 없다.
            if (K == 0) {
                break;
            }
            if (arr[i] <= K) { // K를 구성할 수 있는 가장 큰 수를 찾으면
                count += K / arr[i]; // count를 누적한다.
                K %= arr[i]; // K값을 갱신한다.
            }
        }

        System.out.println(count);
    }
}
