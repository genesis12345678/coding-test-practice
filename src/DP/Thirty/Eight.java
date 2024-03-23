package DP.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 재귀 함수 코드 그대로 dp만 적용하면 된다.
 * 재귀 함수를 돌면서 dp에 저장된 값이 있으면 반환해 추가 반복 수행을 막아야 한다.
 * 처음에 새로운 입력이 올 때마다 dp배열을 새로 선언했는데 그러지 않아도 된다.
 * 왜냐하면 다음 입력에서도 똑같은 원리로 값을 구할 수 있기 때문이다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/9184">백준 9184번 - DP : 신나는 함수 실행</a>
 * @since 2024-03-22
 */

public class Eight {

    //a, b, c중 하나라도 값이 20이 넘으면 w(20, 20, 20)을 호출하기 때문에 20을 넘을 수 없다.
    //또한 저장된 값 계속해서 쓰일 거기 때문에 static으로 처음부터 선언 해놓아도 된다.
    static int[][][] dp = new int[21][21][21];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1 && c == -1) {
                break;
            }

            sb.append("w(")
              .append(a)
              .append(", ")
              .append(b)
              .append(", ")
              .append(c)
              .append(") = ")
              .append(w(a, b, c))
              .append("\n");

        }
        System.out.print(sb);
    }

    private static int w(int a, int b, int c) {

        //극한의 효율을 위해 처음 if문부터 저장된 값 있으면 반환
        //0~20 범위는 체크해야 한다.
        if (a >= 0 && a <= 20 && b >= 0 && b <= 20 && c >= 0 && c <= 20) {
            if (dp[a][b][c] != 0) {
                return dp[a][b][c];
            }
        }

        if (a <= 0 || b <= 0 || c <= 0) {
            return 1;
        }

        if (a > 20 || b > 20 || c > 20) {
            return dp[20][20][20] = w(20, 20, 20);
        }

        if (a < b && b < c) {
            return dp[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
        }

        return dp[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
    }
}
