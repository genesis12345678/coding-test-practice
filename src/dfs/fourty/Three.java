package dfs.fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 이웃한 노드와 usado를 갖는 Pair 클래스를 만든다.
 * 입력으로 오는 두 동영상 쌍을 양방향으로 저장한다.
 * dfs를 통해 usado가 k 이상일 때만 탐색하면서 count를 늘린다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/15591">백준 15591번 : 깊이우선탐색 - MooTube(Silver)</a>
 * @since 2024-02-23
 */
public class Three {

    static List<List<Pair>> graph = new ArrayList<>();
    static boolean[] visit;

    static class Pair {
        int node;
        int usado;

        public Pair(int node, int usado) {
            this.node = node;
            this.usado = usado;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());//동영상 1
            int q = Integer.parseInt(st.nextToken());//동영상 2
            int r = Integer.parseInt(st.nextToken());//USADO

            //양방향 연결
            graph.get(p).add(new Pair(q, r));
            graph.get(q).add(new Pair(p, r));
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            //k=k 일 때 v번 동영상을 보면 몇 개의 동영상이 추천되야 할까
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            visit = new boolean[N + 1];
            sb.append(dfs(k, v)).append("\n");
        }
        System.out.println(sb);
    }

    static int dfs(int k, int start) {

        int count = 0;
        visit[start] = true;

        for (Pair next : graph.get(start)) {
            //usado가 k 이상인 동영상이 추천되어야 한다.
            if (!visit[next.node] && next.usado >= k) {
                count++;
                count += dfs(k, next.node);
            }
        }
        return count;
    }
}
