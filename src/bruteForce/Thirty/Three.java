package bruteForce.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * N의 끝자리를 00~99까지 최대 100번을 반복하는 동안 F로 나누어 떨어질 때 정답을 구할 수 있다.
 * 끝자리는 나머지 연산을 통해 구한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1075">백준 1075번 - 브루트포스 : 나누기</a>
 * @since 2024-04-29
 */
public class Three {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int f = Integer.parseInt(br.readLine());

        int temp = n - (n % 100);

        for (int i = temp; i <= temp + 99; i++) {
            if (i % f == 0) {
                if (i % 100 < 10) { //끝 자리가 한 자리수면 앞에 0을 붙인다.
                    System.out.print(0 + "" + i % 100);
                } else {
                    System.out.print(i % 100);
                }
                return;
            }
        }
    }
}
