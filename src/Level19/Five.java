package Level19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/4779">백준 4779번 : 재귀 - 칸토어 집합</a>
 * @since 2023-12-26
 */

public class Five {
    static StringBuilder sb ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while ((input = br.readLine()) != null && !input.isEmpty()) {
            int N = Integer.parseInt(input);

            int length = (int) Math.pow(3, N); // 3의 N승

            sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append("-");
            }
            solve(0, length);
            System.out.println(sb);
        }
    }

    /**
     *
     * @param start 공백으로 바꿀 시작점
     * @param length 3등분 된 길이
     */
    public static void solve(int start, int length) {
        if(length == 1) return; // 선의 길이가 1이면 멈춘다.

        int divide = length / 3; // 3등분

        // 3분의 2 지점만 공백으로 바꾼다.
        for (int i = start + divide; i < start + divide * 2; i++) {
            sb.setCharAt(i, ' ');
        }
        solve(start, divide); // 3등분의 1/3
        solve(start + divide * 2, divide); // 3등분의 3/3
    }
}
