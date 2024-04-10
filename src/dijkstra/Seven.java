package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 기본적인 다익스트라 알고리즘에다가 생각해야 할 것이 있다.
 * 거의 최단 경로는 원래 최단 경로에 포함되지 않는 경로로만 이루어진 최단 경로이다.
 * 즉, 다익스트라를 두 번 수행하여 처음에는 원래 최단 경로를 구하면서 parent를 통해 최단 경로 과정을 저장한다.
 * 첫 번째 다익스트라가 끝나면 parent에 저장되어 있는 정보로 이 경로를 갈 수 없음을 표시한다.
 * 두 번째 다익스트라를 수행할 때는 경로를 체크하면서 도착점까지 가는 거의 최단 경로를 구한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/5719">백준 5719번 - 다익스트라 : 거의 최단 경로</a>
 * @since 2024-04-08
 */
public class Seven {

    static ArrayList<Node>[] A; //인접 리스트
    /**
     * 기존 최단 경로를 저장할 리스트 배열
     * 예를 들어 6번 노드에 도착하기 전 최단 경로 노드가 3,5번 이라면 [6] = {3, 5}가 저장된다.
     */
    static List<Integer>[] parent;
    static boolean[][] check; //두 번째 다익스트라를 수행하면서 경로를 체크할 배열
    static int[] dist;
    static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken()); //노드 수
            m = Integer.parseInt(st.nextToken()); //에지 수

            if (n == 0 && m == 0) {
                break;
            }

            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()); //시작점
            int d = Integer.parseInt(st.nextToken()); //도착점

            A = new ArrayList[n];
            parent = new ArrayList[n];
            dist = new int[n];
            check = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                A[i] = new ArrayList<>();
                parent[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                //u에서 v로 p만큼 연결 - 단방향
                A[u].add(new Node(v, p));
            }

            dijkstra(s, d); //첫 번째 수행, 기존 최단 경로 탐색
            removeShortest(s, d); //두 번째를 수행하기 전에 최단 경로를 탐색에서 제외하기 위해 최단 경로를 지워준다.
            dijkstra(s, d); //두 번째 수행, 거의 최단 경로 탐색

            System.out.println(dist[d] == Integer.MAX_VALUE ? -1 : dist[d]);
        }
    }

    /**
     * 최단 경로를 지워주는 메서드
     * 재귀호출 방식으로 최단 경로를 거쳐온 경로들을 true로 처리해준다.
     */
    static void removeShortest(int s, int now) {
        if (s == now) {
            return;
        }

        for (int n : parent[now]) {
            if (!check[n][now]) {
                check[n][now] = true;
                removeShortest(s, n);
            }
        }
    }

    static void dijkstra(int s, int d) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<Node> qu = new PriorityQueue<>();

        qu.offer(new Node(s, 0));
        dist[s] = 0;

        boolean[] visit = new boolean[n];

        while (!qu.isEmpty()) {
            Node poll = qu.poll();
            int now = poll.adj;

            if (visit[now]) {
                continue;
            }

            visit[now] = true;

            for (Node next : A[now]) {
                int nextNode = next.adj;

                //기존 다익스트라와 큰 차이는 없지만, 이 부분이 추가되어야 한다.
                //첫 번째 다익스트라를 수행할 때는 기본값 false가 들어가므로 다 통과된다.
                //두 번째 다익스트라를 수행할 때는 기존 최단 경로를 true로 되어 있기 때문에
                //거의 최단 경로로 움직일 수밖에 없다.
                if (!check[now][nextNode]) {

                    //parent 리스트 배열에는 각 노드에 이전 노드, 즉 현재 노드로 오기 전 어떤 노드로부터 이동했냐에 대한 정보가 저장된다.
                    //최단 경로가 1개 이상일 수 있기 때문에 최단 경로 값이 같은지 확인해서 추가해서 넣어준다.
                    if (dist[nextNode] == dist[now] + next.dist) {
                        parent[nextNode].add(now);
                    }

                    //이 부분은 새로운 최단 경로를 찾았을 때다.
                    //거리 배열을 갱신하면서 기존 parent에 있던 최단 경로 정보는 더 이상 최단 경로가 될 수 없기 때문에 clear하고 새로운 정보를 저장한다.
                    else if (dist[nextNode] > dist[now] + next.dist) {
                        dist[nextNode] = dist[now] + next.dist;
                        parent[nextNode].clear();
                        parent[nextNode].add(now);
                        qu.offer(new Node(nextNode, dist[nextNode]));
                    }
                }
            }
        }

    }

    static class Node implements Comparable<Node>{
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
