package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 일반적인 크루스칼 알고리즘을 수행하면서 서로 다른 대학교일 때만 최단거리를 구해준다.
 * 사용된 에지 수가 n-1이 아니면 모든 학교를 연결하는 경로가 없는 경우다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/14621">백준 14621번 - 최소 스패닝 트리 : 나만 안되는 연애</a>
 * @since 2024-04-18
 */
public class Six {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }

        char[] arr = new char[n + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(u, v, d));
        }

        int result = 0;
        int used = 0;
        while (!pq.isEmpty()) {

            Edge e = pq.poll();

            int start = e.start;
            int end = e.end;
            int dist = e.dist;

            //연결되어 있지 않으면서 서로 다른 대학교일 때만 업데이트
            if (find(start) != find(end) && arr[start] != arr[end]) {
                union(start, end);
                used++;
                result += dist;
            }
        }

        //사용된 에지 수가 n-1이어야 모든 학교를 연결한 경우다.
        System.out.println(used == n - 1 ? result : -1);
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
