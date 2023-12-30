package Greedy.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * 30을 소인수분해 하면 2 x 3 x 5이다.
 * 30의 배수 특징은 각 자리수의 합이 3의 배수여야 하고
 * 항상 10의 배수이기 때문에 일의 자리 숫자는 0이다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/10610">백준 10610번 : 그리디 알고리즘 - 30</a>
 * @since 2023-12-30
 */
public class Five {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine(); // 각 자리수의 합을 편하게 구하기 위해 처음에는 String으로 받는다.

        int[] arr = new int[10]; // 카운팅 정렬 배열 : 0~9를 담는다.
        int sum = 0;

        for (int i = 0; i < N.length(); i++) {
            int a = Integer.parseInt(N.substring(i, i + 1));
            arr[a]++;
            sum += a;
        }

        /**
         * 0이 없다는 것은 10의 배수가 될 수 없다 == 30의 배수가 될 수 없다
         * 각 자리수의 합이 3의 배수가 아니다  == 30의 배수가 아니다
         */
        if (arr[0] == 0 || sum % 3 != 0) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0 ; i--) {
            while (arr[i]-- > 0) {
                sb.append(i);
            }
        }
        System.out.println(sb);
    }
}
