package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 거리를 0 또는 1로 저정하고, 크루스칼 알고리즘을 두 번 수행한다.
 * 첫 번째는 일반적인 오름차순으로 진행한다. 그럼 거리가 0부터 탐색할 거고, 0은 오르막길 이므로 0의 최대개수를 알 수 있을거다.
 * 두 번째는 반대로 내림차순으로 진행한다. 그럼 거리가 1부터 탐색할 거고, 0이 나오는 최소개수를 알 수 있을거다.
 * 둘의 차이를 출력하면 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/13418">백준 13418번 - 최소 스패닝 트리 : 학교 탐방하기</a>
 * @since 2024-04-19
 */
public class Eight {

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

        Collections.sort(edges);

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
