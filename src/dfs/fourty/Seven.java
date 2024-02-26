package dfs.fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 이동할 수 있는 횟수가 홀수여야 이길 수 있다.
 * 루트 노드(1번)부터 각 리프 노드까지 깊이 합이 홀수여야 한다.
 * 게임말이 부모 노드로만 옮길 수 있는 것이므로 위로 한 칸씩만 이동할 수 있는 게임이다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/15900">백준 15900번 : 깊이우선탐색 - 나무 탈출</a>
 * @since 2024-02-26
 */
public class Seven {
    static List<List<Integer>> tree = new ArrayList<>();
    static boolean[] visit;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        visit = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        dfs(1, 0);//루트 노드 1번부터 깊이 0으로 시작

        //결과가 홀수여야 이길 수 있다.
        if (result % 2 == 1) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    static void dfs(int cur, int depth) {
        visit[cur] = true;

        for (int next : tree.get(cur)) {
            if (!visit[next]) {
                dfs(next, depth + 1);
            }
        }
//        System.out.println("cur = " + cur + " || tree.get(cur).size() = " + tree.get(cur).size());

        if (cur != 1 && tree.get(cur).size() == 1) {//루트 노드가 아니고, size가 1이라는 것은 부모 노드만 존재하는 것이니 리프 노드다.
            result += depth;
        }
    }
}
