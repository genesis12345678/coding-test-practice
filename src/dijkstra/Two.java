package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 처음에는 모든 경로에 대해 다익스트라를 사용하여 최단 경로를 구해 1~v1 + v1~v2 + v2~n 의 합을 구했다.
 * 하지만 시간초과가 발생했는데, 해답은 1(시작), v1, v2에 대해서만 각 경로의 최단 경로를 구한 다음에
 * 1~v1 + v1~v2 + v2~n과
 * 1~v2 + v2~v1 + v1~n 중 더 작은 값을 출력하면 된다.
 * 물론 중간에 초기값이 그대로 있으면 갈 수 없으니 -1을 출력해야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1504">백준 1504번 - 다익스트라 : 특정한 최단 경로</a>
 * @since 2024-04-02
 */
public class Two {

    //도착지와 거리 정보를 갖는 클래스
    static class Node {
        int node, dist;

        public Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    // 우선순위 큐에 value값 기준 오름차순 정렬할 정보
    static class Info implements Comparable<Info> {
        int value, node;

        public Info(int value, int node) {
            this.value = value;
            this.node = node;
        }

        @Override
        public int compareTo(Info o) {
            return this.value - o.value;
        }
    }

    static ArrayList<Node>[] a;
    static int max = 200_000 * 1000; //최대 간선 개수 200,000에 최대 거리 1,000이므로 최대 거리는 둘을 곱한 값이다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //정점 개수
        int e = Integer.parseInt(st.nextToken()); //간선 개수

        a = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            //양방향 연결
            a[start].add(new Node(end, v));
            a[end].add(new Node(start, v));
        }

        st = new StringTokenizer(br.readLine());

        //반드시 거쳐야 할 두 정점
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] fromStart = dijkstra(n, 1); //시작이 1번일 때 최단 경로
        int[] fromV1 = dijkstra(n, v1); //시작이 v1번일 때 최단 경로
        int[] fromV2 = dijkstra(n, v2); //시작이 v2번일 때 최단 경로

        int path1 = fromStart[v1] + fromV1[v2] + fromV2[n]; //1번에서 v1을 거쳐 v2를 갔다 n번에 도착하는 최단 경로
        int path2 = fromStart[v2] + fromV2[v1] + fromV1[n]; //1번에서 v2를 거쳐 v1을 갔다 n번에 도착하는 최단 경로

        if (path1 >= max && path2 >= max) { //두 경로 모두 초기값 그대로면 경로가 존재하지 않는다.
            System.out.println(-1);
        } else {
            System.out.println(Math.min(path1, path2)); //두 경로 중 최단 경로를 출력한다.
        }

    }

    private static int[] dijkstra(int n, int start) {
        Queue<Info> pq = new PriorityQueue<>();
        int[] d = new int[n + 1];

        Arrays.fill(d, max);
        boolean[] visit = new boolean[n + 1];

        pq.offer(new Info(0, start));

        d[start] = 0;

        while (!pq.isEmpty()) {
            Info poll = pq.poll();
            int now = poll.node;

            if (visit[now]) {
                continue;
            }

            visit[now] = true;

            for (Node next : a[now]) {
                int nextNode = next.node;
                int dist = next.dist;

                if (d[nextNode] > d[now] + dist) {
                    d[nextNode] = d[now] + dist;
                    pq.offer(new Info(d[nextNode], nextNode));
                }
            }
        }

        return d;
    }
}
