package Greedy.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * 가장 큰 돈부터 카운트 해 나간다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/5585">백준 5585번 : 그리디 알고리즘 - 거스름돈</a>
 * @since 2023-12-29
 */
public class Seven {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = 1000 - Integer.parseInt(br.readLine());
        int[] cash = new int[]{500, 100, 50, 10, 5, 1}; // 잔돈은 항상 고정이다.

        int count = 0;
        for (int i = 0; i < cash.length; i++) { // 잔돈은 내림차순으로 정렬되어있다.
            while (m >= cash[i]) {
                m -= cash[i]; // 지불한 돈을 갱신
                count++;
            }
        }
        System.out.println(count);
    }
}
