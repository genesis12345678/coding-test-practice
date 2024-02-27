package dfs.fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 처음 생각은 기존 상태에서 dfs 탐색 후 순서를 비교하고, 안 맞으면 정렬해서 다시 dfs 탐색해서 순서를 비교했는데 틀렸다.
 * 해답은 1부터 시작해서 주변 노드를 자료구조에 저장하고 정답 배열과 비교해서 다음 순서에 정답 배열 순서대로 존재하면 dfs 탐색을 반복한다
 */

/**
 * <a href = "https://www.acmicpc.net/problem/16964">백준 16964번 : 깊이우선탐색 - DFS 스페셜 저지</a>
 * @since 2024-02-27
 */
public class Ten {

    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visit;
    static int[] correct;//정답 배열
    static int idx = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }

        visit = new boolean[N + 1];
        correct = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            correct[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1);//1번부터 시작

        System.out.println(1);
    }

    private static void dfs(int now) {
        visit[now] = true;

        List<Integer> list = new ArrayList<>();
        for (int next : graph.get(now)) {//주변 노드 list에 저장
            if (!visit[next]) {
                list.add(next);
            }
        }

        if (list.isEmpty()) {//리프 노드면 return
            return;
        }

        if (list.contains(correct[idx])) {//주변 노드 중에 정답 순서대로 탐색할 수 있으면 계속 진행
            dfs(correct[idx++]);
        } else {//순서가 맞지 않으면 0을 출력하고 프로그램 종료
            System.out.println(0);
            System.exit(0);
        }
    }
}
