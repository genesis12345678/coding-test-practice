package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 일반적인 다익스트라를 사용하지만 경로까지 출력해야 해서 까다로운 문제다.
 * 일단 최단 경로는 일반적인 다익스트라로 해결할 수 있다.
 * 다익스트라를 수행하면서 path라는 배열에 각 인덱스의 부모 노드를 저장하면서 진행한다.
 * 예를 들어 5번 인덱스에 4가 저장된다면 5번 노드는 4번 노드를 거쳐 도착한 것으로 볼 수 있다.
 * 이런 식으로 저장하면서 다익스트라가 종료되면 path 배열에서 값이 0이 아닐 때까지, 즉 출발 노드가 아닐 때까지 경로를 저장한 다음 역순으로 출력하면 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11779">백준 11779번 - 다익스트라 : 최소비용 구하기 2</a>
 */
public class Three {

    static class Node {
        int next, dist;

        public Node(int next, int dist) {
            this.next = next;
            this.dist = dist;
        }
    }

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

    static int[] dist;
    static ArrayList<Node>[] a;
    static boolean[] visit;
    static int[] path;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        a = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            a[s].add(new Node(e, v));
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);
        printResult(end);
    }

    private static void printResult(int end) {
        System.out.println(dist[end]);

        List<Integer> route = new ArrayList<>();

        int cur = end;
        while (cur != 0) {
            route.add(cur);
            cur = path[cur];
        }

        System.out.println(route.size());

        for (int i = route.size() - 1; i >= 0; i--) {
            System.out.print(route.get(i) + " ");
        }
    }

    private static void dijkstra(int start) {
        dist = new int[n + 1];
        visit = new boolean[n + 1];
        path = new int[n + 1];

        dist[start] = 0;

        Queue<Info> qu = new PriorityQueue<>();
        qu.offer(new Info(0, start));

        while (!qu.isEmpty()) {
            Info poll = qu.poll();
            int now = poll.node;

            if (visit[now]) {
                continue;
            }

            visit[now] = true;

            for (Node next : a[now]) {
                int nextNode = next.next;

                if (dist[nextNode] > dist[now] + next.dist) {
                    dist[nextNode] = dist[now] + next.dist;
                    qu.offer(new Info(dist[nextNode], nextNode));
                    path[nextNode] = now;
                }
            }
        }
    }
}
