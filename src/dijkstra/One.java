package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 기본적인 다익스트라 알고리즘은 동일하지만, 출발 노드가 정해져 있지 않고 다시 돌아오는 것까지 고려해야 하는 문제다.
 * 그래서 최단거리 배열을 1차원이 아닌 2차원으로 선언해서 i에서 j로 가는 최단 경로로 문제를 해결한다.
 * 이렇게 모든 최단거리를 구해놓으면 도착 노드를 x 라고 했으니까
 * dist[x][i] + dist[i][x], 즉 도착하고 다시 돌아가는 거리를 더했을 때 최댓값이 정답이 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1238">백준 1238번 - 다익스트라 : 파티</a>
 * @since 2024-04-01
 */
public class One {

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //노드 수
        int m = Integer.parseInt(st.nextToken()); //에지
        int x = Integer.parseInt(st.nextToken()); //도착 마을

        int[][] dist = new int[n + 1][n + 1]; //i~j 최단거리

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) { //출발지와 도착지가 같은 곳을 제외하고 나머지 초기화
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        ArrayList<Node>[] a = new ArrayList[n + 1]; //인접 리스트
        for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()); //출발
            int e = Integer.parseInt(st.nextToken()); //도착
            int v = Integer.parseInt(st.nextToken()); //가중치

            a[s].add(new Node(e, v));
        }

        boolean[][] visit = new boolean[n + 1][n + 1];
        Queue<Info> pq = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) {

            pq.offer(new Info(0, i)); //반복마다 출발 노드를 다르게

            while (!pq.isEmpty()) {
                Info poll = pq.poll();
                int now = poll.node;

                if (visit[i][now]) {
                    continue;
                }
                visit[i][now] = true;

                for (Node next : a[now]) {
                    int nextNode = next.node;
                    int d = next.dist;

                    if (dist[i][nextNode] > dist[i][now] + d) {
                        dist[i][nextNode] = dist[i][now] + d;
                        pq.offer(new Info(dist[i][nextNode], nextNode));
                    }
                }
            }
        }

        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            if (i == x) { //출발과 도착이 같은 경우 continue
                continue;
            }

            max = Math.max(max, dist[x][i] + dist[i][x]);
        }

        System.out.println(max);
    }
}
