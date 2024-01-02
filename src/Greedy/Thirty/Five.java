package Greedy.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * 당연히 5원을 많이 줄수록 최소의 동전 개수니까
 * 5원으로 나눌 수 있는지 최우선적으로 확인한 다음
 * 2씩 빼면서 2원을 카운트한다.
 */

/**
 * <a href ="https://www.acmicpc.net/problem/14916">백준 14916번 : 그리디 알고리즘 - 거스름돈</a>
 * @since 2024-01-02
 */
public class Five {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int count = 0;
        while (n > 0) {
            if (n % 5 == 0) {
                count += n / 5;
                break;
            } else {
                count++;
                n -= 2;
            }
        }
        if (n < 0) {
            System.out.println(-1);
        } else {
            System.out.println(count);
        }
    }
}
