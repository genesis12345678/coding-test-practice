package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 좌표들 간의 거리를 피타고라스의 정리를 이용해 구한다.
 * 입력과 정답이 실수인 것만 주의하면 어려울 것 없다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/4386">백준 4386번 - 최소 스패닝 트리 : 별자리 만들기</a>
 * @since 2024-04-19
 */
public class Ten {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        parent = new int[n];
        Point[] points = new Point[n]; //좌표 정보 배열

        for (int i = 0; i < n; i++) {
            parent[i] = i;

            StringTokenizer st = new StringTokenizer(br.readLine());

            //x, y 좌표 실수형 주의
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            points[i] = new Point(x, y);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) { //출발점과 도착점이 다를 때만 간선 정보로 저장
                    Point p1 = points[i];
                    Point p2 = points[j];

                    //간선 거리, 피타고라스 정리
                    double dist = Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));

                    pq.offer(new Edge(i, j, dist));
                }
            }
        }

        double result = 0;

        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            int start = e.start;
            int end = e.end;
            double dist = e.dist;

            if (find(start) != find(end)) {
                union(start, end);
                result += dist;
            }
        }

        System.out.printf("%.2f",result);
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

    static class Point{
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
