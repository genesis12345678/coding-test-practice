package bfs.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 사다리와 뱀의 이동 정보를 Map으로 저장한다. Map<칸, 이동할 칸>
 * 주사위 1~6까지 반복하면서 Map에 key가 있으면 다음 칸은 value를 넣는다.
 * bfs탐색하면서 100이 되는 순간 탐색을 종료한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/16928">백준 16928번 : 너비우선탐색 - 뱀과 사다리 게임</a>
 * @since 2024-01-30
 */
public class Five {

    static Map<Integer, Integer> ladder = new HashMap<>(); // 사다리 정보
    static Map<Integer, Integer> snake = new HashMap<>(); // 뱀 정보

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            ladder.put(x, y);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            snake.put(u, v);
        }

        bfs();

    }

    static void bfs() {
        boolean[] visit = new boolean[101];
        visit[1] = true;
        Queue<int[]> qu = new LinkedList<>();
        qu.offer(new int[]{1, 0});

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int num = now[0];
            int cnt = now[1];

            if (num == 100) {
                System.out.println(cnt);
                return;
            }

            for (int i = 1; i <= 6; i++) {
                int next = num + i;

                if (ladder.containsKey(next)) {
                    int ladderNext = ladder.get(next);
                    qu.offer(new int[]{ladderNext, cnt + 1});
                    visit[ladderNext] = true;
                }
                else if (snake.containsKey(next)) {
                    int snakeNext = snake.get(next);
                    qu.offer(new int[]{snakeNext, cnt + 1});
                    visit[snakeNext] = true;
                }
                else if (next <= 100 && !visit[next]) {
                    visit[next] = true;
                    qu.offer(new int[]{next, cnt + 1});
                }
            }
        }
    }
}
