package dfs.thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 정점들을 양방향으로 연결하고 거리를 함께 저장한다.
 * 입력받은 두 노드를 dfs 탐색으로 두 노드 사이의 거리를 구한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1240">백준 1240번 : 깊이우선탐색 - 노드사이의 거리</a>
 * @since 2024-02-18
 */
public class Four {
    static List<List<int[]>> tree = new ArrayList<>();//노드를 저장할 리스트, [0]=이웃 노드, [1]=이웃 노드와 거리
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            //양방향 연결
            tree.get(n1).add(new int[]{n2, distance});
            tree.get(n2).add(new int[]{n1, distance});
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            //n1=시작 노드, n2=끝 노드, -1=시작 부모노드, 0=시작 거리
            sb.append(dfs(n1, n2, -1, 0)).append("\n");

        }
        System.out.println(sb);
    }

    static int dfs(int start, int end, int parent, int distance) {
        if (start == end) {//목표 노드에 도착했다면 거리를 반환한다.
            return distance;
        }
        for (int[] node : tree.get(start)) {
            if (node[0] != parent) {//같은 노드를 방문하는 것을 방지한다.
                //이웃노드가 시작이 되고, end는 그대로, 시작한 노드가 부모 노드가 되고, 거리는 이웃한 노드의 거리만큼 더해준다.
                int result = dfs(node[0], end, start, distance + node[1]);
                if (result != -1) {
                    return result;
                }
            }
        }
        return -1;
    }
}
