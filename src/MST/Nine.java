package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 직접 논에 우물을 파는 것과 다른 논과 연결하는 것 두 가지 경우가 있다고 한다.
 * 다른 논과 연결하는 것은 일반적인 간선이라고 생각하면 된다.
 * 직접 우물을 파는 것을 0이라는 우물을 놓고 0과 연결한다고 봐야 한다. 즉, 공통 노드 0은 다른 모든 노드들과 우물을 팔 때 비용으로 이어져 있는 것이다.
 * 그래서 간선 정보를 우선순위 큐에 저장할 때 출발==도착이면, 0~도착에 거리는 우물 비용으로 저장한다.
 * 외에는 입력대로 저장한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1368">백준 1368번 - 최소 스패닝 트리 : 물대기</a>
 * @since 2024-04-19
 */
public class Nine {
    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        parent = new int[n + 1];
        int[] W = new int[n + 1]; //우물 비용

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            W[i] = Integer.parseInt(br.readLine());
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                int P = Integer.parseInt(st.nextToken()); //출발 ~ 도착 비용

                if (i == j) { //출발==도착이면, 0번(우물)에 자기 자신 우물 비용만큼 거리로 저장
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
