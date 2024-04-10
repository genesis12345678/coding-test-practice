package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 일반적인 다익스트라 알고리즘과 같고, 최단경로를 수행하면서 하나만 더 확인해야 한다.
 * 해당 번호에 와드가 있는지 체크해야 하고, 와드가 없는 경우에만 최단 경로를 탐색해야 한다.
 * 마지막 번호(N-1번)은 무조건 1이 올 거라고 문제에는 나와 있지만, 유일하게 시야에 보이면서 갈 수 있는 곳이라고 하니 0으로 바꿔준다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/17396">백준 17396번 - 다익스트라 : 백도어</a>
 * @since 2024-04-10
 */
public class Ten {

    static ArrayList<Node>[] A;
    static long[] dist;
    static int[] wad;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        A = new ArrayList[n];
        dist = new long[n];
        wad = new int[n];
        visit = new boolean[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            A[i] = new ArrayList<>();
            dist[i] = Long.MAX_VALUE;
            wad[i] = Integer.parseInt(st.nextToken());
        }
        wad[n - 1] = 0; //와드 마지막 번호 0 변경

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            //양방향 연결
            A[a].add(new Node(b, t));
            A[b].add(new Node(a, t));
        }

        dijkstra();
        System.out.println(dist[n - 1] == Long.MAX_VALUE ? -1 : dist[n - 1]);
    }

    private static void dijkstra() {
        Queue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(0, 0));
        dist[0] = 0;

        while (!qu.isEmpty()) {
            Node cur = qu.poll();

            int now = cur.adj;

            if (visit[now]) {
                continue;
            }

            visit[now] = true;

            for (Node next : A[now]) {
                if (wad[next.adj] == 0) { //와드가 없는 곳만 탐색
                    if (dist[next.adj] > dist[now] + next.dist) {
                        dist[next.adj] = dist[now] + next.dist;
                        qu.offer(new Node(next.adj, (int) dist[next.adj]));
                    }
                }
            }
        }
    }
    static class Node implements Comparable<Node> {

        int adj, dist;

        public Node(int adj, int dist) {
            this.adj = adj;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
}
