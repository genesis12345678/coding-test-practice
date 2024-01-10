package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * dfs와 bfs에 개념 이해 문제이다.
 * dfs는 인접리스트와 재귀호출 방식으로 해결한다.
 * bfs은 인접리스트와 큐 자료구조를 이용해 해결한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1260">백준 1260번 : 너비우선탐색 - DFS와 BFS</a>
 * @since 2024-01-10
 */
public class One {

    static StringBuilder sb = new StringBuilder();
    static boolean[] visited; // 방문기록 저장용 배열
    static List<List<Integer>> graph = new ArrayList<>(); // 인접리스트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점(노드)의 개수
        int M = Integer.parseInt(st.nextToken()); // 간선의 개수
        int V = Integer.parseInt(st.nextToken()); // 시작 정점 번호

        visited = new boolean[N + 1]; // 방문기록 배열 초기화
        for (int i = 0; i <= N; i++) { // 1~N까지 인접 노드들을 저장할 배열 초기화
            graph.add(new ArrayList<>());
        }

        /**
         * 간선의 개수만큼 반복한다.
         * 문제에서 양방향이라고 했으니 각각 연결해준다.
         */
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        // 방문할 수 있는 노드가 여러 개인 경우 작은 것을 먼저 방문해야 하므로 각 리스트를 정렬해준다.
        for (int i = 1; i <= N; i++) {
            Collections.sort(graph.get(i));
        }

        dfs(V); // 깊이 우선 탐색
        sb.append("\n");

        visited = new boolean[N + 1]; // 너비 우선 탐색을 위한 방문기록 초기화
        bfs(V); // 너비 우선 탐색

        System.out.println(sb);
    }


    /**
     * 깊이 우선 탐색
     * 재귀호출 방식으로 각 리스트에 담겨있는 값(인접한 노드)들을 탐색한다.
     * 깊이우선이니 계속 파고들어가면서 탐색하는 방식이다.
     * 이미 탐색한 노드는 탐색하지 않아야 한다.
     */
    static void dfs(int x) {
        visited[x] = true;
        sb.append(x).append(" ");
        for (int num : graph.get(x)) {
            if (!visited[num]) {
                dfs(num);
            }
        }
    }

    /**
     * 너비 우선 탐색
     * 너비우선은 보통 큐를 이용해 구현한다.
     * 탐색 시작 번호를 먼저 처음에 넣고 그와 인접한 노드들을 큐에 차례대로 집어넣는다.
     * 이후 하나씩 큐에서 빼면서 그와 인접한 노드들을 집어넣는 것을 반복한다.
     * 방문기록으로 탐색제한을 두므로 제한이 존재한다.
     */
    static void bfs(int x) {
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(x);
        visited[x] = true;

        while (!qu.isEmpty()) {
            int num = qu.poll();
            sb.append(num).append(" ");

            for (int n : graph.get(num)) {
                if(!visited[n]) {
                    visited[n] = true;
                    qu.offer(n);
                }
            }
        }
    }
}
