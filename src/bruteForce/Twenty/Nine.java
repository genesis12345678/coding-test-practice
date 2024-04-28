package bruteForce.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * 모든 순열을 알려면 재귀 호출의 depth를 인덱스로 배열에 값을 저장하면 된다.
 * 중복 호출을 제거하면서 재귀 호출 방식으로 해결한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/10974">백준 10974번 - 브루트포스 : 모든 순열</a>
 * @since 2024-04-27
 */
public class Nine {

    static boolean[] visit;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        visit = new boolean[n + 1];
        arr = new int[n + 1];

        solution(0);
        System.out.print(sb);
    }

    static void solution(int depth) {
        if (depth == n) {
            for (int i = 0; i < n; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                arr[depth] = i;
                solution(depth + 1);
                visit[i] = false;
            }
        }
    }
}
