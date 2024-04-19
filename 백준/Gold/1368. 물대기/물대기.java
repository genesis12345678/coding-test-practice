
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        parent = new int[n + 1];
        int[] W = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            W[i] = Integer.parseInt(br.readLine());
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                int P = Integer.parseInt(st.nextToken());

                if (i == j) {
                    pq.offer(new Edge(0, i, W[i]));
                } else {
                    pq.offer(new Edge(i, j, P));
                }
            }
        }

        int result = 0;

        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            int start = e.start;
            int end = e.end;
            int dist = e.dist;

            if (find(start) != find(end)) {
                union(start, end);
                result += dist;
            }
        }

        System.out.print(result);
    }

    static int find(int a) {
        if (parent[a] == a) {
            return a;
        }

        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    static class Edge implements Comparable<Edge>{
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
