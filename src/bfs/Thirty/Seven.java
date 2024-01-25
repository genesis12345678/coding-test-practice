package bfs.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * bfs 방식으로 S층부터 위로 가는 경우, 아래로 가는 경우를 큐에 이동횟수와 같이 저장하면서 탐색한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/5014">백준 5014번 : 너비우선탐색 - 스타트링크</a>
 * @since 2024-01-25
 */
public class Seven {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int F = Integer.parseInt(st.nextToken()); // 건물 높이
        int S = Integer.parseInt(st.nextToken()); // 강호의 위치
        int G = Integer.parseInt(st.nextToken()); // 스타트링크 위치
        int U = Integer.parseInt(st.nextToken()); // 위로 U층
        int D = Integer.parseInt(st.nextToken()); // 아래로 D층

        // 밑으로 내려갈 수 없는데 현재 위치가 목표보다 높거나 같은 경우
        if (D == 0 && S >= G) {
            System.out.println("use the stairs");
            return;
        }

        Queue<int[]> qu = new LinkedList<>();
        boolean[] visit = new boolean[1_000_001]; // F의 최댓값으로 초기화

        qu.offer(new int[]{S, 0}); // 시작 설정

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int cur = now[0];

            int nu = cur + U; // 다음 위층
            int nd = cur - D; // 다음 아래층

            if (cur == G) { // 목표에 도달했다면 이동횟수 출력
                System.out.println(now[1]);
                return;
            }

            // 최대 층을 넘어설 수 없다.
            if (nu <= F && !visit[nu]) {
                visit[nu] = true;
                qu.offer(new int[]{nu, now[1] + 1});
            }
            // 건물은 1층부터 시작한다.
            if (nd > 0 && !visit[nd]) {
                visit[nd] = true;
                qu.offer(new int[]{nd, now[1] + 1});
            }
        }
        // 여기까지 왔다면 갈 수 없다는 뜻이다.
        System.out.println("use the stairs");
    }
}
