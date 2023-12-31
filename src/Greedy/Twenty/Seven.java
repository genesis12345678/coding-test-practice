package Greedy.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 0그룹과 1그룹의 개수 중 최소를 출력한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1439">백준 1439번 : 그리디 알고리즘 - 뒤집기</a>
 * @since 2023-12-31
 */
public class Seven {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        StringTokenizer st_0 = new StringTokenizer(s, "0");
        StringTokenizer st_1 = new StringTokenizer(s, "1");

        System.out.println(Math.min(st_0.countTokens(), st_1.countTokens()));

    }
}

