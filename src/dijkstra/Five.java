package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Five {


    static ArrayList<Node>[] A;
    static int[] dist;
    static boolean[] visit;
    static int max = 100_000_000;

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            A = new ArrayList[n + 1];
            dist = new int[n + 1];
            visit = new boolean[n + 1];

            for (int i = 1; i <= n; i++) {
                A[i] = new ArrayList<>();
                dist[i] = max;
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                A[b].add(new Node(a, s));
            }

            dijkstra(c);

            int count = 0;
            int time = 0;

            for (int i = 1; i <= n; i++) {
                if (dist[i] != max) {
                    count++;
                    time = Math.max(time, dist[i]);
                }
            }

            System.out.println(count + " " + time);

        }
    }

    private static void dijkstra(int c) {
        dist[c] = 0;

        Queue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(c, 0));

        while (!qu.isEmpty()) {
            Node poll = qu.poll();
            int now = poll.adj;

            if (visit[now]) {
                continue;
            }

            visit[now] = true;

            for (Node next : A[now]) {
                int nextNode = next.adj;
                if (dist[nextNode] > dist[now] + next.dist) {
                    dist[nextNode] = dist[now] + next.dist;
                    qu.offer(new Node(nextNode, dist[nextNode]));
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
