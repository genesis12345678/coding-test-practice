package dfs.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 트리의 지름(골드4) 문제와 똑같은 방식으로 하면 된다. 입력받는 부분만 변경 해주었다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1167">백준 1167번 : 깊이우선탐색 - 트리의 지름</a>
 * @since 2024-02-08
 */

public class Three {

    static List<List<int[]>> tree = new ArrayList<>();
    static boolean[] visit;
    static int maxDist = 0;
    static int farthestNode;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());

        for (int i = 0; i <= V; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());

            while (true) {
                int child = Integer.parseInt(st.nextToken());
                if (child == -1) {
                    break;
                }
                int weight = Integer.parseInt(st.nextToken());
                tree.get(parent).add(new int[]{child, weight});
                tree.get(child).add(new int[]{parent, weight});
            }
        }

        //루트 노드로부터 가장 거리가 먼 노드를 찾는다.
        visit = new boolean[V + 1];
        dfs(1, 0);

        //트리의 지름을 구한다.
        visit = new boolean[V + 1];
        dfs(farthestNode, 0);

        System.out.println(maxDist);
    }

    static void dfs(int node, int distance) {
        if (visit[node]) {
            return;
        }
        visit[node] = true;

        //최장거리와 노드 갱신
        if (distance > maxDist) {
            maxDist = distance;
            farthestNode = node;
        }

        //재귀호출로 탐색
        for (int[] now : tree.get(node)) {
            dfs(now[0], distance + now[1]);
        }
    }
}
