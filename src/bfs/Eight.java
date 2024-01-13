package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dfs로 1번 노드부터 탐색하여 연겨 요소를 센다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11724">백준 11724번 : 너비우선탐색 : 연결 요소의 개수</a>
 * @since 2024-01-13
 */
public class Eight {

    static boolean[] visit;
    static List<List<Integer>> graph = new ArrayList<>();
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visit = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }


        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        for (int i = 1; i <= N; i++) {
            if (!visit[i]) {
                count++;
                dfs(i);
            }
        }
        System.out.println(count);

    }

    static void dfs(int x) {
        visit[x] = true;

        for (int num : graph.get(x)) {
            if (!visit[num]) {
                dfs(num);
            }
        }

    }
}
