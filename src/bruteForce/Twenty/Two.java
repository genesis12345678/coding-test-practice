package bruteForce.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * E, S, M을 각각 0부터 시작하여 e = E, s = S, m = M이 될 때까지 1씩 더해준다.
 * 이떄 E, S, M의 최댓값이 넘어가면 다시 1로 돌아간다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1476">백준 1476번 - 브루트포스 : 날짜 계산</a>
 * @since 2024-04-24
 */
public class Two {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int e = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        final int E_MAX = 16;
        final int S_MAX = 29;
        final int M_MAX = 20;

        int E = 0, S = 0, M = 0;

        int count = 0;

        do {
            count++;
            E++;
            S++;
            M++;

            if (E == E_MAX) {
                E = 1;
            }
            if (S == S_MAX) {
                S = 1;
            }
            if (M == M_MAX) {
                M = 1;
            }
        } while (e != E || s != S || m != M);

        System.out.print(count);
    }
}
