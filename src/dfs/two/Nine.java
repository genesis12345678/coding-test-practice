package dfs.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 루트 노드를 구한 뒤 루트 노드로부터 각 정점들의 부모 노드와 깊이를 구한다.
 * 부모 노드와 깊이를 사용해서 최소 공통 조상을 구한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/3584">백준 3584번 : 가장 가까운 공통 조상</a>
 * @since 2024-02-15
 */
public class Nine {

    static int N;
    static List<List<Integer>> tree;
    static int[] parent, depth; //부모와 깊이 저장 배열
    static int root; //루트 노드

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());

            tree = new ArrayList<>();
            parent = new int[N + 1];
            depth = new int[N + 1];

            for (int i = 0; i <= N; i++) {
                tree.add(new ArrayList<>());
            }

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                tree.get(a).add(b);
                tree.get(b).add(a);

                parent[b] = a; //b의 부모는 a다.
            }

            //부모 노드가 초기화되지 않은 번호가 루트가 된다.
            for (int i = 1; i <= N; i++) {
                if (parent[i] == 0) {
                    root = i;
                    break;
                }
            }

            //루트 노드부터 각 정점들의 깊이를 구한다.
            dfs(root, 0);

            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            sb.append(solution(n1, n2)).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int currentNode, int parent) {
        for (int num : tree.get(currentNode)) {
            if (num != parent) {
                depth[num] = depth[currentNode] + 1;
                dfs(num, currentNode);
            }
        }
    }
    static int solution(int n1, int n2) {
        while (depth[n1] != depth[n2]) { //깊이를 맞춰야 한다. 깊이가 같을 때까지 반복한다.
            if (depth[n1] > depth[n2]) {
                n1 = parent[n1];
            } else {
                n2 = parent[n2];
            }
        }

        while (n1 != n2) { //두 노드의 같은 부모가 나올 떄까지 각 노드를 부모 노드로 변경하는 작업을 반복한다.
            n1 = parent[n1];
            n2 = parent[n2];
        }
        return n1;
    }
}
