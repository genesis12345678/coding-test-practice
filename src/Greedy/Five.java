package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 더하기 연산을 먼저 해주어 최대한 큰 수를 만들고 뺄셈을 진행한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1541">백준 1541번 : 그리디 알고리즘 - 잃어버린 괄호</a>
 * @since 2023-12-28
 */
public class Five {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       // 뺄셈을 기준으로 나눈다. Ex) "55-50+40" -> 1. "55" , 2. "50+40"
        StringTokenizer st = new StringTokenizer(br.readLine(), "-");

        /**
         * 0으로 초기화하면 중간계산에서 0이 나올 수 있어 문제가 될 수 있다.
         * 그래서 절대 중간계산에서 나올 수 없는 수로 초기화 해야 한다.
         */
        int sum = 800_092;

        // 뺄셈 기준으로 자른 문자열
        while (st.hasMoreTokens()) {
            int temp = 0;

            // 덧셈을 기준으로 다시 자른다. Ex) "55" , "50+40" -> 1. "55", 2. "50", "40"
            StringTokenizer add = new StringTokenizer(st.nextToken(), "+");

            while(add.hasMoreTokens()) {
                temp += Integer.parseInt(add.nextToken());
            }

            if (sum == Integer.MAX_VALUE) { // sum이 초기값이라면 식에서 첫번째 숫자다. 즉, 이 숫자부터 뺄셈을 순서대로 해야 한다.
                sum = temp;
            } else { // 위에서 시작 값이 정해졌으므로 덧셈으로 묶은 식들을 빼준다.
                sum -= temp;
            }
        }
        System.out.println(sum);
    }
}
