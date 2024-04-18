package bellmanFord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 기본적으로 벨만포드 알고리즘은 음수 사이클을 찾는 알고리즘이다.
 * 하지만 이 문제의 경우, 최댓값을 찾아야 하는 문제이기 때문에 양수 사이클을 찾아야 한다.
 * 하지만 양수 사이클이 발생하더라도 사이클에 관여된 노드가 목적지랑 연결돼있지 않으면 상관없다.
 * 문제는 위 사이클이 발생해도 괜찮은 경우를 판단하는 것인데, 간단한 방법이 있다.
 * 기존 벨만포드 알고리즘은 n-1번 하고 1번 더 알고리즘을 수행해서 사이클을 판단한다.
 * 여기서는 충분히 큰 수만큼 추가로 업데이트를 수행한다. 에지를 충분히 탐색하면서 양수 사이클에서 도달할 수 있는 모든 노드를
 * 양수 사이클에 연결된 노드로 업데이트할 수 있다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1738">백준 1738번 - 벨만포드 : 골목길</a>
 *
 * @since 2024-04-14
 */
public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[m];
        int[] path = new int[n + 1]; //경로 저장 배열
        int[] dist = new int[n + 1]; //경로 최댓값 배열

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(u, v, w);
        }

        Arrays.fill(dist, Integer.MIN_VALUE);
        dist[1] = 0;

        //n의 최댓값만큼 추가적으로 충분히 반복
        for (int i = 0; i < n + 101; i++) {
            for (Edge edge : edges) {
                int start = edge.start;
                int end = edge.end;
                int time = edge.time;

                if (dist[start] == Integer.MIN_VALUE) { //출발 노드가 미방문 노드
                    continue;

                    //출발 노드가 양수 사이클에 연결된 노드
                    //종료 노드 양수 사이클에 연결된 노드로 업데이트
                } else if (dist[start] == Integer.MAX_VALUE) {
                    dist[end] = Integer.MAX_VALUE;

                    //최댓값을 갱신할 수 있는 경로가 발견되면
                    //최댓값 업데이트하고, 경로 저장
                } else if (dist[end] < dist[start] + time) {
                    dist[end] = dist[start] + time;
                    path[end] = start;

                    //n-1번 이후 반복된 것이면 종료 노드 양수 사이클 노드로 업데이트
                    if (i >= n - 1) {
                        dist[end] = Integer.MAX_VALUE;
                    }
                }
            }
        }

        //종료 노드가 양수 사이클이거나 도달할 수 없으면 -1
        if (dist[n] == Integer.MIN_VALUE || dist[n] == Integer.MAX_VALUE) {
            System.out.println(-1);

        //아니면 경로 순서대로 출력
        } else {

            int now = n;
            Stack<Integer> stack = new Stack<>();

            while (now != 0) {
                stack.push(now);
                now = path[now];
            }

            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop()).append(" ");
            }

            System.out.println(sb);
        }
    }

    static class Edge {
        int start;
        int end;
        int time;

        public Edge(int start, int end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }
    }
}
