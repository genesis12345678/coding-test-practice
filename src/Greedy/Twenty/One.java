package Greedy.Twenty;

import java.io.*;

/**
 * 아이디어
 * 분을 초단위로 바꾸고 큰 값부터 계산해 나간다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/10162">백준 10162번 : 그리디 알고리즘 - 전자레인지</a>
 * @since 2023-12-30
 */
public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        br.close();

        int a = T / 300;
        T %= 300;

        int b = T / 60;
        T %= 60;

        int c = T / 10;
        T %= 10;

        if (T == 0) {
            bw.write(a + " " + b + " " + c);
        }else
            bw.write("-1");

        bw.flush();
        bw.close();
    }
}
