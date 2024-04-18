package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 위치가 좌표인 까다로운 문제였다.
 * 먼저 좌표의 위치를 저장하고, M개의 이미 연결된 통로를 union으로 묶어준다.
 * 좌표들 간의 거리를 피타고라스의 정리로 구해 에지 정보로 우선순위 큐에 저장한다.
 * 다음 크루스칼 알고리즘으로 최단 거리를 구해주면 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1774">백준 1774번 - 최소 스패닝 트리 : 우주신과의 교감</a>
 * @since 2024-04-18
 */
public class Five {

    static int[] parent;
    static int n, m;
    static Point[] points;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        points = new Point[n + 1];
        parent = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points[i] = new Point(x, y);
        }

        addEdges(); //좌표들 간의 거리 정보(에지) 저장

        //이미 연결된 통로 union 으로 묶기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        //크루스칼 알고리즘으로 구한 최소 거리 소수점 둘째 자리까지 출력
        System.out.printf("%.2f", kruscal());
    }

    static void addEdges() {

        for (int i = 1; i <= n; i++) { //출발
            for (int j = 1; j <= n; j++) { //도착

                if (i != j) { //출발과 도착이 다를 때만

                    Point p1 = points[i];
                    Point p2 = points[j];

                    //피타고리스의 정리로 두 좌표 간의 거리 구하기
                    double dist = Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
                    pq.offer(new Edge(i, j, dist));
                }
            }
        }
    }

    static double kruscal() {
        double result = 0;

        int used = 0;
        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            int start = e.start;
            int end = e.end;
            double dist = e.dist;

            if (find(start) != find(end)) {
                union(start, end);
                used++;
                result += dist;
            }

            if (used == n - 1) {
                break;
            }
        }
        return result;
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

    static class Point{
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int start, end;
        double dist;

        public Edge(int start, int end, double dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(dist, o.dist);
        }
    }
}
