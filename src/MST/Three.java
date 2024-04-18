package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/6497">백준 6497번 - 최소 스패닝 트리 : 전력난</a>
 * @since 2024-04-17
 */
public class Three {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Edge> pq;

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0) {
                break;
            }
            pq = new PriorityQueue<>();

            parent = new int[m];
            for (int i = 0; i < m; i++) {
                parent[i] = i;
            }

            int sum = 0;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                sum += z;

                pq.offer(new Edge(x, y, z));
            }

            int used = 0;
            int result = 0;

            while (used < m - 1) {
                Edge poll = pq.poll();

                int start = poll.start;
                int end = poll.end;

                if (find(start) != find(end)) {
                    union(start, end);
                    result += poll.dist;
                    used++;
                }
            }

            System.out.println(sum - result);

        }
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

