import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < m + 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges.add(new Edge(a, b, c));
        }

        edges.sort(Comparator.comparingInt(o -> o.dist));

        int worst = 0;
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);

            int start = edge.start;
            int end = edge.end;
            int dist = edge.dist;

            if (find(start) != find(end)) {
                union(start, end);

                if (dist == 0) {
                    worst++;
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        int best = 0;

        for (int i = edges.size() - 1; i >= 0; i--) {
            Edge edge = edges.get(i);

            int start = edge.start;
            int end = edge.end;
            int dist = edge.dist;

            if (find(start) != find(end)) {
                union(start, end);

                if (dist == 0) {
                    best++;
                }
            }
        }

        System.out.println((worst * worst) - (best * best));
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

    static class Edge{
        int start, end, dist;

        public Edge(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }
    }
}
