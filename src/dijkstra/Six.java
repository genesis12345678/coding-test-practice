package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 출발 노드가 정해져 있고 최단 거리를 구하는 가장 일반적인 다익스트라 문제다.
 * 출발 노드 1을 시작으로 각 노드까지 최단 거리를 구하고 n번째 값을 출력하면 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/5972">백준 5972번 - 다익스트라 : 택배 배송</a>
 * @since 2024-04-07
 */
public class Six {

    static ArrayList<Node>[] A;
    static int[] dist;
    static int max = 100_000_000;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        A = new ArrayList[n + 1];
        dist = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<>();
            dist[i] = max;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            A[a].add(new Node(b, c));
            A[b].add(new Node(a, c));
        }

        dijkstra();
        System.out.println(dist[n]);
    }

    private static void dijkstra() {
        dist[1] = 0;
        boolean[] visit = new boolean[n + 1];
        Queue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(1, 0));

        while (!qu.isEmpty()) {
            Node poll = qu.poll();
            int now = poll.adj;

            if (visit[now]) {
                continue;
            }
            visit[now] = true;

            for (Node next : A[now]) {
                if (dist[next.adj] > dist[now] + next.dist) {
                    dist[next.adj] = dist[now] + next.dist;
                    qu.offer(new Node(next.adj, dist[next.adj]));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
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
