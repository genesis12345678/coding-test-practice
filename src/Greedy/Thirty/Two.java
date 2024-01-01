package Greedy.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 패키지와 낱개 가격 중에 최소가격을 구해준다.
 * 다음 세 가지 선택지 중에 최소값을 출력한다.
 * 1. 패키지로만 구매
 * 2. 낱개로만 구매
 * 3. 패키지 + 낱개로 구매
 * N개의 수를 꼭 맞출 필요 없기 때문에 가능하다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1049">백준 11000번 : 그리디 알고리즘 - 기타줄</a>
 * @since 2024-01-01
 */
public class Two {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        int sixMin = 1001; // 문제에서 가격은 0 <= N <= 1000 이다.
        int oneMin = 1001; // 문제에서 가격은 0 <= N <= 1000 이다.

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            sixMin = Math.min(sixMin, Integer.parseInt(st.nextToken()));
            oneMin = Math.min(oneMin, Integer.parseInt(st.nextToken()));
        }


        // ((N / 6) + 1)에서 +1을 해주어야 오버되더라도 적어도 N개의 줄을 살 수 있다.
        int min = Math.min(((N / 6) + 1) * sixMin, oneMin * N);
        int realMin = Math.min(min, N / 6 * sixMin + N % 6 * oneMin);

        System.out.println(realMin);
    }
}
