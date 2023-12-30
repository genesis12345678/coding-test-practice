package Greedy.Twenty;

import java.io.*;

/**
 * 아이디어
 * 계산하기 쉽게 단위를 바꿔주고 큰 수부터 계산해 나간다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2720">백준 2720번 : 그리디 알고리즘 - 세탁소 사장 동혁</a>
 * @since 2023-12-30
 */
public class Two {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int C = Integer.parseInt(br.readLine());
            bw.write(C / 25 + " ");
            C %= 25;

            bw.write(C / 10 + " ");
            C %= 10;

            bw.write(C / 5 + " ");
            C %= 5;

            bw.write(Integer.toString(C));
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
