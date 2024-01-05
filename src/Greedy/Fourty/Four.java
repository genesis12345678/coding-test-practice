package Greedy.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * 보통 이런 문제는 뒤에서 앞으로 바꿔야 한다.
 * 마지막 문자가 A면 A를 제거하고
 * 마지막 문자가 B면 B를 제거하고 뒤집는다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/12904">백준 12904번 : 그리디 알고리즘 - A와 B</a>
 * @since 2024-01-05
 */
public class Four {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        while (S.length() < T.length()) {
            if (T.charAt(T.length() - 1) == 'A') {
                T = T.substring(0, T.length() - 1);
            } else {
                T = T.substring(0, T.length() - 1);
                T = new StringBuilder(T).reverse().toString();
            }
        }
        if (S.equals(T)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
