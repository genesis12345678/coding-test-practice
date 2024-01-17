package bfs.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 인접 리스트 dfs 방식으로 해결한다.
 * x에서 y를 만날 때까지 몇번 걸리느냐가 문제다.
 */
public class Nine {
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        visit = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int p1 = Integer.parseInt(st.nextToken());
        int p2 = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        System.out.println(dfs(p1, p2, 0));

    }

    static int dfs(int start, int end, int depth) {

        visit[start] = true;

        if (start == end) {
            return depth;
        }

        for (int num : graph.get(start)) {
            if (!visit[num]) {
                int result = dfs(num, end, depth + 1);
                if (result != -1) {
                    return result;
                }
            }
        }
        return -1;
    }
}
