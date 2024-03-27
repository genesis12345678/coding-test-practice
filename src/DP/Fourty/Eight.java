package DP.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 아이디어
 * dp[i]를 i가 되기 전에 숫자라고 생각해본다.
 * 즉, dp[i]에는 i의 부모가 담겨 있다고 생각하면 된다.
 * 초기에는 -1로 초기화해서 나누기 3, 나누기 2, 빼기 1 연산을 하면서 값이 -1이 아닌, 즉 아직 한번도 계산하지 않은 값일 때만 다음 탐색 대상이 되도록 한다.
 * 기본적으로 BFS 탐색으로 진행한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/12852">백준 12852번 - DP : 1로 만들기 2</a>
 * @since 2024-03-25
 */
public class Eight {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        Queue<int[]> qu = new LinkedList<>();
        qu.add(new int[]{n, 0});

        while (true) {
            int[] now = qu.poll();
            int num = now[0];
            int cnt = now[1];

            if (num == 1) {
                printResult(cnt, n, dp);
                break;
            }

            if (num / 3 >= 0 && num % 3 == 0 && dp[num / 3] == -1) {
                qu.offer(new int[]{num / 3, cnt + 1});
                dp[num / 3] = num;
            }

            if (num / 2 >= 0 && num % 2 == 0 && dp[num / 2] == -1) {
                qu.offer(new int[]{num / 2, cnt + 1});
                dp[num / 2] = num;
            }

            if (num - 1 >= 0 && dp[num - 1] == -1) {
                qu.offer(new int[]{num - 1, cnt + 1});
                dp[num - 1] = num;
            }
        }
        System.out.println("dp = " + Arrays.toString(dp));
    }

    private static void printResult(int cnt, int n, int[] arr) {
        System.out.println(cnt);
        Stack<Integer> result = new Stack<>();
        result.push(1);

        int cur = 1;

        while (cur != n) {
            result.push(arr[cur]);
            cur = arr[cur];
        }

        StringBuilder sb = new StringBuilder();

        while (!result.isEmpty()) {
            sb.append(result.pop()).append(" ");
        }
        System.out.println(sb);
    }
}
