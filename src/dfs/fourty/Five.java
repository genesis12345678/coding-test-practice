package dfs.fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 요청 받을 때마다 dfs 탐색으로 계산하면 시간 초과가 난다.
 * 처음 한번 dfs를 통해 전체 사원의 칭찬 점수를 구하고 출력해야 한다.
 * 1번을 루트 노드로 하고 입력받은 정보를 기준으로 총 결과를 구해야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/14267">백준 14267번 : 깊이우선탐색 - 회사 문화 1</a>
 * @since 2024-02-24
 */
public class Five {
    static List<List<Integer>> tree = new ArrayList<>();
    static int[] praise;
//    static int[] praise2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        praise = new int[n + 1];
//        praise2 = new int[n + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            int boss = Integer.parseInt(st.nextToken());
            if (boss != -1) {
                tree.get(boss).add(i);
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

//            dfs2(num, w); 시간 초과 메서드
            praise[num] += w;
        }

        dfs(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(praise[i]).append(" ");
        }
        System.out.println(sb);

    }

    static void dfs(int node) {
        for (int next : tree.get(node)) {
            praise[next] += praise[node];
            dfs(next);
        }
    }

    /**
     * 시간 초과 메서드
     */
/*
    static void dfs2(int node, int w) {
        praise2[node] += w;

        for (int next : tree.get(node)) {
            dfs2(next, w);
        }
    }
*/

}
