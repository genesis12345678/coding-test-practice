package bfs.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dfs를 이용해 부모 노드를 찾는다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11725">백준 11725번 : 너비우선탐색 - 트리의 부모 찾기</a>
 * @since 2024-01-15
 */
public class Four {
    static List<List<Integer>> graph = new ArrayList<>(); // 인접 리스트
    static int[] arr; // 각 노드의 부모 노드가 담길 배열
    static boolean[] visit; // 방문처리 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        visit = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        //루트부터 시작
        dfs(1);

        StringBuilder sb = new StringBuilder();
        //2번 노드부터 출력
        for (int i = 2; i <= N; i++) {
            sb.append(arr[i]).append("\n");
        }
        System.out.println(sb);
    }

    /**
     * 부모 노드를 조회할 dfs 메서드
     * x에 의해 찾아진 n값이라고 생각했다.
     * 그래서 x가 부모가 되고 n이 자식이 된다.
     * 그러면 저장될 때 자연스럽게 각 인덱스의 값이 부모노드가 저장된다.
     */
    static void dfs(int x) {
        visit[x] = true;

        for (int n : graph.get(x)) {
            if (!visit[n]) {
                arr[n] = x;
                dfs(n);
            }
        }
    }
}
