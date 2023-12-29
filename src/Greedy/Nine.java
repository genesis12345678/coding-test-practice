package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * 자연수 S에서 1부터 순서대로 빼서 마지막으로 빼는 수를 구한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1789">백준 1789번 : 그리디 알고리즘 - 수들의 합</a>
 * @since 2023-12-29
 */
public class Nine {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long S = Long.parseLong(br.readLine());// int는 범위를 초과한다.

        /**
         * S   | count
         * 200 | 1
         * 199 | 2
         * 197 | 3
         * 194 | 4
         * 190 | 5
         * 185 | 6
         * 179 | 7
         * 172 | 8
         * 164 | 9
         * 155 | 10
         * 145 | 11
         * 134 | 12
         * 122 | 13
         * 109 | 14
         * 95  | 15
         * 80  | 16
         * 64  | 17
         * 47  | 18
         * 29  | 19
         * 10  | 20 -> !(S >= count)
         */
        int count = 1;
        while (true) {
            if (S >= count) {
                S -= count;
            } else {
                System.out.println(--count); // 가장 마지막 수를 +1하고 반복을 한 것이므로 -1해준다.
                break;
            }
            count++;
        }
    }
}
