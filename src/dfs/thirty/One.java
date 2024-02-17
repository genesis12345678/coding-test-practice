package dfs.thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 1번부터 dfs 탐색하면서 사이클이 발생하지 않을 때만 count를 늘린다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/4803">백준 4803번 : 깊이우선탐색 - 트리</a>
 * @since 2024-02-17
 */
public class One {

    static List<List<Integer>> graph;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int caseCount = 1;
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) {
                break;
            }

            sb.append("Case ").append(caseCount++).append(": ");
            graph = new ArrayList<>();
            visit = new boolean[n + 1];
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());

                graph.get(n1).add(n2);
                graph.get(n2).add(n1);
            }

            int count = 0;
            for (int i = 1; i <= n; i++) {
                if (!visit[i]) {
                    if (dfs(i, -1)) {
                        count++;//사이클이 발생하면 false를 반환한다.
                    }
                }
            }
            if (count == 1) {
                sb.append("There is one tree.").append("\n");
            } else if (count > 1) {
                sb.append("A forest of ").append(count).append(" trees.").append("\n");
            } else {
                sb.append("No trees.").append("\n");
            }
        }
        System.out.println(sb);

    }

    static boolean dfs(int node, int parent) {
        if (visit[node]) {//사이클 발생 시 false 반환
            return false;
        }
        visit[node] = true;
        for (int num : graph.get(node)) {
            if (num != parent && !dfs(num, node)) {
                return false;
            }
        }

        return true;//끝까지 탐색을 완료하면 사이클이 발생한 것이 아니므로 true 반환
    }
}
