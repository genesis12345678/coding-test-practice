package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/16398">백준 16398번 - 최소 스패닝 트리 : 행성 연결</a>
 * @since 2024-04-17
 */
public class Four {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int c = Integer.parseInt(st.nextToken());

                if (i != j) {
                    pq.offer(new Edge(i, j, c));
                }
            }
        }

        int used = 0;
        long result = 0;

        while (used < n - 1) {
            Edge e = pq.poll();

            int start = e.start;
            int end = e.end;
            int dist = e.dist;

            if (find(start) != find(end)) {
                union(start, end);
                used++;
                result += dist;
            }
        }

        System.out.println(result);

    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
        }
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }

        return parent[a] = find(parent[a]);
    }

    static class Edge implements Comparable<Edge> {
        int start, end, dist;

        public Edge(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return dist - o.dist;
        }
    }

}
