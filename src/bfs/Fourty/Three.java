package bfs.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 숨바꼭질 문제에서 이동 경로까지 추가된 문제다.
 * 경로를 담을 배열도 만들어서 bfs 탐색을 하면서 경로에 추가한다.
 * 경로 배열 각 인덱스에는 이전 위치의 값이 들어있다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/13913">백준 13913번 : 너비우선탐색 - 숨바꼭질 4</a>
 * @since 2024-01-29
 */
public class Three {
    static int[] path; // 경로 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N == K) { // 처음부터 위치가 같다.
            System.out.println(0);
            System.out.println(N);

        } else {
            int count = bfs(N, K); // bfs탐색 후 시간이 반환된다.
            System.out.println(count);

            Stack<Integer> stack = new Stack<>();
            stack.push(K); // 스택에 마지막 위치부터 저장

            int index = K;
            while (index != N) { // 각 인덱스에는 이전 위치가 저장되어 있다. 역추적 하면서 스택에 저장한다.
                stack.push(path[index]);
                index = path[index];
            }
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop()).append(" ");
            }
            System.out.println(sb);
        }
    }

    static int bfs(int n, int k) {
        Queue<int[]> qu = new LinkedList<>();
        boolean[] visit = new boolean[100_001];

        path = new int[100_001];
        qu.offer(new int[]{n, 0}); // 시작위치, 시간
        visit[n] = true;

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int x = now[0];
            int cnt = now[1];

            if (x == k) {
                return cnt;
            }

            /**
             * 각 *2, +1, -1 path 인덱스에 현재 큐에서 꺼낸 값을 저장한다.
             * 즉, 이전 위치를 저장시키는 것이다.
             */
            if (x * 2 <= 100_000 && !visit[x * 2]) {
                path[x * 2] = x;
                visit[x * 2] = true;
                qu.offer(new int[]{x * 2, cnt + 1});
            }
            if (x + 1 <= 100_000 && !visit[x + 1]) {
                path[x + 1] = x;
                visit[x + 1] = true;
                qu.offer(new int[]{x + 1, cnt + 1});
            }
            if (x - 1 >= 0 && !visit[x - 1]) {
                path[x - 1] = x;
                visit[x - 1] = true;
                qu.offer(new int[]{x - 1, cnt + 1});
            }

        }
        return 0;
    }
}
