package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.util.Collections.reverseOrder;

/**
 * 아이디어
 * A의 최솟값과 B의 최댓값끼리 매칭되면 된다.
 * 처음에는 2차원 배열로 만들고 B는 재배열하면 안 된다고 해서 별의 별걸 해봤다.
 * 결국 그냥 단순하게 a, b배열 나누고 a는 오름차순 정렬, b는 내림차순 정렬을 하면 됐었다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1026">백준 1026번 : 그리디 알고리즘 - 보물</a>
 * @since 2023-12-29
 */
public class Six {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] a = new int[N];
        Integer[] b = new Integer[N]; // 내림차순 정렬을 하려면 T 타입이 필요하다.

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a); // 오름차순 정렬
        Arrays.sort(b, reverseOrder()); // 내림차순 정렬

        int sum = 0;

        for (int i = 0; i < N; i++) {
            sum += a[i] * b[i];
        }
        System.out.println(sum);
    }
}
