package programmers.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class FarthestNode {

    static ArrayList<Node>[] A;
    static int[] dist;

    public static void main(String[] args) {
        int[][] edge = {
                {3, 6},
                {4, 3},
                {3, 2},
                {1, 3},
                {1, 2},
                {2, 4},
                {5, 2}
        };

        int n = 6;
        A = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < edge.length; i++) {
            int s = edge[i][0];
            int e = edge[i][1];

            A[s].add(new Node(e, 1));
            A[e].add(new Node(s, 1));
        }

        dijkstra(n);

        int max = dist[1];

        for (int i = 2; i <= n; i++) {
            if (dist[i] > max) {
                max = dist[i];
            }
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == max) {
                result++;
            }
        }

        System.out.println(result);

    }

    private static void dijkstra(int n) {
        boolean[] visit = new boolean[n + 1];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));

        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            int now = poll.adj;

            if (visit[now]) {
                continue;
            }

            visit[now] = true;

            for (Node next : A[now]) {
                if (dist[next.adj] > dist[now] + next.dist) {
                    dist[next.adj] = dist[now] + next.dist;
                    pq.offer(new Node(next.adj, dist[next.adj]));
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
