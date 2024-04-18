package bellmanFord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 이 문제의 핵심은 최단 거리를 구하는 것이 아닌 음의 사이클이 발생하는가를 찾아야만 하는 문제다.
 * 두 가지 풀이가 존재한다.
 * 1. 1번부터 n번까지 모든 노드를 시작점으로 보고 n번 벨만포드 알고리즘 수행
 * 2. 1번만을 시작점으로 보고 한번 벨만포드 알고리즘 수행
 * 당연히 2번이 더 효울적인데 주의해야 할 것이 있다.
 * 기존 벨만포드 알고리즘은 단절된 노드를 거르기 위해서 dist[start] != max 를 사용했다.
 * 하지만 이런 방식은 이후 노드들에 대해 사이클 여부를 알 수 없다.(탐색조차 되지 않는다.)
 *
 * 그래서 dist[start] != max는 생략하고 dist배열의 초기값은 오버플로우가 나지 않을 정도로만 적당한 값으로 해도 괜찮다.
 * 왜냐하면 이 문제는 최단 거리가 문제가 아니라 음의 사이클의 유무를 찾는 문제다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1865">백준 1865번 - 벨만포드 : 웜홀</a>
 * @since 2024-04-14
 */
public class Two {

    static int[] dist;
    static ArrayList<Edge>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken()); //정점 개수
            int m = Integer.parseInt(st.nextToken()); //간선 개수
            int w = Integer.parseInt(st.nextToken()); //(웜홀) 음의 간선 개수

            edges = new ArrayList[n + 1];
            dist = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                edges[i] = new ArrayList<>(); //양방향으로 연결해야 해서 리스트 배열로 저장
            }

            for (int i = 0; i < m + w; i++) {
                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                if (i < m) {
                    edges[s].add(new Edge(e, t));
                    edges[e].add(new Edge(s, t));
                } else {
                    edges[s].add(new Edge(e, -t));
                }
            }

/*
            //오래 걸리는 버전, n번까지 모든 번호에 대해 시작점으로 벨만포드를 수행한다.
            boolean cycle = false;

            for (int i = 1; i <= n; i++) {
                if (bellmanFord(i, n)) {
                    sb.append("YES").append("\n");
                    cycle = true;
                    break;
                }
            }

            if (!cycle) {
                sb.append("NO").append("\n");
            }
*/

            //효율적인 버전, 임의의 시작점만으로 벨만포드를 1번 수행한다.
            if (bellmanFord(n)) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }


        System.out.print(sb);
    }

    /**
     * 벨만포드 한 번만 수행
     */
    static boolean bellmanFord(int n) {
        Arrays.fill(dist, -1); //최단 거리가 중요한 것이 아니고, 음의 사이클만 찾으면 되기 때문에 오버플로우가 나지 않을 적당한 값으로 초기화
        dist[1] = 0; //1번 시작점 지정

        //첫 벨만포드 n-1번 수행
        //주의할 점은 dist[start] != max를 체크하면 안 된다. 뒤 노드들의 사이클 여부를 알 수 없다.
        for (int i = 0; i < n - 1; i++) {

            for (int start = 1; start <= n; start++) {
                for (Edge edge : edges[start]) {
                    if (dist[edge.end] > dist[start] + edge.time) {
                        dist[edge.end] = dist[start] + edge.time;
                    }
                }
            }
        }

        //사이클 여부 확인 위해 한번 더 수행
        //주의할 점은 dist[start] != max를 체크하면 안 된다. 뒤 노드들의 사이클 여부를 알 수 없다.
        for (int i = 1; i <= n; i++) {
            for (Edge edge : edges[i]) {
                if (dist[edge.end] > dist[i] + edge.time) {
                    return true; //사이클 발생
                }
            }
        }

        return false;
    }

    /**
     * 벨만포드 n번 수행
     */
    static boolean bellmanFord(int start, int n) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        boolean update = false; //n번 수행하는 벨만포드에서는 업데이트가 발생했는지 체크해줘야 시간초과가 발생하지 않는다.

        //첫 벨만포드 n-1번 수행
        for (int i = 0; i < n - 1; i++) {

            update = false;

            for (int j = 1; j <= n; j++) {
                for (Edge edge : edges[j]) {
                    if (dist[j] != Integer.MAX_VALUE && dist[edge.end] > dist[j] + edge.time) {
                        dist[edge.end] = dist[j] + edge.time;
                        update = true;
                    }
                }
            }

            //최단거리 업데이트가 발생하지 않은 경우 종료
            //n번 수행하는 경우 체크해 줘야 시간초과가 발생하지 않는다.
            if (!update) {
                break;
            }
        }

        //n-1번까지 업데이트가 발생했을 경우
        //n번도 업데이트가 발생하면 음의 사이클 발생을 의미
        if (update) {
            for (int i = 1; i <= n; i++) {
                for (Edge edge : edges[i]) {
                    if (dist[i] != Integer.MAX_VALUE && dist[edge.end] > dist[i] + edge.time) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    static class Edge {
        int end, time;

        public Edge(int end, int time) {
            this.end = end;
            this.time = time;
        }

    }
}
