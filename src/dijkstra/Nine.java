package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * dist를 2차원 배열로 선언하여 dist[n+1][k+1]로 만든다.
 * 1번부터 n번 노드까지 0~k번 포장했을 때 최소 비용을 모두 다익스트라를 이용하여 계산한다.
 * 최종 답은 1번부터 N번에 대한 최소 경로이므로 dist[n][0~k] 중 최솟값이 된다.
 * 이때 최대 가중치가 1,000,000고, 최대 경로 수가 50,000으로 int 형은 값을 담지 못할 수 있으므로 long을 사용해야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1162">백준 1162번 - 다익스트라 : 도로포장</a>
 * @since 2024-04-09
 */
public class Nine {

    static long[][] dist;
    static int n, m, k;
    static ArrayList<Node>[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); //노드 수
        m = Integer.parseInt(st.nextToken()); //에지 수
        k = Integer.parseInt(st.nextToken()); //포장할 수

        A = new ArrayList[n + 1];
        dist = new long[n + 1][k + 1];//1번부터 n번 노드까지 0~k번 포장했을 때 최소 비용

        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<>();
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            //양방향 연결
            A[s].add(new Node(e, 0, v));
            A[e].add(new Node(s, 0, v));
        }

        dijkstra(); //다익스트라 수행

        long min = Long.MAX_VALUE;
        for (int i = 1; i <= k; i++) {
            min = Math.min(min, dist[n][i]);
        }

        System.out.println(min);
    }

    private static void dijkstra() {
        Queue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(1, 0, 0));

        dist[1][0] = 0;

        boolean[][] visit = new boolean[n + 1][k + 1];

        while (!qu.isEmpty()) {
            Node cur = qu.poll();
            int now = cur.adj;
            int count = cur.count;

            if (visit[now][count]) {
                continue;
            }
            visit[now][count] = true;

            for (Node next : A[now]) {
                int nextNode = next.adj;

                //포장하고 가는 경우
                //현재 거리에서 가중치를 더하지 않는 것이 포인트다.
                if (count < k && dist[nextNode][count + 1] > dist[now][count]) {
                    dist[nextNode][count + 1] = dist[now][count];
                    qu.offer(new Node(nextNode, count + 1, dist[nextNode][count + 1]));
                }

                //포장하지 않고 가는 경우
                //일반적인 다익스트라 수행이랑 똑같다.
                if (dist[nextNode][count] > dist[now][count] + next.dist) {
                    dist[nextNode][count] = dist[now][count] + next.dist;
                    qu.offer(new Node(nextNode, count, dist[nextNode][count]));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int adj, count; //인접 노드, 포장 횟수
        long dist; //거리

        public Node(int adj, int count, long dist) {
            this.adj = adj;
            this.count = count;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return (int) (this.dist - o.dist);
        }
    }
}
