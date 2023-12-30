package Greedy.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 거꾸로 B에서 A로 계산해나간다.
 * B를 2로 나눌수 있다면 2로 나누고 그렇지 않다면 끝에 '1'을 제거한다.
 * 만약 2로 나눌수도 없고 끝 숫자가 1이 아니라면 가능한 연산 두 가지로 계산한 수가 아니게 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/16953">백준 16953번 : 그리디 알고리즘 - A -> B</a>
 * @since 2023-12-30
 */
public class Four {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int count = 1;
        while (B > A) {
            if (B % 2 == 0) {
                B /= 2;
            } else if (B % 10 == 1) {
                B /= 10;
            } else {
                System.out.println(-1);
                return;
            }
            count++;
        }

        if (B < A) {
            System.out.println(-1);
        } else {
            System.out.println(count);
        }
    }
}
