package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 크루스칼 알고리즘의 약간의 응용 문제다.
 * 발전소는 연결 없이 전기를 공급받을 수 있도록 구현해야 한다.
 * union과 find 연산에 수정이 필요한데, 서로 노드 간 번호 외에 발전소가 있다는 조건도 추가되어야 한다.
 * 그래서 발전소라면 발전소랑 연결하고, 아니라면 서로 연결하면 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/10423">백준 10423번 - 최소 스패닝 트리 : 전기가 부족해</a>
 * @since 2024-04-18
 */
public class Seven {

    static final int POWER_STATION = -1; //발전소 번호, 양수는 애매해질 수 있다.
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //노드 수
        int m = Integer.parseInt(st.nextToken()); //간선 수
        int k = Integer.parseInt(st.nextToken()); //발전소 개수

        parent = new int[n + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < k; i++) {
            parent[Integer.parseInt(st.nextToken())] = POWER_STATION; //발전소 표시
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(u, v, w)); //간선 정보 저장
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
        if (parent[a] == POWER_STATION) { //발전소면 발전소 리턴
            return POWER_STATION;
        }
        if (a == parent[a]) {
            return a;
        }

        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            //a나 b가 발전소에 연결된 것이라면 반대편도 발전소에 연결
            if (a == POWER_STATION) {
                parent[b] = POWER_STATION;

            } else if (b == POWER_STATION) {
                parent[a] = POWER_STATION;

            //외에는 일반적인 유니온
            } else {
                parent[b] = a;
            }
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
