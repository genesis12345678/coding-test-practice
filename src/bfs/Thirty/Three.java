package bfs.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * bfs 방식으로 1부터 각 숫자까지 몇번 이동하는지 계산한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1389">백준 1389번 : 너비우선탐색 - 케빈 베이컨의 6단계 법칙</a>
 * @since 2024-01-21
 */
public class Three {

    static List<List<Integer>> graph = new ArrayList<>(); // 인접 리스트
    static int N;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 무방향 그래프
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int minValue = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 1; i <= N; i++) {
            // bfs를 통해 각 숫자까지 이동한 수의 합이 나온다.
            int count = bfs(i);
            if (count < minValue) {
                minIndex = i;
                minValue = count;
            }
        }
        System.out.println(minIndex);
    }

    static int bfs(int x) {
        visit = new boolean[N + 1]; // 방문 배열 초기화
        visit[x] = true; // 시작 지점 방문

        Queue<int[]> qu = new LinkedList<>();
        qu.add(new int[]{x, 0}); // 시작, 이동횟수

        int count = 0;
        while (!qu.isEmpty()) {
            int[] n = qu.poll();
            visit[n[0]] = true;
            // qu에서 빼주면서 이동횟수들을 누적합 해준다.
            count += n[1];

            for (int a : graph.get(n[0])) {
                if (!visit[a]) {
                    qu.offer(new int[]{a, n[1] + 1}); // 전 이동 횟수에 +1을 더해준다.
                    visit[a] = true;
                }
            }
        }
        return count;
    }
}
