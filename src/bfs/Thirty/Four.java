package bfs.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 이분 그래프란 쉽게 생각해서 서로 다른 두 개로 끝까지 탐색할 수 있으면 이분 그래프이다.
 * 즉 어떤 한 정점에서 연결된 모든 정점이 다른 값을 가져야 한다.
 * 여기서는 1과 2로 번갈아서 가기로 했다.(boolean으로 true, false로도 가능하다.)
 */
public class Four {
    static List<List<Integer>> graph; // 인접 리스트
    static StringBuilder sb = new StringBuilder();
    static int[] visit;
    static int V; // 정점 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        while (K-- > 0) {
            graph = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            visit = new int[V + 1];

            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph.get(u).add(v);
                graph.get(v).add(u);
            }
            bfs();

        }
        System.out.println(sb);
    }

    static void bfs() {
        Queue<Integer> qu = new LinkedList<>();
        for (int i = 1; i <= V; i++) {
            if (visit[i] == 0) {
                qu.add(i);
                visit[i] = 1;

                while (!qu.isEmpty()) {
                    int now = qu.poll();

                    for (int num : graph.get(now)) {
                        if (visit[num] == 0) { // visit[num]==0 은 방문을 안 한 거라고 볼 수 있다.
                            qu.add(num);
                            visit[num] = 3 - visit[now];
                            //visit[now]에는 1또는 2가 올 것이다.
                            //방문을 안 한 노드여서 if (visit[i] == 0)에 걸려 1인 경우와
                            //다음 노드로 이동해서 2인 경우
                        } else if (visit[num] == visit[now]) {
                            // 자식 노드끼리 값이 같다면 이분 그래프가 아니다.
                            sb.append("NO").append("\n");
                            return;
                        }
                    }
                }
            }
        }
        sb.append("YES").append("\n");
    }
}
