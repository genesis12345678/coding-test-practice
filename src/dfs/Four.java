package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 양방향으로 parent와 child를 연결하고나서 어떻게 해보려고 했는데 그럴 필요 없이 인덱스에 부모 노드만 저장해놓아도 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1068">백준 1068번 : 깊이우선탐색 - 트리</a>
 * @since 2024-02-08
 */
public class Four {

    static boolean[] visit;
    static int[] parent;
    static int removeNode;
    static int N;
    static int result = 0;
    static int root = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        visit = new boolean[N];
        parent = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());

            if (n == -1) { //루트 노드 저장
                root = i;
            }
            parent[i] = n; //각 인덱스에는 자신의 부모 노드 번호가 저장된다.
        }

        removeNode = Integer.parseInt(br.readLine());
        //삭제하려는 노드가 루트 노드라면 노드가 모두 삭제된다.
        if (removeNode == root) {
            System.out.println(0);
            return;
        }

        remove(removeNode); //노드 제거
        countLeaf(root); //리프 노드 계산, 루트 노드부터

        System.out.println(result);
    }

    static void remove(int removeNode) {
        parent[removeNode] = -2; //삭제 대상 노드는 -2로 표시한다.

        //재귀호출로 자신의 부모 노드가 삭제 대상인지 탐색한다.
        //부모 노드가 삭제 대상이면 자식도 삭제 대상이 된다.
        for (int i = 0; i < N; i++) {
            if (parent[i] == removeNode) {
                remove(i);
            }
        }
    }

    static void countLeaf(int n) {
        boolean isLeaf = true;
        visit[n] = true;
        if (parent[n] != -2) {//삭제 대상이 아닌 노드만 탐색한다.
            for (int i = 0; i < N; i++) {
                //자신을 부모로 갖고 있는 노드가 있으면 그 번호는 리프 노드가 아니다.
                if (!visit[i] && parent[i] == n) {
                    countLeaf(i);
                    isLeaf = false;
                }
            }
            //탐색을 다 해도 자신을 부모로 갖고 있는 노드가 나오지 않으면 그것은 리프 노드다.
            if (isLeaf) {
                result++;
            }
        }
    }
}
