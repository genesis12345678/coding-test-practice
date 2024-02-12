package dfs.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 노드별로 dfs로 깊이를 탐색하면서 깊이가 4이상 나오면 문제 조건에 맞는 것이다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/13023">백준 13023번 : 깊이우선탐색 - ABCDE</a>
 * @since 2024-02-09
 */
public class Six {

    static int N, M;
    static List<List<Integer>> list = new ArrayList<>();
    static boolean[] visit;
    static boolean isOK;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }

        //연결리스트 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        visit = new boolean[N];
        for (int i = 0; i < N; i++) {
            dfs(i, 0);//각 노드별로 최대 깊이를 구한다.

            //dfs를 통해 최대 깊이가 4 이상 나오면 더 이상 탐색할 필요는 없기에 결과를 출력하고 끝낸다.
            if (isOK) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }

    static void dfs(int start, int depth) {
        System.out.println("start => " + start + " || " + "depth => " + depth);
        if (depth == 4) {
            isOK = true;
            return;
        }

        visit[start] = true;
        for (int num : list.get(start)) {
            if (!visit[num]) {
                dfs(num, depth + 1);
            }
        }
        visit[start] = false;//다음 탐색에 지장이 안 가도록 초기화 해준다.
    }
}
