package dfs.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 힌트에서 알려준 대로 루트 노드를 기준으로 각 정점을 루트로 할 때 서브트리에 속한 정점의 수를 미리 구해서 저장해 둔다.
 * 이 방법을 사용하라고 일부러 임의의 루트 노드를 알려주는 것 같다.
 * 출력할 때는 미리 구해둔 저장소에서 꺼내기만 하면 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/15681">백준 15681번 : 깊이우선탐색 - 트리와 쿼리</a>
 * @since 2024-02-14
 */
public class Seven {

    static int N, R, Q;
    static List<List<Integer>> tree = new ArrayList<>();
    static int[] subTreeSize;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        //입력, 무방향이므로 양방향으로 연결
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            tree.get(U).add(V);
            tree.get(V).add(U);
        }

        subTreeSize = new int[N + 1];//각 정점을 루트로 할 때 서브트리에 속한 정점의 수를 저장한다.

        dfs(R, 0);//루트노드부터 시작한다, 루트는 부모가 없으므로 0으로 시작

        StringBuilder sb = new StringBuilder();

        //출력할 때는 저장소에서 꺼내서 출력한다.
        for (int i = 0; i < Q; i++) {
            sb.append(subTreeSize[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.println(sb);
    }
    static void dfs(int currentNode, int parentNode) {
        subTreeSize[currentNode] = 1;//자기도 자신을 루트로 하는 서브트리에 포함되므로 1에서 시작한다.

        for (int childNode : tree.get(currentNode)) {//연결된 노드를 하나씩 꺼내서
            if (childNode != parentNode) {//같은 부모를 갖고 있는 노드가 아니라면(자식이 하나라면)
                dfs(childNode, currentNode);//현재 노드를 부모 노드로 하고 더 깊이 파고든다.
                subTreeSize[currentNode] += subTreeSize[childNode];
                //자식 노드에는 자식 노드를 루트로 하는 서브트리의 정점의 수가 저장된다.
                //현재 노드에는 자식 노드에 저장된 수를 더해준다.
            }
        }
    }
}

