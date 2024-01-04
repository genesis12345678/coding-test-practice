package Greedy.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 핵심은 작은 숫자들을 빼고 남은 숫자를 재배열해서 큰 수를 만드는 것이 아닌
 * 순서는 유지하며 큰 수가 앞으로 가게 해야 한다.
 * 맨 앞자리부터 다음 수와 비교하여 스택에 저장한다.
 * 다음 수가 작거나 같다면 그냥 push하고 다음 수가 크다면 K번 내에서 최대한 앞자리를 차지한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2812">백준 2812번 : 그리디 알고리즘 - 크게 만들기</a>
 * @since 2024-01-04
 */
public class Two {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());

        Stack<Integer> stack = new Stack<>();
        String s = br.readLine();

        int count = 0;
        for (int i = 0; i < N; i++) {
            int num = s.charAt(i) - '0';
            while (!stack.isEmpty() && stack.peek() < num && K > count) {
                stack.pop();
                count++;
            }
            stack.push(num);
        }

        /**
         * 위 for문에서 K번 만큼 pop연산을 수행하지 못할 수도 있다.
         */
        while (K > count) {
            stack.pop();
            count++;
        }

        /**
         *  while (!stack.isEmpty()) {
         *             sb.append(stack.pop());
         *         }
         *  위에 처럼 하면 역순이 되어 버린다.
         */

        for (Integer num : stack) {
            sb.append(num);
        }
        System.out.println(sb);
    }
}
