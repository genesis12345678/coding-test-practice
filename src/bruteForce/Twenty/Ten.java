package bruteForce.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 단순한 브루트포스로 1씩 늘려가면서 확인하면 시간 초과가 발생한다.
 * 우선 count를 x에서 시작해서 m씩 늘리면 여러 해 중에서 x는 항상 고정된 값으로 탐색할 수 있다.
 * 그렇다면 y가 현재 몇인지 알아야 하는데, 나머지 연산으로 알 수 있다.
 * 현재 count와 y를 각각 n으로 나눈 나머지가 같으면 현재 count가 해가 된다.
 * 그리고 문제를 잘 읽어보면 해의 최대는 m과 n의 최소공배수임을 알 수 있다.
 * 따라서 count가 최소공배수를 넘으면 유효하지 않은 표현이므로 -1을 출력한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/6064">백준 6064번 - 브루트포스 : 카잉 달력</a>
 * @since 2024-04-28
 */
public class Ten {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int limit = lcm(m, n);

            int count = x;

            boolean flag = false;

            while (count <= limit) {
                if (count % n == y % n) {
                    flag = true;
                    break;
                }

                count += m;
            }

            System.out.println(flag ? count : -1);
        }
    }

    static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}

