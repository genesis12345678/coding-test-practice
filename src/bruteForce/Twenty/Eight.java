package bruteForce.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * m을 1씩 더해가면서 n이 될 때까지 모든 경우의 수를 살펴본다.
 * m이 완전제곱수면 누적합에 더해주고, 첫 번째 나오는 숫자가 최솟값이 된다.
 * 완전제곱수인지 판별하는 방식은 제곱근을 구한 뒤, 제곱근의 제곱이 다시 m이 되면 완전제곱수이다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1977">백준 1977번 - 브루트포스 : 완전제곱수</a>
 * @since 2024-04-27
 */
public class Eight {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        int min = -1;
        int sum = 0;
        while (n >= m) {
            if (isSquareNum(m)) {
                sum += m;
                if (min == -1) {
                    min = m;
                }
            }
            m++;
        }

        if (min == -1) {
            System.out.print(min);
        } else {
            System.out.print(sum + "\n" + min);
        }
    }

    private static boolean isSquareNum(int n) {
        int sqrt = (int) Math.sqrt(n);

        return sqrt * sqrt == n;
    }
}
