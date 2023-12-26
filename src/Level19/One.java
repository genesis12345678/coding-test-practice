package Level19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/27433">백준 27433번 : 재귀 - 팩토리얼2</a>
 * @since 2023-12-25
 */
public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(factorial(N));
    }

    /**
     * 재귀 방식으로 factorial 계산
     *
     * @param n factorial을 계산할 값
     * @return N!
     *
     * 0이나 1이라면 바로 1을 return한다.
     * 만약 N = 5라면 다음과 같이 진행된다.
     * 1. factorial(5)는 5 * factorial(4)를 반환한다.
     * 2. factorial(4)는 4 * factorial(3)을 반환한다.
     * 3. factorial(3)은 3 * factorial(2)를 반환한다.
     * 4. factorial(2)는 2 * factorial(1)을 반환한다.
     * 5. factorial(1)은 1을 반환한다.
     * 최종적으로 5 * 4 * 3 * 2 * 1 이 반환된다.
     */
    static long factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
