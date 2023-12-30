package Greedy.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

/**
 * 아이디어
 * 각 로프의 최대 중량을 내림차순 정렬 후 각 중량을 각 로프의 개수만큼 곱한다.
 * 모든 로프를 사용할 필욘 없으니 그 중에서 최대를 구한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2217">백준 2217번 : 그리디 알고리즘 - 로프</a>
 * @since 2023-12-29
 */
public class Eight {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Integer[] arr = new Integer[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        /**
         * 결국 로프의 최대 중량으로 최대한 많이 드는게 유리하다.
         * 내림차순으로 하면 현재 로프의 최대 중량은 이전 로프들도 버틸 수 있게 된다.
         */
        Arrays.sort(arr, Collections.reverseOrder());
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            max = Math.max(max, arr[i] * (i + 1));
        }
        System.out.println(max);
    }
}
