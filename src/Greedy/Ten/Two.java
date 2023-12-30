package Greedy.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 1. 오름차순 정렬한 뒤 누적합을 구해준다.
 * 2. 정렬은 Counting sort를 사용한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11399">백준 11399번 : 그리디 알고리즘 - ATM</a>
 * @since 2023-12-27
 */
public class Two {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[Integer.parseInt(st.nextToken())]++;
        }

        int sum = 0;
        int total = 0;
        for (int i = 1; i <= N; i++) {
            while (arr[i]-- > 0) {
                sum += i;
                total += sum;
            }

        }
        System.out.println(total);

    }
}
