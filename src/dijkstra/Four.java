package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 아이디어
 * 출발지 s 지점에서 목적지 후보 T1, T2.. Tt 까지 최단 경로에 g와 h 경로가 포함되어 있는지 확인해야 하는 문제다.
 * 다익스트라를 이용하여 s, g, h를 출발지로 하는 각 최단 경로를 구한다.
 * s -> g -> h -> t = s -> t
 * s -> h -> g -> t = s -> t
 * 각 목적지 후보에 따라 g와 h 경로 순서를 바꿔가면서 g,h 경로가 포함되어 있는지 확인한다.
 * 합이 같으면 포함되어 있다는 뜻이다.
 * 왜냐하면 최단 경로에 포함되어야 최단 경로의 일부분이기 때문이다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/9370">백준 9370번 - 다익스트라 : 미확인 도착지</a>
 * @since 2024-04-06
 */
public class Four {

    static ArrayList<Node>[] A;
    static int max = 100_000_000;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine()); //테스트 케이스

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken()); //도시 개수
            int m = Integer.parseInt(st.nextToken()); //도로 개수
            int t = Integer.parseInt(st.nextToken()); //목적지 후보 개수

            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()); //출발지
            int g = Integer.parseInt(st.nextToken()); //경유지 1
            int h = Integer.parseInt(st.nextToken()); //경유지 2

            A = new ArrayList[n + 1];

            for (int i = 1; i <= n; i++) { //인접 리스트 생성
                A[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken()); //도시1
                int b = Integer.parseInt(st.nextToken()); //도시2
                int d = Integer.parseInt(st.nextToken()); //도시 간 거리

                //도시1, 도시2 연결
                A[a].add(new Node(b, d));
                A[b].add(new Node(a, d));
            }

            //s, g, h를 출발지로 하는 다익스트라 수행
            //최단거리 정보가 담겨있는 배열 반환
            int[] fromS = dijkstra(s);
            int[] fromG = dijkstra(g);
            int[] fromH = dijkstra(h);

            ArrayList<Integer> result = new ArrayList<>(); //목적지가 될 수 있는 도시를 담을 리스트

            for (int i = 0; i < t; i++) {
                int c = Integer.parseInt(br.readLine());

                int temp = fromS[c]; //각 경유지를 거쳐 가는 것이 이 값과 같아야 한다.

                int temp1 = fromS[g] + fromG[h] + fromH[c];
                if (temp == temp1) {
                    result.add(c);
                }

                int temp2 = fromS[h] + fromH[g] + fromG[c];
                if (temp == temp2) {
                    result.add(c);
                }
            }

            result.stream()
                    .sorted()
                    .mapToInt(i -> i)
                    .forEach(n -> sb.append(n).append(" "));
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int[] dijkstra(int s) {
        Queue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(s, 0));

        int[] temp = new int[n + 1];
        Arrays.fill(temp, max);
        temp[s] = 0;

        boolean[] visit = new boolean[n + 1];

        while (!qu.isEmpty()) {
            Node poll = qu.poll();
            int now = poll.adj;
            int d = poll.dist;

            if (visit[now]) {
                continue;
            }

            visit[now] = true;

            for (Node next : A[now]) {
                int nextNode = next.adj;

                if (temp[nextNode] > temp[now] + next.dist) {
                    temp[nextNode] = temp[now] + next.dist;
                    qu.offer(new Node(nextNode, temp[nextNode]));
                }
            }
        }

        return temp;
    }

    static class Node implements Comparable<Node> {
        int adj, dist; //인접 노드, 거리

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

