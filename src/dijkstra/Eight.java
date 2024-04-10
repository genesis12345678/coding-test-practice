package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 어떤 경로가 최단 경로임을 묻는 문제다.
 * 다익스트라를 통해 최단 경로를 구하면서 각 노드 이전에는 어떤 노드가 오는지 배열에 인덱스와 값으로 저장한다.
 * 값이 0이 아니면 연결이 됐다는 뜻이므로 인덱스와 값을 출력해준다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2211">백준 2211번 - 다익스트라 : 네트워크 복구</a>
 * @since 2024-04-09
 */
public class Eight {

    static ArrayList<Node>[] A;
    static int[] parent;
    static int[] dist;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        A = new ArrayList[n + 1];
        dist = new int[n + 1];
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            A[a].add(new Node(b, c));
            A[b].add(new Node(a, c));
        }

        dijkstra();

        List<int[]> result = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            if (parent[i] > 0) {
                result.add(new int[]{parent[i], i});
            }
        }


        StringBuilder sb = new StringBuilder();

        sb.append(result.size()).append("\n");
        for (int[] ints : result) {
           sb.append(ints[0]).append(" ").append(ints[1]).append("\n");
        }
        System.out.print(sb);

    }

    private static void dijkstra() {
        Queue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(1, 0));
        dist[1] = 0;

        boolean[] visit = new boolean[n + 1];

        while (!qu.isEmpty()) {
            Node poll = qu.poll();
            int now = poll.adj;

            if (visit[now]) {
                continue;
            }

            visit[now] = true;

            for (Node next : A[now]) {

                if (dist[next.adj] > dist[now] + next.dist) {
                    dist[next.adj] = dist[now] + next.dist;
                    parent[next.adj] = now;
                    qu.offer(new Node(next.adj, dist[next.adj]));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int adj, dist;

        public Node(int adj, int dist) {
            this.adj = adj;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
}
