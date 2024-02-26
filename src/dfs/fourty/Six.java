package dfs.fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * dfs로 순환선인 노선을 구한 뒤에
 * bfs로 각 순환선까지 거리를 구한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/16947">백준 16947번 : 깊이우선탐색 - 서울 지하철 2호선</a>
 * @since 2024-02-26
 */
public class Six {

    static List<List<Integer>> tree = new ArrayList<>();
    static boolean[] cycle;
    static boolean[] visit;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        cycle = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //양방향 연결
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        visit = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (cycleCheckDfs(i, i, i)) {//사이클이 발생하면 종료
                break;
            }
        }

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            visit = new boolean[N + 1];
            if (!cycle[i]) {//순환선이 아닌 노선만 진행
                arr[i] = bfs(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb);
    }

    static int bfs(int now) {
        Queue<int[]> qu = new LinkedList<>();
        qu.offer(new int[]{now, 0});//역(노드), 거리

        while (!qu.isEmpty()) {
            int[] cur = qu.poll();
            if (cycle[cur[0]]) {//순환선을 발견하면 거리를 반환
                return cur[1];
            }
            for (int next : tree.get(cur[0])) {
                if (!visit[next]) {
                    visit[next] = true;
                    qu.offer(new int[]{next, cur[1] + 1});
                }
            }
        }
        return 0;
    }

    static boolean cycleCheckDfs(int start, int prev, int cur) {
        cycle[cur] = true;
        for (int next : tree.get(cur)) {
            if (!cycle[next]) {
                if (cycleCheckDfs(start, cur, next)) {
                    return true;
                }
            } else if (prev != next && start == next) {//사이클이 발생하면 종료
                return true;
            }
        }
        cycle[cur] = false;
        return false;
    }
}

